package com.robbie.hackathon.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataProfile(
    val name: String,
    val Profession: String,
    val address: String,
) : Parcelable