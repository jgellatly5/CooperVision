package com.jordangellatly.coopervision.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chemical(
        val bottleCount: Long = 0,
        val id: Long = 0,
        val casNumber: String? = "",
        val expirationDate: String? = "",
        val locationInLab: String? = "",
        val lotOrderNumber: String? = "",
        val manufacturer: String? = "",
        val materialName: String? = "",
        val receiveDate: String? = "",
        val type: String? = ""
) : Parcelable
