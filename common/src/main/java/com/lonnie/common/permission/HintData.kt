package com.lonnie.common.permission

import androidx.annotation.DrawableRes
import java.io.Serializable

data class HintData(
    val hintTitle: String = "", val description: String = "",
    val title: String = "", @DrawableRes val image: Int = -1
) : Serializable