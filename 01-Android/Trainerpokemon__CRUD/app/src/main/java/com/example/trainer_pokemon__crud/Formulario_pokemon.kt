package com.example.trainer_pokemon__crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.*

class Formulario_pokemon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_pokemon)
        val objTrainer = intent.getParcelableExtra<BEntrenador>("entrenador")
        val editar = intent.getStringExtra("editar")
        val pokemon = intent.getParcelableExtra<BPokemon>("pokemon")
        val btnGuardarPokemon = findViewById<Button>(R.id.btn_crearp)
        if (editar=="editar") {
            findViewById<EditText>(R.id.et_nombre_pokemo).setText(pokemon!!.nombre_pokemon)
            findViewById<EditText>(R.id.et_nivel).setText(pokemon!!.nivel.toString())
            findViewById<Button>(R.id.btn_crearp).setText("Editar")

            val botoneditarpokemon = findViewById<Button>(R.id.btn_crearp)
            botoneditarpokemon.setOnClickListener {
                editarPokemon(pokemon!!.id_pokemon.toInt())
                Toast.makeText(this,"se editó ${pokemon.nombre_pokemon}",Toast.LENGTH_SHORT).show()
                irActividadConEntrenador(Pokemon::class.java,objTrainer!!)
            }
        }else{





        btnGuardarPokemon.setOnClickListener {
            if (objTrainer != null) {
                val nuevoPokemon = BPokemon(
                    BDatosMemoria.arregloBPokemon.size+1,
                    findViewById<EditText>(R.id.et_nombre_pokemo).text.toString(),
                    findViewById<EditText>(R.id.cb_shiny).isSelected,
                    findViewById<EditText>(R.id.et_nivel).text.toString().toInt(),
                    objTrainer.id_trainer
                )
                BDatosMemoria.arregloBPokemon.add(nuevoPokemon)
                Toast.makeText(
                    this,
                    "Se creó ${findViewById<EditText>(R.id.et_nombre_pokemo).text}",
                    Toast.LENGTH_SHORT
                ).show()
                irActividadConEntrenador(Pokemon::class.java, objTrainer)
            }
        }
    }}

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConEntrenador(clase: Class<*>, entrenador: BEntrenador) {
        val intent = Intent(this, clase)
        intent.putExtra("entrenador", entrenador)
        startActivity(intent)
    }

    fun editarPokemon(id: Int) {
        var shiny = findViewById<CheckBox>(R.id.cb_shiny).isSelected
        BDatosMemoria.arregloBPokemon.filter { it.id_pokemon == id }
            .map {
                it.nombre_pokemon = findViewById<EditText>(R.id.et_nombre_pokemo).text.toString()
                it.shiny = shiny
                it.nivel = findViewById<EditText>(R.id.et_nivel).text.toString().toInt()
            }
    }
}