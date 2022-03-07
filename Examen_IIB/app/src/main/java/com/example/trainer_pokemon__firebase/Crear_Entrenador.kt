package com.example.trainer_pokemon__firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.hashMapOf as hashMapOf1


class Crear_Entrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)
        val btnCrearE = findViewById<Button>(R.id.btn_crear_E)

        btnCrearE.setOnClickListener {
            val et_id = findViewById<EditText>(R.id.et_id_entrenador)
            val et_nombre = findViewById<EditText>(R.id.et_nombre_entrenador)
            val et_genero = findViewById<EditText>(R.id.et_genero)
            val et_dinero = findViewById<EditText>(R.id.et_dinero)
            val nuevo_entrenador = hashMapOf1<String, Any>(
                "id_trainer" to et_id.text.toString().toInt(),
                "nombre_trainer" to et_nombre.text.toString(),
                "genero_trainer" to et_genero.text.toString(),
                "dinero" to et_dinero.text.toString().toFloat()
            )
            val db = Firebase.firestore
            val referencia = db.collection("Entrenador").document("${et_genero.text}-${et_nombre.text}")
            referencia
                .set(nuevo_entrenador)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.et_nombre_entrenador).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(
                            this,
                            MainActivity::class.java
                        )
                    )
                }
        }
    }
}