package com.android.launcher3.compat

/**
 * Kompatibilitas SafeCloseable untuk Closeable / SAM / lambda.
 */
fun interface SafeCloseable : AutoCloseable {
    override fun close()
}

/**
 * Anotasi ThreadSafe shim agar anotasi AOSP/SystemUI tidak memicu unresolved reference.
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.LOCAL_VARIABLE
)
@Retention(AnnotationRetention.SOURCE)
annotation class ThreadSafe

/**
 * Shim Flags untuk menggantikan flag aconfig/build AOSP saat build Gradle standalone.
 */
object Flags {
    @JvmStatic
    fun enableUnfoldStateAnimation(): Boolean = false

    @JvmStatic
    fun enableTaskbarUiThread(): Boolean = false

    @JvmStatic
    fun enableRefactorTaskbar(): Boolean = false

    @JvmStatic
    fun enableGridOnlyOverview(): Boolean = false

    @JvmStatic
    fun enableDesktopWindowingMode(): Boolean = false
}

open class IconProvider(
    private val context: Any? = null
) {
    open fun getIcon(info: Any?, iconDpi: Int = 0): Any? = null
    open fun getIcon(componentName: Any?, userHandle: Any? = null): Any? = null
    open fun registerIconChangeListener(listener: Any?): SafeCloseable = SafeCloseable {}
}

interface RemoteAnimationDelegate<T> {
    fun onAnimationStart(
        transit: Int,
        apps: Array<Any>?,
        wallpapers: Array<Any>?,
        nonApps: Array<Any>?,
        finishedCallback: Runnable?
    )

    fun onAnimationCancelled() {}
}

open class RemoteAnimationRunnerCompat {
    open fun onAnimationStart(
        transit: Int,
        apps: Array<Any>?,
        wallpapers: Array<Any>?,
        nonApps: Array<Any>?,
        finishedCallback: Runnable?
    ) {
    }

    open fun onAnimationCancelled() {}
}

interface RecentsAnimationListener {
    fun onAnimationStart(
        controller: Any?,
        transitionTargets: Array<Any>?,
        apps: Array<Any>?,
        homeContentEnter: Boolean,
        transit: Int,
        wallpaperTargets: Array<Any>?
    ) {}

    fun onAnimationCanceled(taskIds: IntArray?, taskSnapshots: Map<Int, Any>?) {}
    fun onTasksAppeared(apps: Array<Any>?) {}
}

interface TaskStackChangeListener {
    fun onTaskStackChanged() {}
    fun onTaskMovedToFront(taskInfo: Any?) {}
    fun onTaskRemoved(taskId: Int) {}
}

interface PluginListener<T> {
    fun onPluginConnected(plugin: T?, pluginContext: Any?) {}
    fun onPluginDisconnected(plugin: T?) {}
}

interface PluginEnabler {
    fun setEnabled(component: Any?) {}
    fun setDisabled(component: Any?, reason: Int) {}
    fun isEnabled(component: Any?): Boolean = true
    fun getDisableReason(component: Any?): Int = 0
}

interface ResourceProvider {
    fun getFloat(key: String?): Float = 0f
    fun getInt(key: String?): Int = 0
    fun getBoolean(key: String?): Boolean = false
}

open class ComponentKey(
    val componentName: Any? = null,
    val user: Any? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComponentKey) return false
        return componentName == other.componentName && user == other.user
    }

    override fun hashCode(): Int {
        var result = componentName?.hashCode() ?: 0
        result = 31 * result + (user?.hashCode() ?: 0)
        return result
    }
}

open class CachedObject<T>(
    val value: T? = null
) {
    open fun get(): T? = value
}

interface LauncherOverlayCallbacks {
    fun onScrollChanged(progress: Float) {}
    fun onOverlayScrollInteractionBegin() {}
    fun onOverlayScrollInteractionEnd() {}
}

interface UnfoldTransitionProgressProvider {
    fun addCallback(listener: Any?) {}
    fun removeCallback(listener: Any?) {}
}

open class Stub {
    open fun asBinder(): Any? = null
}
