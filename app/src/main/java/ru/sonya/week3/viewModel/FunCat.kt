package ru.sonya.week3.viewModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FunCat(
    val image: String?,
    val title: String?,
    val subtitle: String?
) : Parcelable
