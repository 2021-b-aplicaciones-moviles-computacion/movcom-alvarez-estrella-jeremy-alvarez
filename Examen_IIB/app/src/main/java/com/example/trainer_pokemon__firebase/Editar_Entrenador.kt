package com.example.trainer_pokemon__firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Editar_Entrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_entrenador)
        val objEntrenador = intent.getParcelableExtra<Entrenador>("entrenador")

        val id = objEntrenador!!.id_trainer.toString()
        val nombre = objEntrenador!!.nombre_trainer.toString()
        val genero = objEntrenador!!.genero_trainer.toString()
        val dinero = objEntrenador!!.dinero.toString()

        findViewById<EditText>(R.id.et_id_entrenador2).setText(id)
        findViewById<EditText>(R.id.et_nombre_entrenador2).setText(nombre)
        findViewById<EditText>(R.id.et_genero2).setText(genero)
        findViewById<EditText>(R.id.et_dinero2).setText(dinero)

        val btnEditar_E = findViewById<Button>(R.id.btn_editar_E)
        btnEditar_E.setOnClickListener {
            val nuevoEntrenador = hashMapOf<String, Any>(
                "id_trainer" to findViewById<EditText>(R.id.et_id_entrenador2).text.toString().toInt(),
                "nombre_trainer" to findViewById<EditText>(R.id.et_nombre_entrenador2).text.toString(),
                "genero_trainer" to findViewById<EditText>(R.id.et_genero2).text.toString(),
                "dinero" to findViewById<EditText>(R.id.et_dinero2).text.toString().toFloat()
            )
            val db = Firebase.firestore
            val referencia =
                db.collection("Entrenador").document("${id}-${nombre}")
            referencia.update(nuevoEntrenador)
                .addOnSuccessListener {
                    Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
                    irActividad(MainActivity::class.java)
                }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}