package ru.sonya.week3.domain

import ru.sonya.week3.ui.viewModel.DtoCat

data class FunCat(
    val image: String?,
    val title: String?,
    val subtitle: String?
)

fun FunCat.mapToDomain() =
    DtoCat(
        image = image,
        title = title,
        subtitle = subtitle
    )