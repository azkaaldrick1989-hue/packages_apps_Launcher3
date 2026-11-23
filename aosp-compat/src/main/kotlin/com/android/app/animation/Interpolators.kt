package com.android.app.animation

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator

object Interpolators {
    @JvmField val LINEAR: Interpolator = LinearInterpolator()
    @JvmField val ACCELERATE: Interpolator = AccelerateInterpolator()
    @JvmField val ACCELERATE_1_5: Interpolator = AccelerateInterpolator(1.5f)
    @JvmField val ACCELERATE_2: Interpolator = AccelerateInterpolator(2f)
    @JvmField val DECELERATE: Interpolator = DecelerateInterpolator()
    @JvmField val DECELERATE_1_5: Interpolator = DecelerateInterpolator(1.5f)
    @JvmField val DECELERATE_1_7: Interpolator = DecelerateInterpolator(1.7f)
    @JvmField val DECELERATE_2: Interpolator = DecelerateInterpolator(2f)
    @JvmField val DECELERATE_3: Interpolator = DecelerateInterpolator(3f)
    @JvmField val ACCELERATE_DECELERATE: Interpolator = AccelerateDecelerateInterpolator()
    @JvmField val FAST_OUT_SLOW_IN: Interpolator = PathInterpolator(0.4f, 0f, 0.2f, 1f)
    @JvmField val FAST_OUT_LINEAR_IN: Interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
    @JvmField val LINEAR_OUT_SLOW_IN: Interpolator = PathInterpolator(0f, 0f, 0.2f, 1f)
    @JvmField val EMPHASIZED: Interpolator = PathInterpolator(0.2f, 0f, 0f, 1f)
    @JvmField val EMPHASIZED_ACCELERATE: Interpolator = PathInterpolator(0.3f, 0f, 0.8f, 0.15f)
    @JvmField val EMPHASIZED_DECELERATE: Interpolator = PathInterpolator(0.05f, 0.7f, 0.1f, 1f)
    @JvmField val STANDARD: Interpolator = PathInterpolator(0.2f, 0f, 0f, 1f)
    @JvmField val STANDARD_ACCELERATE: Interpolator = PathInterpolator(0.3f, 0f, 1f, 1f)
    @JvmField val STANDARD_DECELERATE: Interpolator = PathInterpolator(0f, 0f, 0f, 1f)
    @JvmField val TOUCH_RESPONSE: Interpolator = PathInterpolator(0.3f, 0f, 0.1f, 1f)
    @JvmField val AGGRESSIVE_EASE: Interpolator = PathInterpolator(0.2f, 0f, 0f, 1f)
    @JvmField val AGGRESSIVE_EASE_IN_OUT: Interpolator = PathInterpolator(0.6f, 0f, 0.4f, 1f)
    @JvmField val BACK_GESTURE: Interpolator = PathInterpolator(0.1f, 0.1f, 0f, 1f)
    @JvmField val FINAL_FRAME: Interpolator = Interpolator { input -> if (input < 1f) 0f else 1f }
    @JvmField val INSTANT: Interpolator = Interpolator { 1f }

    @JvmStatic
    fun clampToProgress(interpolator: Interpolator, lowerBound: Float, upperBound: Float): Interpolator {
        return Interpolator { t ->
            when {
                t < lowerBound -> 0f
                t > upperBound -> 1f
                else -> interpolator.getInterpolation((t - lowerBound) / (upperBound - lowerBound))
            }
        }
    }

    @JvmStatic
    fun reverse(interpolator: Interpolator): Interpolator {
        return Interpolator { t -> 1f - interpolator.getInterpolation(1f - t) }
    }
}
