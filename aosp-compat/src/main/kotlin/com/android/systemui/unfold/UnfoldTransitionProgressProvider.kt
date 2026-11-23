package com.android.systemui.unfold

interface UnfoldTransitionProgressProvider {
    fun addCallback(listener: Any?) {}
    fun removeCallback(listener: Any?) {}
}
