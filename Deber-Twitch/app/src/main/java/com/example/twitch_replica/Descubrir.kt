package com.example.twitch_replica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class Descubrir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descubrir)

        val boton_siguiendo = findViewById<ImageButton>(R.id.btn_siguiendo3)
        boton_siguiendo.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val boton_descubrir = findViewById<ImageButton>(R.id.btn_descubrir3)
        boton_descubrir.setOnClickListener {
            irActividad(Descubrir::class.java)
        }

        val boton_explorar = findViewById<ImageButton>(R.id.btn_explorar3)
        boton_explorar.setOnClickListener {
            irActividad(Explorar::class.java)
        }

        val listaStreamer = arrayListOf<Streamer>()
        listaStreamer
            .add(
                Streamer(
                    "Jeremy",
                    "Rocket League"
                )
            )
        listaStreamer
            .add(
                Streamer(
                    "projere99",
                    "Call of duty"
                )
            )

        listaStreamer
            .add(
                Streamer(
                    "añañin",
                    "Fortnite"
                )
            )

        listaStreamer
            .add(
                Streamer(
                    "deoxys",
                    "Pokemon"
                )
            )
        val recyclerViewStreamer = findViewById<RecyclerView>(
            R.id.rv_recomendados
        )
        inicializarRecyclerView(
            listaStreamer,
            this,
            recyclerViewStreamer
        )
    }
    fun inicializarRecyclerView(
        lista: List<Streamer>,
        actividad: Descubrir,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorRecomendado(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }


    fun irActividad(
        clase: Class<*>,
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}