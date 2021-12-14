import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo");
    //Tipo nombre = valor;
    //Int edad = 12;

    //Tipos de variables

    //INMUTABLES (val)

    val inmutable: String = "Jeremy";
    //inmutable = "a"; -->X

    //MUTABLES (var)
    var mutable: String = "David";
    mutable = "Jeremy";

    //val > var

    //Sintaxis y Duck Typing
    val ejemploVariable = "Nombre"
    var edadEjemplo = 12
    //edadEjemplo = 12.2
    val fechaNacimiento: Date = Date();

    //Condicionales
    if (true) {
        //Verdadero
    } else {
        //Falso
    }

    //switch Estado -> S ->C -> :::
    val estadoCivilWhen: String = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse");
        }
        "C " -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido");
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"
    calcularSueldo(100.00, 14.00, 25.00)
    //Named Parameters}
    calcularSueldo(
        bonoEspecial = 15.00,
        //tasa = 12.00
        sueldo = 150.00
    )
    calcularSueldo(
        bonoEspecial = 14.00,
        tasa = 30.00,
        sueldo = 1000.00
    )

    //Tipos de Arreglos
    //ESTATICO
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    //DINAMICO
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5)
    println(arregloDinamico)
    arregloDinamico.add(11)
    println(arregloDinamico)

    //FOR EACH
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual${valorActual} ")
        }
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> cambia el arreglo
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100
        }
    println(respuestaMap)

    val respuestaMap2 = arregloDinamico.map { it + 15 }

    //FILTER
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual>5 //Expresion Condicion
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter{it<=5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY(Alguno cumple?)
    //AND -> ALL(Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual >5)
        }
    println(respuestaAny)//true

    val respuestaAll: Boolean = arregloDinamico
        .all{valorActual: Int ->
            return@all (valorActual >5)
        }
    println(respuestaAll)//false

    //REDUCE -> valor acumulado
    //Valor acumulado = 0 -> siempre 0 en kotlin
    //valorIteracion1 = valorEmpieza +1 = 0+1 = 1
    //Iteracion 2 = iteracion1 +2 = 1 + 2 = 3

    val respuestaReduce: Int =arregloDinamico
        .reduce{//acumulable = 0 -> SIEMPRE EMPIEZA  EN 0
            acumulado: Int, valorActual: Int ->
            return@reduce(acumulado+valorActual)//logica Negocio
        }
    println(respuestaReduce)//78

    val arregloDanio = arrayListOf<Int>(15,15,8,10)
    val respuestaReduceFold = arregloDanio
        .fold( 100,
            {
                acumulado, valorActualIteracion ->
                return@fold acumulado -valorActualIteracion
            }
        )
    println(respuestaReduceFold)

    val vidaActual: Double = arregloDinamico
        .map {it * 2.3} //arreglo
        .filter { it> 20 }
        .fold(100.00,{acc, i -> acc + i})//valor
        .also{println(it)}
    println("Valor vida actual ${vidaActual}")

    //CLASES
    val ejemplo1 = Suma(1,2)
    val ejemplo2 = Suma(null,2)
    val ejemplo3 = Suma(1,null)
    val ejemplo4 = Suma(null,null)

    println(ejemplo1.sumar())
    println(Suma.historialSuma)
    println(ejemplo2.sumar())
    println(Suma.historialSuma)
    println(ejemplo3.sumar())
    println(Suma.historialSuma)
    println(ejemplo4.sumar())
    println(Suma.historialSuma)


}





fun imprimirNombre(nombre: String){
    println("Nombre: ${nombre}")
}

fun calcularSueldo (
    sueldo: Double, //requerido
    tasa: Double = 12.00, //opcional(Defecto)
    bonoEspecial: Double? = null //Opcional (Null) -> nullable
): Double{
    //String -> String
    //Int -> Int?
    //Date -> Date?
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}


abstract class NumerosJava{
    protected val numerouNO: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int
    ){
        this.numerouNO = uno
        numeroDos = dos
        println("inicializar")
    }
}

abstract class Numeros(
    protected var numeroUno: Int,
    protected var numeroDos: Int
){
    init{
        println("Inicializacion")
    }
}


class Suma(
    uno:Int,
    dos:Int,
): Numeros(
    uno, dos
    ){
    init {
        this.numeroUno
        this.numeroDos
    }


    constructor(
        uno: Int?,
        dos: Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )

    constructor(
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos==null) 0 else dos,
    )

    constructor(
        uno: Int?,
        dos: Int?
    ):this(
        if(uno==null) 0 else uno,
        if(uno==null) 0 else dos
    )

    fun sumar(): Int{
        val total: Int = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }
    //SINGLETON
    companion object{
        val pi = 3.14
        val historialSuma = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSuma.add(valorNuevaSuma)
        }
    }
}