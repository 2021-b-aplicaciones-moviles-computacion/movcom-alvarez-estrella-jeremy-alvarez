package com.example.trainer_pokemon__crud

import android.os.Parcel
import android.os.Parcelable

class BPokemon (
    val id_pokemon: Int,
    var nombre_pokemon: String?,
    var shiny: Boolean,
    var nivel: Int = 1,
    var trainer: Int
):Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readBoolean(),
        parcel.readInt(),
        parcel.readInt()
    ){}

    override fun toString(): String {
        return "${this.id_pokemon}\t${this.nombre_pokemon}\t${this.shiny}\t${this.nivel}\t${this.trainer}"
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(id_pokemon)
        p0?.writeString(nombre_pokemon)
        p0?.writeBoolean(shiny)
        p0?.writeInt(nivel)
        p0?.writeInt(trainer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPokemon> {
        override fun createFromParcel(parcel: Parcel): BPokemon {
            return BPokemon(parcel)
        }

        override fun newArray(size: Int): Array<BPokemon?> {
            return arrayOfNulls(size)
        }
    }
}