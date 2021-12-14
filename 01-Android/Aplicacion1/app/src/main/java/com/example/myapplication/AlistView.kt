package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class AlistView : AppCompatActivity() {
    var idItemSelected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo: ArrayList<Int> = arrayListOf(1,2,3,4,5)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,//como se a ver XML
            arreglo //arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)

        botonAnadirListView
            .setOnClickListener{anadirItem(adaptador,arreglo,1)}

        /*listView
            .setOnItemLongClickListener{parent,view,position,id ->
                Log.i("list-view", "LONG CLICK ${arreglo[position]}")
                return@setOnItemLongClickListener true
            }*/

        //1)Registrar menu contextual
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSelected = id
        Log.i("context-menu","Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar -> {
                Log.i("context-menu","Editar Posicion: ${idItemSelected}")
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("conetxt-menu","Eliminar Posicion ${idItemSelected}")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Titulo")
        //builder.setMessage("Descripcion")

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true,false,false
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia
        ){dialog,
        which,
        isChecked ->
            Log.i("dialogo","Dio click en el item ${which}")
        }



        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{dialog, which ->
                Log.i("dialogo","Hola =D")
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }

    fun anadirItem(
        adaptador:ArrayAdapter<Int>,
        arreglo: ArrayList<Int>,
        valor: Int
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }

}