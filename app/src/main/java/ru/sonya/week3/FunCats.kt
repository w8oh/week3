package ru.sonya.week3

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class FunCats(val image:Int, val title: String?, val subtitle: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    private companion object : Parceler<FunCats> {
        override fun FunCats.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(image)
            parcel.writeString(title)
            parcel.writeString(subtitle)
        }

        override fun create(parcel: Parcel): FunCats {
            return FunCats(parcel)
        }
    }

}
