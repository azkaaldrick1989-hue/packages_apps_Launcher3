package android.view

import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import java.util.concurrent.Executor
import java.util.function.Consumer

interface IWindowManager : IInterface {
    abstract class Stub : Binder(), IWindowManager {
        override fun asBinder(): IBinder = this
        companion object {
            @JvmStatic
            fun asInterface(obj: IBinder?): IWindowManager? = null
        }
    }
}

object WindowManagerGlobal {
    @JvmStatic
    fun getWindowManagerService(): IWindowManager? = null

    @JvmStatic
    fun getInstance(): WindowManagerGlobal = this
}

class CrossWindowBlurListeners private constructor() {
    val isCrossWindowBlurEnabled: Boolean = false

    fun addListener(executor: Executor, listener: Consumer<Boolean>) {
        listener.accept(false)
    }

    fun removeListener(listener: Consumer<Boolean>) {}

    companion object {
        @JvmStatic
        val instance: CrossWindowBlurListeners = CrossWindowBlurListeners()
    }
}
