package com.android.launcher3.util

import android.content.ComponentName
import android.os.Process
import android.os.UserHandle
import java.util.Objects

open class ComponentKey(
    @JvmField val componentName: ComponentName,
    @JvmField val user: UserHandle
) {
    private val mHashCode: Int = Objects.hash(componentName, user)

    constructor(componentName: ComponentName, userId: Int) : this(
        componentName,
        UserHandle.getUserHandleForUid(userId * 100000)
    )

    override fun hashCode(): Int = mHashCode

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComponentKey) return false
        return componentName == other.componentName && user == other.user
    }

    override fun toString(): String = "$componentName#${user.hashCode()}"
}
