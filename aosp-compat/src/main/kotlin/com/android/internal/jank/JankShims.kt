package com.android.internal.jank

import android.view.View

object Cuj {
    const val CUJ_LAUNCHER_APP_LAUNCH_FROM_RECENTS = 7
    const val CUJ_LAUNCHER_APP_LAUNCH_FROM_ICON = 8
    const val CUJ_LAUNCHER_APP_CLOSE_TO_HOME = 9
    const val CUJ_LAUNCHER_APP_CLOSE_TO_PIP = 10
    const val CUJ_LAUNCHER_QUICK_SWITCH = 11
    const val CUJ_LAUNCHER_APP_SWIPE_TO_RECENTS = 66
    const val CUJ_LAUNCHER_OPEN_ALL_APPS = 25
    const val CUJ_LAUNCHER_CLOSE_ALL_APPS_SWIPE = 67
    const val CUJ_LAUNCHER_CLOSE_ALL_APPS_TO_HOME = 68
    const val CUJ_LAUNCHER_UNLOCK_ENTRANCE_ANIMATION = 63
    const val CUJ_LAUNCHER_APP_LAUNCH_FROM_WIDGET = 27
    const val CUJ_LAUNCHER_PRIVATE_SPACE_LOCK = 102
    const val CUJ_LAUNCHER_PRIVATE_SPACE_UNLOCK = 103
    const val CUJ_LAUNCHER_UNFOLD_ANIM = 83
    const val CUJ_DESKTOP_MODE_APP_LAUNCH_FROM_INTENT = 117
    const val CUJ_DESKTOP_MODE_APP_LAUNCH_FROM_ICON = 118
    const val CUJ_DESKTOP_MODE_MINIMIZE_WINDOW = 109
    const val CUJ_DESKTOP_MODE_MAXIMIZE_WINDOW = 104
    const val CUJ_DESKTOP_MODE_UNMAXIMIZE_WINDOW = 116
    const val CUJ_DESKTOP_MODE_ENTER_MODE_APP_HANDLE_MENU = 112
    const val CUJ_DESKTOP_MODE_EXIT_MODE = 108
}

class InteractionJankMonitor private constructor() {
    fun begin(v: View?, cujType: Int): Boolean = false
    fun begin(builder: Configuration.Builder): Boolean = false
    fun end(cujType: Int): Boolean = false
    fun cancel(cujType: Int): Boolean = false

    class Configuration private constructor() {
        class Builder {
            fun setTag(tag: String?): Builder = this
            fun setTimeout(timeout: Long): Builder = this
            fun setDeferMonitorForAnimationStart(defer: Boolean): Builder = this
            fun build(): Configuration = Configuration()

            companion object {
                @JvmStatic
                fun withView(cuj: Int, view: View): Builder = Builder()
            }
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(): InteractionJankMonitor = InteractionJankMonitor()
    }
}
