package com.example.twitch_replica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitch_replica.AdaptadorStreamer.*

class AdaptadorExplorar(
    private val contexto: Explorar,
    private val listaStreamer: List<Streamer>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorExplorar.MyViewHolder>() {
    inner class MyViewHolder (view:View): RecyclerView.ViewHolder(view) {
        val streamerTV: TextView
        val foto: ImageView
        val descripcion: TextView

        init {
            streamerTV = view.findViewById(R.id.tv_streamer)
            foto = view.findViewById(R.id.iv_foto)
            descripcion = view.findViewById(R.id.tv_descripcion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_list, // Definimos la vista de nuestro recycler view
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val streamer = listaStreamer[position]
        holder.streamerTV.text = streamer.nombre
        holder.descripcion.text = streamer.descripcion

    }

    override fun getItemCount(): Int {
        return listaStreamer.size
    }
    }


