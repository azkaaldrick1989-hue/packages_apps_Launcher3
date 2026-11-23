package com.android.systemui.shared.system

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.os.Handler
import android.window.BackEvent
import android.window.OnBackAnimationCallback

class ActivityManagerWrapper private constructor() {
    val runningTask: ActivityManager.RunningTaskInfo? = null
    fun getRunningTasks(ignoreHomeAndRecents: Boolean): Array<ActivityManager.RunningTaskInfo> = emptyArray()
    fun getRunningTasks(ignoreHomeAndRecents: Boolean, userId: Int): Array<ActivityManager.RunningTaskInfo> = emptyArray()
    fun removeTask(taskId: Int) {}
    fun removeAllRecentTasks() {}
    fun isLockTaskKioskModeActive(): Boolean = false

    companion object {
        @JvmStatic
        val instance: ActivityManagerWrapper = ActivityManagerWrapper()
    }
}

interface TaskStackChangeListener {
    fun onTaskStackChanged() {}
    fun onTaskStackChangedBackground() {}
    fun onActivityPinned(packageName: String, userId: Int, taskId: Int, stackId: Int) {}
    fun onActivityUnpinned() {}
    fun onActivityRestartAttempt(
        task: ActivityManager.RunningTaskInfo,
        homeTaskVisible: Boolean,
        clearedTask: Boolean,
        wasVisible: Boolean
    ) {}
    fun onActivityForcedResizable(packageName: String, taskId: Int, reason: Int) {}
    fun onActivityDismissingDockedStack() {}
    fun onActivityLaunchOnSecondaryDisplayFailed() {}
    fun onActivityLaunchOnSecondaryDisplayRerouted() {}
    fun onTaskProfileLocked(taskInfo: ActivityManager.RunningTaskInfo, userId: Int) {}
    fun onTaskCreated(taskId: Int, componentName: ComponentName?) {}
    fun onTaskRemoved(taskId: Int) {}
    fun onTaskMovedToFront(taskInfo: ActivityManager.RunningTaskInfo) {}
    fun onTaskDescriptionChanged(taskInfo: ActivityManager.RunningTaskInfo) {}
    fun onActivityRequestedOrientationChanged(taskId: Int, requestedOrientation: Int) {}
    fun onBackPressedOnTaskRoot(taskInfo: ActivityManager.RunningTaskInfo) {}
    fun onTaskDisplayChanged(taskId: Int, newDisplayId: Int) {}
    fun onRecentTaskListUpdated() {}
    fun onRecentTaskListFrozenChanged(frozen: Boolean) {}
    fun onRecentTaskRemovedForAddTask(taskId: Int) {}
    fun onTaskFocusChanged(taskId: Int, focused: Boolean) {}
}

class TaskStackChangeListeners private constructor() {
    fun registerTaskStackListener(listener: TaskStackChangeListener) {}
    fun unregisterTaskStackListener(listener: TaskStackChangeListener) {}

    companion object {
        @JvmStatic
        val instance: TaskStackChangeListeners = TaskStackChangeListeners()
    }
}

abstract class FlingOnBackAnimationCallback : OnBackAnimationCallback {
    open fun onBackStartedCompat(backEvent: BackEvent) {}
    open fun onBackProgressedCompat(backEvent: BackEvent) {}
    open fun onBackInvokedCompat() {}
    open fun onBackCancelledCompat() {}

    override fun onBackStarted(backEvent: BackEvent) = onBackStartedCompat(backEvent)
    override fun onBackProgressed(backEvent: BackEvent) = onBackProgressedCompat(backEvent)
    override fun onBackInvoked() = onBackInvokedCompat()
    override fun onBackCancelled() = onBackCancelledCompat()
}
