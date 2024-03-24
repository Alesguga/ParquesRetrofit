package net.azarquiel.parquesretrofit.api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import net.azarquiel.parquesretrofit.api.ParquesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pacopulido
 */

object WebAccess {

    val parquesService : ParquesService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apiparques/")
            .build()

        return@lazy retrofit.create(ParquesService::class.java)
    }
}
