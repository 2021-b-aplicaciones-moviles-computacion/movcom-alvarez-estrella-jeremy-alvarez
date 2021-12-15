import java.util.*

class Trainer (
    private var id_trainer: Int = 0,
    private var nombre_trainer: String = "",
    private var genero_trainer:String = "",
    private var dinero: Float = 0F,
    private var juego_iniciado: Date = Date()
){
    init {

    }

    //-------GETTERS-------------------
    public fun getIdTrainer(): Int{
        return this.id_trainer
    }

    public fun getnombreTrainer(): String{
        return this.nombre_trainer
    }

    public fun getGeneroTrainer(): String{
        return this.genero_trainer
    }

    public fun getDinero(): Float{
        return this.dinero
    }

    public fun getJuegoInit(): Date{
        return this.juego_iniciado
    }

    //-----------SETTERS------------------
    public fun setIdTrainer(idTra: Int){
        this.id_trainer = idTra
    }

    public fun setNombreTrainer(nombreTra: String){
        this.nombre_trainer = nombreTra
    }

    public fun setGeneroTrainer(genero: String){
        this.genero_trainer = genero
    }

    public fun setDinero(dinero: Float){
        this.dinero = dinero
    }

    public fun setJuegoIniciado(gameStart: Date){
        this.juego_iniciado = gameStart
    }
}