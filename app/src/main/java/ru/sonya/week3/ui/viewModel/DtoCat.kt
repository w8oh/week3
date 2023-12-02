package ru.sonya.week3.ui.viewModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.sonya.week3.domain.FunCat
import ru.sonya.week3.ui.view.ItemCat

@Parcelize
data class DtoCat(
    val image: String?,
    val title: String?,
    val subtitle: String?
) : Parcelable

fun DtoCat.mapToView() =
    ItemCat(
        image = image,
        title = title,
        subtitle = subtitle
    )

fun ItemCat.mapToViewModal() =
    DtoCat(
        image = image,
        title = title,
        subtitle = subtitle
    )

