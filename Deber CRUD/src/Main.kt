import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

var sc: Scanner = Scanner(System.`in`)
fun main(){
    val listaTrainers = readTrainer("Archivos/entrenador.txt")
    val listaPokemons = readPokemon("Archivos/pokemon.txt")
    var opcion = ""
    var mensaje = "\n----------------BIENVENIDO------------------\n" +
                  "Ingrese a que entidad desea entrar\n"+
                  "1.- Entrenador\t\t\t\t\t2.- Pokemon"

    println(mensaje)
    opcion = sc.nextLine()
    when(opcion){
        "1"->{
            operacion("entrenador",listaTrainers,listaPokemons)

        }
        "2"->{
            operacion("pokemon",listaTrainers,listaPokemons)

        }
    }
    println("Desea realizar otra operación??")
    opcion = sc.nextLine()
    when(opcion){
        "si"->{
            main()
        }
        "no"->{
            System.exit(0)
        }
    }
}

fun operacion(entidad:String,traiL:ArrayList<Trainer>,pokL:ArrayList<Pokemon>){
    val mensaje = "Que operación desea realizar en la entidad "+entidad+":\n"+
                  "1.-Crear\t\t\t2.-Leer\t\t\t3.-Actualizar\t\t\t4.-Eliminar"
    println(mensaje)
    val opcionCRUD = sc.nextLine()
    when(opcionCRUD){
        "1"->{
            crear(entidad)
        }
        "2"->{
            imprimir(entidad,readFile("Archivos/"+entidad+".txt"))
        }
        "3"->{
            update(traiL,pokL,entidad)
        }
        "4"->{
            delete(entidad,traiL,pokL)
        }
    }
}

fun readFile(nombreArchivo: String):ArrayList<ArrayList<String>> {
    val uno = ArrayList<String>()
    val varios = arrayListOf(ArrayList<String>())

    try {
        val myObj = File(nombreArchivo)
        val myReader = Scanner(myObj)
        while (myReader.hasNextLine()) {
            val data: String = myReader.nextLine()
            val st = StringTokenizer(data, ",")
            while (st.hasMoreTokens()) {
                uno.add(st.nextToken())
            }

            varios.add(uno.clone() as ArrayList<String>)
            uno.clear()
        }
        myReader.close()
    } catch (e: FileNotFoundException) {
        println("An error occurred.")
        e.printStackTrace()
    }
    varios.removeAt(0)
    return varios
}

fun imprimir(entidad:String,lista:ArrayList<ArrayList<String>>){
    var tabla = ""
    var i:Int=0
    var j:Int=0
    when(entidad){
        "entrenador"->{
            tabla = "ID\t\t\t\tNombre\t\t\t\tGenero\t\t\t\tDinero\t\t\t\tJuego Iniciado\n"
        }
        "pokemon"->{
            tabla = "ID\t\t\t\tPokemon\t\t\t\tShiny\t\t\t\tlvl\t\t\t\tEntrenador\n"
        }

    }
    i=0
    while(i<lista.size){
        j=0
        while(j<lista[i].size) {
            tabla += lista[i][j] + "\t\t\t\t"
            j++
        }
        i++
        tabla+="\n"
    }
    println(tabla)
}

