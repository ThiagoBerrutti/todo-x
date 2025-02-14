package com.tba.todox.core.model

import androidx.annotation.DrawableRes

data class Tag(
    val label: String,
    val color: Long,
    @DrawableRes val icon: Int,
)
