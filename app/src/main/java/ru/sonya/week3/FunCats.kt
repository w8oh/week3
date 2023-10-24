package ru.sonya.week3

import android.os.Parcel
import android.os.Parcelable

data class FunCats(val image:Int, val title: String?, val subtitle: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(subtitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FunCats> {
        override fun createFromParcel(parcel: Parcel): FunCats {
            return FunCats(parcel)
        }

        override fun newArray(size: Int): Array<FunCats?> {
            return arrayOfNulls(size)
        }
    }
}
