package net.azarquiel.parquesretrofit.api

import net.azarquiel.parquesretrofit.model.*


class MainRepository() {
    val service = WebAccess.parquesService

    suspend fun getComunidades(): List<Comunidad> {
        val webResponse = service.getComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }
    suspend fun getParques(): List<Parque> {
        val webResponse = service.getParques().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }

    suspend fun getParquesByComunidad(idcomunidad:Long): List<Parque> {
        val webResponse = service.getParquesByComunidad(idcomunidad).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.parques
        }
        return emptyList()
    }

    suspend fun getImagenes(idparque:Long): List<Imagen> {
        val webResponse = service.getImagenes(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.imagenes
        }
        return emptyList()
    }

    suspend fun setLike(idparque: Long): String? {
        val webResponse = service.setLike(idparque).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.msg
        }
        return ""
    }


}
