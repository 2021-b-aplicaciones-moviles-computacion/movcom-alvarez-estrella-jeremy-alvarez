

class Pokemon (
    private var idPokemon: Int = 0,
    private var nombre_pokemon: String = "",
    private var shiny: Boolean = true,
    private var nivel: Int = 1,
    private var habilidad: String = "",
    private var estadisticas: Array<Int> = arrayOf(0,0,0,0,0,0),
    private var trainer: Int = 0
){
    init {

    }
    //-------GETTERS-------------------
    public fun getIdPokemon(): Int{
        return this.idPokemon
    }

    public fun getNombre_Pokemon(): String{
        return this.nombre_pokemon
    }

    public fun getShiny(): Boolean{
        return this.shiny
    }

    public fun getNivel(): Int{
        return this.nivel
    }

    public fun getHabilidad(): String{
        return this.habilidad
    }

    public fun getEstadisticas(): Array<Int>{
        return this.estadisticas
    }

    public fun getTrainer(): Int{
        return this.trainer
    }

    //-----------SETTERS------------------
    public fun setIdPokemon(idPok: Int){
        this.idPokemon = idPok
    }

    public fun setNombre_Pokemon(nombre: String){
        this.nombre_pokemon = nombre
    }

    public fun setShiny(shi: Boolean){
        this.shiny = shi
    }

    public fun setNivel(lvl:Int){
        this.nivel = lvl
    }

    public fun setHabilidad(hab:String){
        this.habilidad=hab
    }

    public fun setEstadisticas(stats: Array<Int>){
        this.estadisticas = stats
    }

    public fun setTrainer(entrenador: Int){
        this.trainer = entrenador
    }
}