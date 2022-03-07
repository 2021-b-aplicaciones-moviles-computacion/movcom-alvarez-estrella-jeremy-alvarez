package com.example.trainer_pokemon__firebase


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var posicionLista = 0
    var entrenadores = arrayListOf<Entrenador>()
    var adaptador: ArrayAdapter<Entrenador>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCrearEntrenador = findViewById<Button>(R.id.btn_crear_entrenador)
        btnCrearEntrenador.setOnClickListener { irActividad(Crear_Entrenador::class.java) }

        val db = Firebase.firestore
        var docEntrenador: (MutableList<DocumentSnapshot>)
        val trainers = db.collection("Entrenador").orderBy("id_trainer")
        trainers.get().addOnSuccessListener {
            docEntrenador = it.documents
            docEntrenador.forEach { iteracion ->
                entrenadores.add(
                    Entrenador(
                        iteracion.get("id_trainer").toString().toInt(),
                        iteracion.get("nombre_trainer").toString(),
                        iteracion.get("genero_trainer").toString(),
                        iteracion.get("dinero").toString().toFloat()
                    )
                )
            }
            if (entrenadores.size > 0) {
                val lv_entrenadores = findViewById<ListView>(R.id.lv_entrenadores)
                adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, entrenadores)
                lv_entrenadores.adapter = adaptador
                registerForContextMenu(lv_entrenadores)
                adaptador!!.notifyDataSetChanged()
            }
        }.addOnFailureListener { }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_entrenadores, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objEntrenador = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.item_editar_e -> {
                if (objEntrenador != null) {
                    irActividadConEntrenador(Editar_Entrenador::class.java, objEntrenador)
                }
                return true
            }
            R.id.item_verP_e -> {
                if (objEntrenador != null) {
                    irActividadConEntrenador(Pokemon_Vista::class.java, objEntrenador)

                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
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