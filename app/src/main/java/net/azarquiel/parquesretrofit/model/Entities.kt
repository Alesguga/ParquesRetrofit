package net.azarquiel.parquesretrofit.model

import java.io.Serializable

data class Comunidad(var id:Long=0, var nombre:String=""):Serializable
data class Parque(var id:Long=0, var nombre:String="", var comunidad:Long=0, var imagen:String="", var mapa:String="", var fondo:String="", var descripcion:String="", var likes:Int=0):Serializable
data class Imagen(var id:Long=0, var parque:Long, var foto:String=""):Serializable

data class Respuesta(
    var comunidades:List<Comunidad>,
    var parques:List<Parque>,
    val imagenes: List<Imagen>,
    val msg: String
)