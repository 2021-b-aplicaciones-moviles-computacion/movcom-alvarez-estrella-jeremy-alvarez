package com.example.trainer_pokemon__crud

import android.os.Parcelable
import android.os.Parcel
import java.text.SimpleDateFormat
import java.util.*

class BEntrenador (
    val id_trainer: Int,
    var nombre_trainer: String?,
    var genero_trainer: String?,
    var dinero: Float,
    var juego_iniciado: Date
): Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        Date(parcel.readString())
    ){}

    override fun toString(): String {
        val fecha  = SimpleDateFormat("dd/MM/yyyy").format(juego_iniciado)
        return "${this.id_trainer}\t${this.nombre_trainer}\t${this.genero_trainer}\t${this.dinero}\t${fecha}"
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(id_trainer)
        p0?.writeString(nombre_trainer)
        p0?.writeString(genero_trainer)
        p0?.writeFloat(dinero)
        p0?.writeString(SimpleDateFormat("dd/MM/yyyy").format(juego_iniciado))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}