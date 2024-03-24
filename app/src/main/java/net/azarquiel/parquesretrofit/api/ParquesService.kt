package net.azarquiel.parquesretrofit.api

import kotlinx.coroutines.Deferred
import net.azarquiel.parquesretrofit.model.*
import retrofit2.Response
import retrofit2.http.*


interface ParquesService {
    @GET("comunidades")
    fun getComunidades(): Deferred<Response<Respuesta>>

    @GET("parques")
    fun getParques(): Deferred<Response<Respuesta>>

    // idcomunidad en la url
    @GET("comunidad/{idcomunidad}/parques")
    fun getParquesByComunidad(@Path("idcomunidad") idcomunidad: Long): Deferred<Response<Respuesta>>

    @GET("parque/{idparque}/imagenes")
    fun getImagenes(@Path("idparque") idparque:Long) : Deferred<Response<Respuesta>>

    @PATCH("parque/{idparque}/like")
    fun setLike(@Path("idparque") idparque:Long) : Deferred<Response<Respuesta>>
// ……..   resto de métodos de la interfaz ………..

}