import java.util.*

fun main(){
    var sc: Scanner = Scanner(System.`in`)
    var opcion = ""
    val mensaje = "\n----------------BIENVENIDO------------------\n" +
                  "Ingrese a que entidad desea entrar\n"+
                  "1.- Entrenador\t\t\t\t\t2.- Pokemon"

    println(mensaje)
    opcion = sc.nextLine()
    when(opcion){
        "1"->{
            main()
        }
        "2"->{

        }
    }

}
