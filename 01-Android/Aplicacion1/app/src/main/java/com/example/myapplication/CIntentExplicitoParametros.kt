package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("name")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)
        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        Log.i("intent","Valores: ${nombre} ${apellido} ${edad}")
        val boton = findViewById<Button>(R.id.btn_devolverRespuesta)
        boton
            .setOnClickListener { devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevoverParams = Intent()
        intentDevoverParams.putExtra("nombreModificado","David")
        intentDevoverParams.putExtra("edadModificado","24")
        setResult(
            RESULT_OK,
            intentDevoverParams
        )
        this.finish()
    }
}