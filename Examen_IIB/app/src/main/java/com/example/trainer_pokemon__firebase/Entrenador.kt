package com.example.trainer_pokemon__firebase

import android.os.Parcelable
import android.os.Parcel


class Entrenador (
    val id_trainer: Int,
    var nombre_trainer: String?,
    var genero_trainer: String?,
    var dinero: Float,

): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
    ){}

    override fun toString(): String {

        return "${this.id_trainer}\t${this.nombre_trainer}\t${this.genero_trainer}\t${this.dinero}"
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(id_trainer)
        p0?.writeString(nombre_trainer)
        p0?.writeString(genero_trainer)
        p0?.writeFloat(dinero)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entrenador> {
        override fun createFromParcel(parcel: Parcel): Entrenador {
            return Entrenador(parcel)
        }

        override fun newArray(size: Int): Array<Entrenador?> {
            return arrayOfNulls(size)
        }
    }
}