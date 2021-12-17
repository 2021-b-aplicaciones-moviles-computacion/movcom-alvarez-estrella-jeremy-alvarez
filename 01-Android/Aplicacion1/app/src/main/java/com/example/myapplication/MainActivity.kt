package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                val data = result.data
                Log.i("intent","${data?.getStringExtra("nombreModificado")}")
                Log.i("intent","${data?.getIntExtra("edadModificado",0)}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCicloVIda = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVIda
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(AlistView::class.java)
            }
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this,clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("nombre","Jeremy")
        intentExplicito.putExtra("apellido","Alvarez")
        intentExplicito.putExtra("edad","22")
        intentExplicito.putExtra("A",BEntrenador("a","b"))
        resultLauncher.launch(intentExplicito)

        startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK -> {
                    //Ejecutar codigo OK
                }
            }
        }
    }
}