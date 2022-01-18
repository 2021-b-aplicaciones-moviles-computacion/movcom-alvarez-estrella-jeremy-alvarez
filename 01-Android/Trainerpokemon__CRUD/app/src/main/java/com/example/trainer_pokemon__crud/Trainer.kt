package com.example.trainer_pokemon__crud

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class Trainer : AppCompatActivity() {
    var posicionLista = 0
    var lista = BDatosMemoria.arregloBEntrenador
    var adaptador: ArrayAdapter<BEntrenador>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)
        val boton_crear = findViewById<Button>(R.id.btn_crear_trainer)
        boton_crear
            .setOnClickListener {
                irActividad(Formulario_trainer::class.java)
            }

        val trainer_lview = findViewById<ListView>(R.id.lv_trainer)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        trainer_lview.adapter = adaptador
        registerForContextMenu(trainer_lview)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objEntrenador = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.menu_editar -> {
                if (objEntrenador != null) {
                    ireditar(Formulario_trainer::class.java, "editar",objEntrenador)
                }
                return true
            }
            R.id.menu_eliminar -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Esta seguro")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objEntrenador != null) {
                            eliminarEntrenador(objEntrenador)
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
                return true
            }
            R.id.menu_ver_pokemon -> {
                if (objEntrenador != null) {
                    irActividadEntrenador(Pokemon::class.java, objEntrenador)
                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }


    fun irActividadEntrenador(clase: Class<*>, entrenador: BEntrenador) {
        val intent = Intent(this, clase)
        intent.putExtra("entrenador", entrenador)
        startActivity(intent)
    }

    fun eliminarEntrenador(entrenador: BEntrenador) {
        lista.removeIf { trainers -> (trainers == entrenador) }
    }



    fun ireditar(clase: Class<*>, editar: String, entrenador: BEntrenador) {
        val intent = Intent(this, clase)
        intent.putExtra("editar", editar)
        intent.putExtra("entrenador", entrenador)
        startActivity(intent)
    }

    fun irActividad(
        clase: Class<*>,
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}