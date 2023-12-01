package ru.sonya.week3.ui.view

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import ru.sonya.week3.R

fun Toolbar.addBackButton(clickListener: (() -> Unit)) {
    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
    navigationIcon = drawable
    setNavigationOnClickListener { clickListener.invoke() }
}