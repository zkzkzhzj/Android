package com.example.recyclerviewapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// data model(뼈대)
data class Affirmation(
    @StringRes
    val stringResourceId: Int,
    @DrawableRes
    val imageResourceId: Int
)
