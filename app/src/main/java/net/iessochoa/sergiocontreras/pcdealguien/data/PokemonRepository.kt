package net.iessochoa.sergiocontreras.pcdealguien.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonApiServer
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object PokemonRepository {

    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .baseUrl(baseUrl)
            .build()
    }

    private val retrofitService: PokemonApiServer by lazy {
        retrofit.create(PokemonApiServer::class.java)
    }

    suspend fun getGenerations() =
        retrofitService.getGenerations().results

    suspend fun getPokemonsFromGeneration(id: Int) =
        retrofitService.getPokemons(id).pokemons
}
