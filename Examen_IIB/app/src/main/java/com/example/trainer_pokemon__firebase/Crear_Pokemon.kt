package com.example.trainer_pokemon__firebase


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Crear_Pokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pokemon)

        val objEntrenador = intent.getParcelableExtra<Entrenador>("entrenador")
        val btnCrearpokemon = findViewById<Button>(R.id.btnCrearP)
        btnCrearpokemon.setOnClickListener {

            if (objEntrenador != null) {

                val et_idP = findViewById<EditText>(R.id.et_idP)
                val et_nombreP = findViewById<EditText>(R.id.et_nombreP)
                val et_nivel = findViewById<EditText>(R.id.et_nivelP)
                val et_entrenador = findViewById<EditText>(R.id.et_entrenador)
                val nuevoPokemon = hashMapOf<String, Any>(
                    "id_pokemon" to et_idP.text.toString().toInt(),
                    "nombre_pokemon" to et_nombreP.text.toString(),
                    "nivel" to et_nivel.text.toString(),
                    "trainer" to et_entrenador.text.toString().toInt(),
                )
                val db = Firebase.firestore
                var referencia =
                    db.collection("Pokemon").document("${et_idP.text}-${et_nombreP.text}")
                referencia.set(nuevoPokemon).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.et_nombreP).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    irActividadConEntrenador(Pokemon_Vista::class.java, objEntrenador)
                }


            }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConEntrenador(clase: Class<*>, entrenador: Entrenador) {
        val intent = Intent(this, clase)
        intent.putExtra("entrenador", entrenador)
        startActivity(intent)
    }
}