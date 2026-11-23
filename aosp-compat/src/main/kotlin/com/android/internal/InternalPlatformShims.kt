package com.android.internal.graphics.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class BackgroundBlurDrawable : Drawable() {
    fun setBlurRadius(blurRadius: Int) {}
    fun setCornerRadius(cornerRadius: Float) {}
    fun setCornerRadius(
        cornerRadiusTL: Float,
        cornerRadiusTR: Float,
        cornerRadiusBL: Float,
        cornerRadiusBR: Float
    ) {}
    fun setColor(color: Int) {}

    override fun draw(canvas: Canvas) {}
    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
}
