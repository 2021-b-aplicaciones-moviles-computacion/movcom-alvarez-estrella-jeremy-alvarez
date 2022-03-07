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

class Pokemon_Vista : AppCompatActivity() {
    var posicionLista = 0
    var pokemons = arrayListOf<Pokemon>()
    var adaptador: ArrayAdapter<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_vista)

        val objEntrenador = intent.getParcelableExtra<Entrenador>("entrenador")

        val db = Firebase.firestore
        var docPokemon: (MutableList<DocumentSnapshot>)
        val pokes = db.collection("Pokemon").orderBy("id_pokemon").whereEqualTo("trainer", objEntrenador?.id_trainer.toString().toInt())
        pokes.get().addOnSuccessListener {
            docPokemon = it.documents
            docPokemon.forEach { iteracion ->
                pokemons.add(
                    Pokemon(
                        iteracion.get("id_pokemon").toString().toInt(),
                        iteracion.get("nombre_pokemon").toString(),
                        iteracion.get("nivel").toString().toInt(),
                        iteracion.get("trainer").toString().toInt(),
                    )
                )
            }
            if (pokemons.size > 0) {
                val listV_pokemon = findViewById<ListView>(R.id.lv_pokemons)
                adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    pokemons
                )
                listV_pokemon.adapter = adaptador
                registerForContextMenu(listV_pokemon)
                adaptador!!.notifyDataSetChanged()
            }
        }

        val btnCrearPokemon = findViewById<Button>(R.id.btn_nuevoP)
        btnCrearPokemon.setOnClickListener {
            if (objEntrenador != null) {
                    irActividadConEntrenador(Crear_Pokemon::class.java, objEntrenador)
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
        inflater.inflate(R.menu.menu_pokemons, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objPokemon = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.item_editarP -> {
                if (objPokemon != null) {
                    irActividadConPokemon(Editar_Pokemon::class.java, objPokemon)
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

    fun irActividadConPokemon(clase: Class<*>, pokemon: Pokemon) {
        val intent = Intent(this, clase)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }
}
