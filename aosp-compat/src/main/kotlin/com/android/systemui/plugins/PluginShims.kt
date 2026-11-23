package com.android.systemui.plugins

import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Drawable

interface Plugin {
    fun getVersion(): Int = -1
    fun onCreate(sysuiContext: Context, pluginContext: Context) {}
    fun onDestroy() {}
}

interface PluginListener<T : Plugin> {
    fun onPluginConnected(plugin: T, pluginContext: Context)
    fun onPluginDisconnected(plugin: T) {}
    fun onPluginLoaded(plugin: T, pluginContext: Context, manager: PluginLifecycleManager<T>) {
        onPluginConnected(plugin, pluginContext)
    }
    fun onPluginUnloaded(plugin: T, manager: PluginLifecycleManager<T>) {
        onPluginDisconnected(plugin)
    }
}

interface PluginLifecycleManager<T : Plugin> {
    val componentName: ComponentName
    val `package`: String
    val plugin: T?
    fun loadPlugin()
    fun unloadPlugin()
}

interface PluginEnabler {
    fun setEnabled(component: ComponentName)
    fun setDisabled(component: ComponentName, reason: Int)
    fun isEnabled(component: ComponentName): Boolean
    fun getDisableReason(component: ComponentName): Int

    companion object {
        const val ENABLED = 0
        const val DISABLED_MANUALLY = 1
        const val DISABLED_INVALID_VERSION = 2
        const val DISABLED_FROM_EXPLICIT_CRASH = 3
        const val DISABLED_FROM_SYSTEM_CRASH = 4
        const val DISABLED_UNKNOWN = 100
    }
}

interface ResourceProvider : Plugin {
    fun getInt(resId: Int): Int
    fun getFloat(resId: Int): Float
    fun getDimen(resId: Int): Float
    fun getDimenPixelSize(resId: Int): Int
    fun getDimenPixelOffset(resId: Int): Int
    fun getColor(resId: Int): Int
    fun getFraction(resId: Int, base: Int, pbase: Int): Float

    companion object {
        const val ACTION = "com.android.systemui.action.PLUGIN_RESOURCE_PROVIDER"
        const val VERSION = 1
    }
}

interface IconProcessorPlugin : Plugin {
    fun processIcon(icon: Drawable, packageName: String): Drawable = icon

    companion object {
        const val ACTION = "com.android.launcher3.action.PLUGIN_ICON_PROCESSOR"
        const val VERSION = 1
    }
}
