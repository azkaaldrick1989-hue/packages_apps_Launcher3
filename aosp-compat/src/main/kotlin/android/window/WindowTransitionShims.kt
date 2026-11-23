package android.window

import android.app.ActivityManager
import android.content.ComponentName
import android.graphics.Point
import android.graphics.Rect
import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.SurfaceControl

interface IRemoteTransitionFinishedCallback : IInterface {
    fun onTransitionFinished(wct: WindowContainerTransaction?, sct: SurfaceControl.Transaction?)

    abstract class Stub : Binder(), IRemoteTransitionFinishedCallback {
        override fun asBinder(): IBinder = this
        override fun onTransitionFinished(
            wct: WindowContainerTransaction?,
            sct: SurfaceControl.Transaction?
        ) {}
    }
}

interface IRemoteTransition : IInterface {
    fun startAnimation(
        token: IBinder,
        info: TransitionInfo,
        t: SurfaceControl.Transaction,
        finishCallback: IRemoteTransitionFinishedCallback
    )

    fun mergeAnimation(
        transition: IBinder,
        info: TransitionInfo,
        t: SurfaceControl.Transaction,
        mergeTarget: IBinder,
        finishCallback: IRemoteTransitionFinishedCallback
    ) {}

    fun takeOverAnimation(
        transition: IBinder,
        info: TransitionInfo,
        t: SurfaceControl.Transaction,
        finishCallback: IRemoteTransitionFinishedCallback,
        states: Array<WindowAnimationState>?
    ) {}

    fun onTransitionConsumed(transition: IBinder, aborted: Boolean) {}

    abstract class Stub : Binder(), IRemoteTransition {
        override fun asBinder(): IBinder = this
    }
}

abstract class RemoteTransitionStub : IRemoteTransition.Stub()

class WindowAnimationState : Parcelable {
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<WindowAnimationState> {
        override fun createFromParcel(source: Parcel): WindowAnimationState = WindowAnimationState()
        override fun newArray(size: Int): Array<WindowAnimationState?> = arrayOfNulls(size)
    }
}

class WindowContainerTransaction : Parcelable {
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<WindowContainerTransaction> {
        override fun createFromParcel(source: Parcel): WindowContainerTransaction =
            WindowContainerTransaction()
        override fun newArray(size: Int): Array<WindowContainerTransaction?> = arrayOfNulls(size)
    }
}

class RemoteTransition @JvmOverloads constructor(
    var remoteTransition: IRemoteTransition? = null,
    var appThread: android.app.IApplicationThread? = null,
    var debugName: String? = null
) : Parcelable {
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<RemoteTransition> {
        override fun createFromParcel(source: Parcel): RemoteTransition = RemoteTransition()
        override fun newArray(size: Int): Array<RemoteTransition?> = arrayOfNulls(size)
    }
}

class TransitionInfo(
    var type: Int = 0,
    var flags: Int = 0
) : Parcelable {
    val changes: MutableList<Change> = mutableListOf()
    val roots: MutableList<Root> = mutableListOf()
    var debugId: Int = -1

    fun getRoot(idx: Int): Root = roots.getOrElse(idx) { Root() }
    fun findRootIndex(displayId: Int): Int = roots.indexOfFirst { it.displayId == displayId }.coerceAtLeast(0)

    class Root(
        val displayId: Int = 0,
        val leash: SurfaceControl = SurfaceControl.Builder().setName("stub-root").build(),
        val offset: Point = Point()
    )

    class Change(
        val container: WindowContainerToken? = null,
        val leash: SurfaceControl = SurfaceControl.Builder().setName("stub-change").build()
    ) {
        var parent: WindowContainerToken? = null
        var lastParent: WindowContainerToken? = null
        var mode: Int = 0
        var flags: Int = 0
        var taskInfo: ActivityManager.RunningTaskInfo? = null
        var startAbsBounds: Rect = Rect()
        var endAbsBounds: Rect = Rect()
        var endRelOffset: Point = Point()
        var startDisplayId: Int = 0
        var endDisplayId: Int = 0
        var startRotation: Int = 0
        var endRotation: Int = 0
        var activityComponent: ComponentName? = null

        fun hasFlags(flags: Int): Boolean = (this.flags and flags) != 0
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        const val FLAG_NONE = 0
        const val FLAG_SHOW_WALLPAPER = 1
        const val FLAG_IS_WALLPAPER = 2
        const val FLAG_TRANSLUCENT = 4
        const val FLAG_STARTING_WINDOW_TRANSFER_RECIPIENT = 8
        const val FLAG_IS_VOICE_INTERACTION = 16
        const val FLAG_IS_DISPLAY = 32
        const val FLAG_FIRST_CUSTOM = 1 shl 16

        @JvmField
        val CREATOR: Parcelable.Creator<TransitionInfo> = object : Parcelable.Creator<TransitionInfo> {
            override fun createFromParcel(source: Parcel): TransitionInfo = TransitionInfo()
            override fun newArray(size: Int): Array<TransitionInfo?> = arrayOfNulls(size)
        }
    }
}

class WindowContainerToken : Parcelable {
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<WindowContainerToken> {
        override fun createFromParcel(source: Parcel): WindowContainerToken = WindowContainerToken()
        override fun newArray(size: Int): Array<WindowContainerToken?> = arrayOfNulls(size)
    }
}

class TransitionFilter : Parcelable {
    @JvmField var mTypeSet: IntArray? = null
    @JvmField var mFlags: Int = 0
    @JvmField var mNotFlags: Int = 0
    @JvmField var mRequirements: Array<Requirement>? = null

    class Requirement : Parcelable {
        @JvmField var mActivityType: Int = 0
        @JvmField var mWindowingMode: Int = 0
        @JvmField var mModes: IntArray? = null
        @JvmField var mMustBeTask: Boolean = false
        @JvmField var mNot: Boolean = false
        @JvmField var mIsCrossDisplayMove: Boolean = false
        @JvmField var mTopActivity: ComponentName? = null

        override fun describeContents(): Int = 0
        override fun writeToParcel(dest: Parcel, flags: Int) {}

        companion object CREATOR : Parcelable.Creator<Requirement> {
            override fun createFromParcel(source: Parcel): Requirement = Requirement()
            override fun newArray(size: Int): Array<Requirement?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<TransitionFilter> {
        override fun createFromParcel(source: Parcel): TransitionFilter = TransitionFilter()
        override fun newArray(size: Int): Array<TransitionFilter?> = arrayOfNulls(size)
    }
}
