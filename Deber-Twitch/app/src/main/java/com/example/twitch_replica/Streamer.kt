package com.example.twitch_replica

import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.os.Parcelable

class Streamer(
    val nombre: String?,
    val descripcion: String?,
    //val foto: ParcelFileDescriptor
) : Parcelable {
    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        //parcel.readFileDescriptor()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(nombre)
        p0?.writeString(descripcion)

    }


    companion object CREATOR : Parcelable.Creator<Streamer> {
        override fun createFromParcel(parcel: Parcel): Streamer {
            return Streamer(parcel)
        }

        override fun newArray(size: Int): Array<Streamer?> {
            return arrayOfNulls(size)
        }
    }
}