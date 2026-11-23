package com.android.launcher3.icons.cache

import android.content.Context
import android.graphics.Bitmap
import com.android.launcher3.util.ComponentKey

open class BaseIconCache(
    val context: Context,
    val dbFileName: String?,
    val bgLooper: Any?,
    val iconDpi: Int,
    val iconPixelSize: Int,
    val inMemoryCache: Boolean
) {
    open fun close() {}
    open fun clear() {}
}

class IconCacheUpdateHandler
