package com.android.launcher3.util

import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Consumer

interface ListenableRef<T> {
    val value: T
    fun addListener(listener: Consumer<T>): AutoCloseable
}

class MutableListenableRef<T>(initialValue: T) : ListenableRef<T> {
    private val listeners = CopyOnWriteArrayList<Consumer<T>>()

    override var value: T = initialValue
        private set

    fun dispatchValue(newValue: T) {
        value = newValue
        listeners.forEach { it.accept(newValue) }
    }

    override fun addListener(listener: Consumer<T>): AutoCloseable {
        listeners.add(listener)
        return AutoCloseable { listeners.remove(listener) }
    }
}

class MutableListenableStream<T> {
    private val listeners = CopyOnWriteArrayList<Consumer<T>>()

    fun dispatchValue(value: T) {
        listeners.forEach { it.accept(value) }
    }

    fun addListener(listener: Consumer<T>): AutoCloseable {
        listeners.add(listener)
        return AutoCloseable { listeners.remove(listener) }
    }
}
