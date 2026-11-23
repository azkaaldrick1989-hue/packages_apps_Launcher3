package com.android.app.displaylib

import android.content.Context
import android.view.Display
import android.view.IWindowManager
import java.util.concurrent.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PerDisplayRepository<T> {
    val debugName: String
    operator fun get(displayId: Int): T?
    fun getOrDefault(displayId: Int, defaultValue: T): T = get(displayId) ?: defaultValue
    fun forEach(createIfAbsent: Boolean = false, action: (T) -> Unit)
}

interface PerDisplayInstanceProvider<T> {
    fun createInstance(displayId: Int): T?
}

interface PerDisplayInstanceProviderWithTeardown<T> : PerDisplayInstanceProvider<T> {
    fun destroyInstance(instance: T)
}

interface DisplayInstanceLifecycleManager {
    val displayIds: StateFlow<Set<Int>>
}

class PerDisplayInstanceRepositoryImpl<T>(
    override val debugName: String,
    private val instanceProvider: PerDisplayInstanceProvider<T>,
    private val lifecycleManager: DisplayInstanceLifecycleManager? = null,
    private val backgroundApplicationScope: CoroutineScope? = null,
    private val displayRepository: DisplayRepository? = null,
    private val createInstanceEagerly: Boolean = false
) : PerDisplayRepository<T> {
    private val instances = mutableMapOf<Int, T>()

    override fun get(displayId: Int): T? {
        return instances[displayId] ?: instanceProvider.createInstance(displayId)?.also {
            instances[displayId] = it
        }
    }

    override fun forEach(createIfAbsent: Boolean, action: (T) -> Unit) {
        if (createIfAbsent && !instances.containsKey(Display.DEFAULT_DISPLAY)) {
            get(Display.DEFAULT_DISPLAY)
        }
        instances.values.forEach(action)
    }
}

interface DisplayRepository {
    val displayIds: StateFlow<Set<Int>>
    val displays: StateFlow<Set<Display>>
    fun getDisplay(displayId: Int): Display?
}

interface DisplaysWithDecorationsRepository {
    val displayIdsWithSystemDecorations: StateFlow<Set<Int>>
}

interface DisplaysWithDecorationsRepositoryCompat {
    val displayIdsWithSystemDecorations: StateFlow<Set<Int>>
}

interface DisplayLibComponent {
    val displayRepository: DisplayRepository
    val displaysWithDecorationsRepository: DisplaysWithDecorationsRepository
    val displaysWithDecorationsRepositoryCompat: DisplaysWithDecorationsRepositoryCompat
}

fun createDisplayLibComponent(
    windowManager: IWindowManager,
    backgroundHandler: Any?,
    bgApplicationScope: CoroutineScope,
    bgExecutor: Executor
): DisplayLibComponent {
    val defaultSet = MutableStateFlow(setOf(Display.DEFAULT_DISPLAY))
    val emptyDisplays = MutableStateFlow(emptySet<Display>())
    val repo = object : DisplayRepository {
        override val displayIds: StateFlow<Set<Int>> = defaultSet
        override val displays: StateFlow<Set<Display>> = emptyDisplays
        override fun getDisplay(displayId: Int): Display? = null
    }
    val decoRepo = object : DisplaysWithDecorationsRepository {
        override val displayIdsWithSystemDecorations: StateFlow<Set<Int>> = defaultSet
    }
    val decoCompatRepo = object : DisplaysWithDecorationsRepositoryCompat {
        override val displayIdsWithSystemDecorations: StateFlow<Set<Int>> = defaultSet
    }
    return object : DisplayLibComponent {
        override val displayRepository: DisplayRepository = repo
        override val displaysWithDecorationsRepository: DisplaysWithDecorationsRepository = decoRepo
        override val displaysWithDecorationsRepositoryCompat: DisplaysWithDecorationsRepositoryCompat = decoCompatRepo
    }
}
