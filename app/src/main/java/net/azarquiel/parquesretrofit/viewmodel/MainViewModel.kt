package net.azarquiel.parquesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.parquesretrofit.api.MainRepository
import net.azarquiel.parquesretrofit.model.Comunidad
import net.azarquiel.parquesretrofit.model.Imagen
import net.azarquiel.parquesretrofit.model.Parque

class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getComunidades()
        }
        return comunidades
    }
    fun getParques(): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParques()
        }
        return parques
    }

    fun getParquesByComunidad(idcomunidad:Long): MutableLiveData<List<Parque>> {
        val parques = MutableLiveData<List<Parque>>()
        GlobalScope.launch(Main) {
            parques.value = repository.getParquesByComunidad(idcomunidad)
        }
        return parques
    }
    fun getImagenes(idparque:Long): MutableLiveData<List<Imagen>> {
        val imagenes = MutableLiveData<List<Imagen>>()
        GlobalScope.launch(Main) {
            imagenes.value = repository.getImagenes(idparque)
        }
        return imagenes
    }

    fun setLike(idparque: Long):MutableLiveData<String> {
        val msg= MutableLiveData<String>()
        GlobalScope.launch(Main) {
            msg.value = repository.setLike(idparque)
        }
        return msg
    }
}

