package com.android.wm.shell.shared.desktopmode

import android.content.Context
import android.view.Display

object DesktopModeStatus {
    @JvmStatic fun canEnterDesktopMode(context: Context): Boolean = false
    @JvmStatic fun canEnterDesktopModeOrShowAppHandle(context: Context): Boolean = false
    @JvmStatic fun isDesktopModeSupported(context: Context): Boolean = false
    @JvmStatic fun enableDesktopWindowingWallpaperActivity(): Boolean = false
}

class DesktopModeCompatPolicy(private val context: Context) {
    fun shouldDisableDesktopEntryPoints(packageName: String): Boolean = true
    fun isTopActivityExemptFromDesktopWindowing(task: Any?): Boolean = false
}

interface DesktopState {
    val canEnterDesktopMode: Boolean
    val isDesktopModeSupported: Boolean
    fun isDesktopModeSupportedOnDisplay(display: Display): Boolean = false
    fun isDesktopModeSupportedOnDisplay(displayId: Int): Boolean = false

    companion object {
        @JvmStatic
        fun fromContext(context: Context): DesktopState = object : DesktopState {
            override val canEnterDesktopMode: Boolean = false
            override val isDesktopModeSupported: Boolean = false
        }
    }
}

enum class DesktopModeTransitionSource {
    TASK_DRAG,
    APP_FROM_OVERVIEW,
    APP_HANDLE_MENU_BUTTON,
    KEYBOARD_SHORTCUT,
    UNKNOWN
}
