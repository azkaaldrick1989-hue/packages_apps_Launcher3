package com.android.wm.shell.shared.bubbles

import android.os.Parcel
import android.os.Parcelable

enum class BubbleBarLocation : Parcelable {
    DEFAULT,
    LEFT,
    RIGHT;

    fun isOnLeft(isRtl: Boolean): Boolean = when (this) {
        LEFT -> true
        RIGHT -> false
        DEFAULT -> isRtl
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<BubbleBarLocation> {
        override fun createFromParcel(source: Parcel): BubbleBarLocation =
            valueOf(source.readString() ?: DEFAULT.name)
        override fun newArray(size: Int): Array<BubbleBarLocation?> = arrayOfNulls(size)
    }
}
