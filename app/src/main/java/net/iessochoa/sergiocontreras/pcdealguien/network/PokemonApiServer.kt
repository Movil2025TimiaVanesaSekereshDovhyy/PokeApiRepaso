package net.iessochoa.sergiocontreras.pcdealguien.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiServer {

    // TODO 2: Definir el endpoint
    // GET generation/{id}/
    // Recuerda que debe ser una función 'suspend' si usamos Corrutinas directamente o devolver Call

    // suspend fun getGeneration...


    @GET("generation")
    suspend fun getGenerations():PokemonGenerationsResponse

    @GET("generation/{id}")
    suspend fun getPokemons(
        @Path("id") id:Int
    ) :PokemonResponse
}