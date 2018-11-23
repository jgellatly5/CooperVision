package com.jordangellatly.coopervision.models

import android.os.Parcelable
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Chemicals(
    val bottleCount: Long = 0,
    val id: Long = 0,
    val casNumber: String = "",
    val expirationDate: String = "",
    val locationInLab: String = "",
    val lotOrderNumber: String= "",
    val manufacturer: String = "",
    val materialName: String = "",
    val receiveDate: String = "",
    val type: String = ""
) : PaperParcelable {
    constructor(parcel: android.os.Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeLong(bottleCount)
        parcel.writeLong(id)
        parcel.writeString(casNumber)
        parcel.writeString(expirationDate)
        parcel.writeString(locationInLab)
        parcel.writeString(lotOrderNumber)
        parcel.writeString(manufacturer)
        parcel.writeString(materialName)
        parcel.writeString(receiveDate)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Chemicals> {
        override fun createFromParcel(parcel: android.os.Parcel): Chemicals {
            return Chemicals(parcel)
        }

        override fun newArray(size: Int): Array<Chemicals?> {
            return arrayOfNulls(size)
        }
    }
}
