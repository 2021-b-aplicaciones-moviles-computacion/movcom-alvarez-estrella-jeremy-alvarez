package com.example.trainer_pokemon__crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class Formulario_trainer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_trainer)
        val btnGuardarTrainer = findViewById<Button>(R.id.btn_crear_t)

        val editar = intent.getStringExtra("editar")
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        if (editar=="editar"){
            findViewById<EditText>(R.id.et_Nombre_trainer).setText(entrenador!!.nombre_trainer)
            findViewById<EditText>(R.id.et_dinero).setText(entrenador!!.dinero.toString())
            findViewById<EditText>(R.id.etd_fecha_inicio).setText(entrenador!!.juego_iniciado.toString())
            findViewById<Button>(R.id.btn_crear_t).setText("Editar")

            val botonEditartrainer = findViewById<Button>(R.id.btn_crear_t)
            botonEditartrainer.setOnClickListener {
                editarEntrenador(entrenador!!.id_trainer.toInt())
                Toast.makeText(this, "Se editó ${entrenador!!.nombre_trainer}", Toast.LENGTH_SHORT).show()
                irActividad(Trainer::class.java)
            }
        }else{

        btnGuardarTrainer.setOnClickListener {
            var genero: String
            if(findViewById<CheckBox>(R.id.cb_generoF).isSelected)
                genero = "F"
            else(findViewById<CheckBox>(R.id.cb_generoM).isSelected)
            genero = "M"
            val nuevoTrainer = BEntrenador(
                BDatosMemoria.arregloBEntrenador.size+1,
                findViewById<EditText>(R.id.et_Nombre_trainer).text.toString(),
                genero,
                findViewById<EditText>(R.id.et_dinero).text.toString().toFloat(),
                Date(findViewById<EditText>(R.id.etd_fecha_inicio).text.toString())
            )
            BDatosMemoria.arregloBEntrenador.add(nuevoTrainer)
            Toast.makeText(
                this,
                "Se creó ${findViewById<EditText>(R.id.et_Nombre_trainer).text}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(
                    this,
                    Trainer::class.java
                )
            )
        }
    }


}
    fun editarEntrenador(id: Int) {
        var genero: String
        if(findViewById<CheckBox>(R.id.cb_generoF).isSelected)
            genero = "F"
        else(findViewById<CheckBox>(R.id.cb_generoM).isSelected)
        genero = "M"
        BDatosMemoria.arregloBEntrenador.filter { it.id_trainer == id }
            .map {
                it.nombre_trainer = findViewById<EditText>(R.id.et_Nombre_trainer).text.toString()
                it.genero_trainer = genero
                it.dinero = findViewById<EditText>(R.id.et_dinero).text.toString().toFloat()
                it.juego_iniciado = Date(findViewById<EditText>(R.id.etd_fecha_inicio).text.toString())
            }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}