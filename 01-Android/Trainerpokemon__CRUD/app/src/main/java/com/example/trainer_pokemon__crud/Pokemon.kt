package com.example.trainer_pokemon__crud

import android.app.AlertDialog
import android.content.DialogInterface
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

class Pokemon : AppCompatActivity() {
    var posicionLista = 0
    var lista = BDatosMemoria.arregloBPokemon
    var adaptador: ArrayAdapter<BPokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val objEntrenador = intent.getParcelableExtra<BEntrenador>("entrenador")
        val listV_pokemon = findViewById<ListView>(R.id.lv_pokemon)

        if (objEntrenador != null) {
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                lista.filter { it.trainer == objEntrenador.id_trainer })
        }
        listV_pokemon.adapter = adaptador
        registerForContextMenu(listV_pokemon)
        adaptador!!.notifyDataSetChanged()

        val btnIrCrearPokemon = findViewById<Button>(R.id.btn_crear_pokemon)
        btnIrCrearPokemon.setOnClickListener {
            if (objEntrenador != null) {
                irActividadEntrenador(Formulario_pokemon::class.java, objEntrenador)
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_p, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objPokemon = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.m_editarP -> {
                if (objPokemon != null) {
                    ireditar(Formulario_pokemon::class.java, "editar",objPokemon)
                }
                return true
            }
            R.id.m_eliminarP -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Esta seguro")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objPokemon != null) {
                            eliminarPokemon(objPokemon)
                            adaptador!!.remove(adaptador!!.getItem(posicionLista))
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
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

    fun irActividadEntrenador(clase: Class<*>, entrenador: BEntrenador) {
        val intent = Intent(this, clase)
        intent.putExtra("entrenador", entrenador)
        startActivity(intent)
    }

    fun irActividadPokemon(clase: Class<*>, pokemon: BPokemon) {
        val intent = Intent(this, clase)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    fun ireditar(clase: Class<*>, editar: String, pokemon: BPokemon) {
        val intent = Intent(this, clase)
        intent.putExtra("editar", editar)
        intent.putExtra("pokemon", pokemon)

        startActivity(intent)
    }

    fun eliminarPokemon(pokemon: BPokemon) {
        lista.removeIf { pokemons -> (pokemons == pokemon) }
    }
}