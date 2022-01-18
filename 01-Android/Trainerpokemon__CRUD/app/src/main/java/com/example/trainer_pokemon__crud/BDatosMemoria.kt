package com.example.trainer_pokemon__crud

import java.util.*

class BDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        val arregloBPokemon = arrayListOf<BPokemon>()
        init {
            var fecha: String = "20/07/2000"
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Jeremy","M",1.5f,Date(fecha))
                )
            fecha = "19/02/2004"
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Ash","M",15000f,Date(fecha))
                )
            fecha = "02/06/2020"
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Misty","F",2000f,Date(fecha))
                )


            arregloBPokemon
                .add(
                    BPokemon(1, "Pikachu",false,24,2)
                )
            arregloBPokemon
                .add(
                    BPokemon(2, "Geodude",false,52,1)
                )
            arregloBPokemon
                .add(
                    BPokemon(3, "Noctowl",true,16,2)
                )
            arregloBPokemon
                .add(
                    BPokemon(4, "Magykarp",true,16,3)
                )
            arregloBPokemon
                .add(
                    BPokemon(5, "Lapras",true,16,1)
                )
            arregloBPokemon
                .add(
                    BPokemon(6, "Dragonite",true,16,3)
                )
        }
    }


}