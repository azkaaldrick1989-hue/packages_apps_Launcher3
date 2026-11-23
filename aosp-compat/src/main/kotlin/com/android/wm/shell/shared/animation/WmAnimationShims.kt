package com.android.wm.shell.shared.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PointF
import android.os.Handler
import android.view.SurfaceControl
import android.window.TransitionInfo

object WindowAnimator {
    @JvmStatic
    fun createBoundsAnimator(
        displayMetrics: Any?,
        boundsAnimParams: BoundsAnimParams,
        change: TransitionInfo.Change,
        transaction: SurfaceControl.Transaction
    ): ValueAnimator = ValueAnimator.ofFloat(0f, 1f)

    data class BoundsAnimParams(
        val durationMs: Long = 300L,
        val startScale: Float = 1f,
        val endScale: Float = 1f,
        val startRadius: Float = 0f,
        val endRadius: Float = 0f,
        val interpolator: Any? = null
    )
}

object MinimizeAnimator {
    @JvmStatic
    fun create(
        context: Context,
        change: TransitionInfo.Change,
        transaction: SurfaceControl.Transaction,
        onAnimationEnd: (Animator) -> Unit,
        interpolator: Any?,
        animationHandler: Handler
    ): Animator = ValueAnimator.ofFloat(0f, 1f)
}