fun crear( entidad:String ){
    val scanner = Scanner(System.`in`)
    when(entidad){
        "entrenador"->{

            println("Ingrese el id del entrenador:")
            val id_trainer = scanner.nextLine()
            println("Ingrese el Nombre del entrenador:")
            val nombre_trainer = scanner.nextLine()
            println("Ingrese el genero:")
            val genero_trainer = scanner.nextLine()
            println("Ingrese el dinero:")
            val dinero = scanner.nextLine()
            println("Ingrese la fecha del juego iniciado:")
            val juego_iniciado = scanner.nextLine()

            try {
                FileWriter("Archivos/${entidad}.txt", true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("\n"+"${id_trainer},${nombre_trainer},${genero_trainer},${dinero},${juego_iniciado}")
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }

            deleteLine("Archivos/${entidad}.txt","")
            println("Trainer creado con exito")
        }
        "pokemon"->{
            println("Ingrese el id del pokemon:")
            val id = scanner.nextLine()
            println("Ingrese el Nombre del pokemon:")
            val nombre = scanner.nextLine()
            println("Diga si es shiny (si/no):")
            val shiny = scanner.nextLine()
            println("Ingrese el nivel del pokemon:")
            val nivel = scanner.nextLine()
            println("Ingrese el id del trainer:")
            val id_trainer = scanner.nextLine()

            try {
                FileWriter("Archivos/${entidad}.txt", true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("\n"+"${id},${nombre},${shiny},${nivel},${id_trainer}")
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }

            deleteLine("Archivos/${entidad}.txt","")
            println("Pokemon creado con exito")
        }
    }




}

fun deleteLine(file: String?, lineToRemove: String) {
    try {
        val inFile = File(file)
        if (!inFile.isFile) {
            println("Parameter is not an existing file")
            return
        }

        //Construct the new file that will later be renamed to the original filename.
        val tempFile = File(inFile.absolutePath + ".tmp")
        val br = BufferedReader(FileReader(file))
        val pw = PrintWriter(FileWriter(tempFile))
        var line: String? = null

        //Read from the original file and write to the new
        //unless content matches data to be removed.
        while (br.readLine().also { line = it } != null) {
            if (line!!.trim { it <= ' ' } != lineToRemove) {
                pw.println(line)
                pw.flush()
            }
        }
        pw.close()
        br.close()

        //Delete the original file
        if (!inFile.delete()) {
            println("Could not delete file")
            return
        }

        //Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inFile)) println("Could not rename file")
    } catch (ex: FileNotFoundException) {
        ex.printStackTrace()
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
}

fun delete(entidad:String , archivo1: ArrayList<Trainer>,archivo2: ArrayList<Pokemon>){
    val scanner = Scanner(System.`in`)
    when(entidad){
        "entrenador"->{
            var id:String?
            var nombre:String?
            var genero:String?
            var dinero:String?
            var juego_iniciado:String?

            println("Ingrese el Id del trainer a eliminar")
            val opcion =   scanner.nextLine()
            val borrar = findTrainer(opcion,archivo1)
            borrar.forEach {
                id = it.getIdTrainer().toString()
                nombre = it.getnombreTrainer().toString()
                genero = it.getGeneroTrainer().toString()
                dinero = it.getDinero().toString()
                juego_iniciado = it.getJuegoInit().toString()
                deleteLine("Archivos/${entidad}.txt", "${id},${nombre},${genero},${dinero},${juego_iniciado}")
                deleteLine("Archivos/${entidad}.txt", "")
                println("Entrenador eliminado con exito")
            }
        }
        "pokemon"->{
                var id:String?
                var nombre_pokemon:String?
                var shiny:String?
                var nivel:String?
                var trainer:String?

                println("Ingrese el Id del pokemon a eliminar")
                val opcion =   scanner.nextLine()
                val borrar = findPokemon(opcion,archivo2)
                borrar.forEach {
                    id = it.getIdPokemon().toString()
                    nombre_pokemon = it.getNombre_Pokemon().toString()
                    shiny = it.getShiny().toString()
                    nivel = it.getNivel().toString()
                    trainer = it.getTrainer().toString()
                    deleteLine("Archivos/${entidad}.txt","${id},${nombre_pokemon},${shiny},${nivel},${trainer}")
                    deleteLine("Archivos/${entidad}.txt", "")
                    println("Pokemon eliminado con exito")

                }

        }
    }
    }


fun findTrainer( str:String, archivo: ArrayList<Trainer> ): ArrayList<Trainer> {
        return (archivo.filter {
            return@filter (it.getIdTrainer() == str.toInt())
        } as ArrayList<Trainer>)
}

fun findPokemon( str:String, archivo: ArrayList<Pokemon> ): ArrayList<Pokemon> {
    return (archivo.filter {
        return@filter (it.getIdPokemon() == str.toInt())
    } as ArrayList<Pokemon>)
}

fun readTrainer(ruta:String): ArrayList<Trainer> {
    val trainers = ArrayList<Trainer>()
    val entrenador_lista = readFile(ruta)
    entrenador_lista.forEach {
        val date1 = SimpleDateFormat("dd/MM/yyyy").parse(it[4])
        trainers.add(Trainer(it[0].toInt(), it[1].toString(), it[2].toString(), it[3].toFloat(), date1))
    }
    return trainers
}

fun readPokemon(ruta:String): ArrayList<Pokemon> {
    val pokemons = ArrayList<Pokemon>()
    val pokemon_lista = readFile(ruta)
    pokemon_lista.forEach {
        pokemons.add(Pokemon(it[0].toInt(), it[1].toString(), it[2].toBoolean(), it[3].toInt(), it[4].toInt()))
    }
    return pokemons
}

fun update(lista_entrenadores: ArrayList<Trainer>, lista_pokemon: ArrayList<Pokemon>,entidad:String){
    when(entidad){
        "entrenador"->{
            val scanner = Scanner(System.`in`)
            var id:String?
            var nombre:String?
            var genero:String?
            var dinero:String?

            var fecha_init:String?
            print("Ingrese el id del club a actualizar: \n")
            val prueba = scanner.nextLine()
            print("Seleccione el parametro a actualizar: \n" +
                    "1. Nombre\n" +
                    "2. Genero\n" +
                    "3. Dinero\n" +
                    "Opción: ")
            val cambio = scanner.nextLine().toInt()
            print("Ingrese la actualización:\n ")
            val nuevo = scanner.nextLine()
            var antiguo:String ="a"
            if(cambio.toString() == "1") {
                lista_entrenadores.filter { it.getIdTrainer() == prueba.toInt() }.forEach {
                    antiguo = it.getnombreTrainer().toString()
                    it.setNombreTrainer(nuevo.toString())

                }
            }

            if(cambio.toString() == "2") {
                lista_entrenadores.filter { it.getIdTrainer() == prueba.toInt() }.forEach {
                    antiguo = it.getGeneroTrainer().toString()
                    it.setGeneroTrainer(nuevo.toString())

                }
            }

            if(cambio.toString() == "3") {
                lista_entrenadores.filter { it.getIdTrainer() == prueba.toInt() }.forEach {
                    antiguo = it.getDinero().toString()
                    it.setDinero(nuevo.toFloat())

                }
            }

            val borrar = findTrainer(prueba, lista_entrenadores)
            borrar.forEach {
                id = it.getIdTrainer().toString()
                nombre = it.getnombreTrainer()
                genero = it.getGeneroTrainer()
                dinero = it.getDinero().toString()
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                var date2: String? = formatter.format(it.getJuegoInit())
                fecha_init = date2.toString()


                when(cambio.toString()) {
                    "1" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${antiguo},${genero},${dinero},${fecha_init}")
                    }
                    "2" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${nombre},${antiguo},${dinero},${fecha_init}")
                    }

                    "3" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${nombre},${genero},${antiguo},${fecha_init}")
                    }


                }
                try {


                    FileWriter("Archivos/${entidad}.txt", true).use { fw ->
                        BufferedWriter(fw).use { bw ->
                            PrintWriter(bw).use { out ->
                                out.print("\n"+"${id},${nombre},${genero},${dinero},${fecha_init},")
                            }
                        }
                    }
                } catch (e: IOException) {
                    //exception handling left as an exercise for the reader
                }
            }

            deleteLine("Archivos/${entidad}.txt","")
        }
        "pokemon"->{
            val scanner = Scanner(System.`in`)
            var id:String?
            var nombre_pokemon:String?
            var shiny:String?
            var nivel:String?
            var trainer:String?
            print("Ingrese el id del pokemon a actualizar: \n")
            val prueba = scanner.nextLine()
            print("Seleccione el parametro a actualizar: \n" +
                    "1. Nombre del pokemon\n" +
                    "2. Shiny\n" +
                    "3. Nivel\n" +
                    "4. trainer\n" +
                    "Opción: ")
            val cambio = scanner.nextLine().toInt()
            println("Ingrese la actualización:\n ")
            val nuevo = scanner.nextLine()
            var antiguo:String ="a"
            if(cambio.toString() == "1") {
                lista_pokemon.filter { it.getIdPokemon() == prueba.toInt() }.forEach {
                    antiguo = it.getNombre_Pokemon().toString()
                    it.setNombre_Pokemon(nuevo.toString())

                }
            }

            if(cambio.toString() == "2") {
                lista_pokemon.filter { it.getIdPokemon() == prueba.toInt() }.forEach {
                    antiguo = it.getShiny().toString()
                    it.setShiny(nuevo.toBoolean())

                }
            }

            if(cambio.toString() == "3") {
                lista_pokemon.filter { it.getIdPokemon() == prueba.toInt() }.forEach {
                    antiguo = it.getNivel().toString()
                    it.setNivel(nuevo.toInt())
                }

            if(cambio.toString() == "4") {
                lista_pokemon.filter { it.getIdPokemon() == prueba.toInt() }.forEach {
                    antiguo = it.getTrainer().toString()
                    it.setTrainer(nuevo.toInt())
                }
            }

            val borrar = findPokemon(prueba, lista_pokemon)
            borrar.forEach {
                id = it.getIdPokemon().toString()
                nombre_pokemon = it.getNombre_Pokemon()
                shiny = it.getShiny().toString()
                nivel = it.getNivel().toString()
                trainer = it.getTrainer().toString()

                when(cambio.toString()) {
                    "1" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${antiguo},${shiny},${nivel},${trainer},")
                    }
                    "2" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${nombre_pokemon},${antiguo},${nivel},${trainer},")
                    }

                    "3" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${nombre_pokemon},${shiny},${antiguo},${trainer},")
                    }
                    "4" -> {
                        deleteLine("Archivos/${entidad}.txt", "${id},${nombre_pokemon},${shiny},${nivel},${antiguo},")
                    }

                }
                try {


                    FileWriter("Archivos/${entidad}.txt", true).use { fw ->
                        BufferedWriter(fw).use { bw ->
                            PrintWriter(bw).use { out ->
                                out.print("\n"+"${id},${nombre_pokemon},${shiny},${nivel},${trainer},")
                            }
                        }
                    }
                } catch (e: IOException) {
                    //exception handling left as an exercise for the reader
                }
            }

            deleteLine("Archivos/${entidad}.txt","")
        }
    }
}}