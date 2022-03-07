package com.example.trainer_pokemon__firebase


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Editar_Pokemon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pokemon)
        val objPokemon = intent.getParcelableExtra<Pokemon>("pokemon")

        val idP = objPokemon!!.id_pokemon.toString()
        val nombreP = objPokemon!!.nombre_pokemon.toString()
        val nivel = objPokemon!!.nivel.toString()
        val idE = objPokemon!!.trainer.toString()

        findViewById<EditText>(R.id.et_idP2).setText(idP)
        findViewById<EditText>(R.id.et_nombreP2).setText(nombreP)
        findViewById<EditText>(R.id.et_nivelP2).setText(nivel)
        findViewById<Switch>(R.id.et_nombre_entrenador2).setText(idE)

        val btn_editarP = findViewById<Button>(R.id.btn_editarP)
        btn_editarP.setOnClickListener {
            val nuevoPokemon = hashMapOf<String, Any>(
                "id_pokemon" to findViewById<EditText>(R.id.et_idP2).text.toString().toInt(),
                "nombre_pokemon" to findViewById<EditText>(R.id.et_nombreP2).text.toString(),
                "nivel" to findViewById<EditText>(R.id.et_nivelP2).text.toString().toInt(),
                "trainer" to findViewById<EditText>(R.id.et_entrenador2).text.toString().toInt(),

            )
            val db = Firebase.firestore
            val referencia = db.collection("Pokemon").document("${idP}-${nombreP}")
            referencia.update(nuevoPokemon).addOnSuccessListener {
                Toast.makeText(this, "Se editó ${nombreP}", Toast.LENGTH_SHORT).show()
                irActividad(MainActivity::class.java)
            }

        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}