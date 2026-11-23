package com.android.internal.util

import android.content.Context

class LatencyTracker private constructor() {
    fun onActionStart(action: Int) {}
    fun onActionStart(action: Int, tag: String?) {}
    fun onActionEnd(action: Int) {}
    fun onActionCancel(action: Int) {}

    companion object {
        const val ACTION_EXPAND_PANEL = 0
        const val ACTION_TOGGLE_RECENTS = 1
        const val ACTION_FINGERPRINT_WAKE_AND_UNLOCK = 2
        const val ACTION_CHECK_CREDENTIAL = 3
        const val ACTION_CHECK_CREDENTIAL_UNLOCKED = 4
        const val ACTION_TURN_ON_SCREEN = 5
        const val ACTION_ROTATE_SCREEN = 6
        const val ACTION_FACE_WAKE_AND_UNLOCK = 7
        const val ACTION_START_RECENTS_ANIMATION = 8
        const val ACTION_ROTATE_SCREEN_CAMERA_CHECK = 9
        const val ACTION_ROTATE_SCREEN_SENSOR = 10
        const val ACTION_LOCKSCREEN_UNLOCK = 11
        const val ACTION_USER_SWITCH = 12
        const val ACTION_SWITCH_DISPLAY_UNFOLD = 13
        const val ACTION_UDFPS_ILLUMINATE = 14
        const val ACTION_SHOW_BACK_ARROW = 15
        const val ACTION_LOAD_SHARE_SHEET = 16
        const val ACTION_SHOW_SELECTION_TOOLBAR = 17
        const val ACTION_FOLD_TO_AOD = 18
        const val ACTION_SHOW_VOICE_INTERACTION = 19
        const val ACTION_REQUEST_IME_SHOWN = 20
        const val ACTION_REQUEST_IME_HIDDEN = 21
        const val ACTION_SMARTSPACE_DOORBELL = 22

        @JvmStatic
        fun getInstance(context: Context): LatencyTracker = LatencyTracker()
    }
}
