package android.app

import android.os.Binder
import android.os.IBinder
import android.os.IInterface

interface IApplicationThread : IInterface {
    abstract class Stub : Binder(), IApplicationThread {
        override fun asBinder(): IBinder = this
    }
}

object WindowConfiguration {
    const val ACTIVITY_TYPE_UNDEFINED = 0
    const val ACTIVITY_TYPE_STANDARD = 1
    const val ACTIVITY_TYPE_HOME = 2
    const val ACTIVITY_TYPE_RECENTS = 3
    const val ACTIVITY_TYPE_ASSISTANT = 4
    const val ACTIVITY_TYPE_DREAM = 5

    const val WINDOWING_MODE_UNDEFINED = 0
    const val WINDOWING_MODE_FULLSCREEN = 1
    const val WINDOWING_MODE_PINNED = 2
    const val WINDOWING_MODE_MULTI_WINDOW = 6
    const val WINDOWING_MODE_FREEFORM = 5
}
