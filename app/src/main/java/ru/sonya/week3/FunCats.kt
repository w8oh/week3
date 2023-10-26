package ru.sonya.week3

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class FunCats(
    val image: Int,
    val title: String?,
    val subtitle: String?
) : Parcelable
