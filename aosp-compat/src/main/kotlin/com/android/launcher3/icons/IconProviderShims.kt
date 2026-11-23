package com.android.launcher3.icons

import android.content.ComponentName
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.ApplicationInfo
import android.content.pm.LauncherActivityInfo
import android.content.pm.PackageItemInfo
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.UserHandle
import com.android.launcher3.util.ComponentKey

interface CachedObject {
    fun getComponent(): ComponentName
    fun getUser(): UserHandle
    fun getLabel(): CharSequence
    fun getFullResIcon(cache: Any?): Drawable? = null
}

open class IconProvider(protected val mContext: Context) {
    constructor(context: Context, supportsIconTheme: Boolean) : this(context)

    open fun getIcon(info: ActivityInfo): Drawable = info.loadIcon(mContext.packageManager)
    open fun getIcon(info: ActivityInfo, iconDpi: Int): Drawable = info.loadIcon(mContext.packageManager)
    open fun getIcon(info: LauncherActivityInfo, iconDpi: Int): Drawable = info.getIcon(iconDpi)
    open fun getIcon(info: ApplicationInfo): Drawable = info.loadIcon(mContext.packageManager)
    open fun getIcon(info: PackageItemInfo, appInfo: ApplicationInfo, iconDpi: Int): Drawable =
        info.loadIcon(mContext.packageManager)

    open fun getSystemStateForPackage(systemState: String, packageName: String): String = systemState
    open fun getSystemIconState(): String = ""
    open fun registerIconChangeListener(
        callback: IconChangeListener,
        handler: Handler
    ): AutoCloseable = AutoCloseable { }

    fun interface IconChangeListener {
        fun onAppIconChanged(packageName: String, user: UserHandle)
        fun onSystemIconStateChanged(iconState: String) {}
    }

    companion object {
        @JvmField val CONFIG_ICON_MASK_RES_ID: Int = Resources.getSystem()
            .getIdentifier("config_icon_mask", "string", "android")
    }
}
