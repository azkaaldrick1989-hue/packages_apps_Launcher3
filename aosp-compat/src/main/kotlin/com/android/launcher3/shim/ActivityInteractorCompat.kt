package com.android.launcher3

/**
 * Shim Flags di package com.android.launcher3 untuk memenuhi import:
 * import com.android.launcher3.Flags.enableUnfoldStateAnimation
 */
object Flags {
    @JvmStatic
    fun enableUnfoldStateAnimation(): Boolean = false
}

package com.android.launcher3.util

/**
 * Shim SafeCloseable di package com.android.launcher3.util untuk memenuhi import:
 * import com.android.launcher3.util.SafeCloseable
 */
fun interface SafeCloseable : AutoCloseable {
    override fun close()
}

package com.android.systemui.unfold

/**
 * Shim UnfoldTransitionProgressProvider di package com.android.systemui.unfold untuk memenuhi import:
 * import com.android.systemui.unfold.UnfoldTransitionProgressProvider
 */
interface UnfoldTransitionProgressProvider {
    fun addCallback(listener: Any?) {}
    fun removeCallback(listener: Any?) {}
}
