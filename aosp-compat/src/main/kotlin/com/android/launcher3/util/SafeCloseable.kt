package com.android.launcher3.util

fun interface SafeCloseable : AutoCloseable {
    override fun close()
}
