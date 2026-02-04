package net.iessochoa.sergiocontreras.pcdealguien.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
// TODO 1: Crear las Data Classes según el JSON de https://pokeapi.co/api/v2/generation/{id}/

@Serializable
data class PokemonGenerationsResponse(
    // TODO: Necesitamos el nombre y la url para luego sacar el ID
    @SerialName(value = "results") val results : List<GenerationDto>

)
@Serializable
data class GenerationDto(
    @SerialName(value = "name") val nameGeneration: String,
    @SerialName(value = "url") val url: String
)



@Serializable
data class PokemonResponse(
    // TODO: Mapear el array "pokemon_species"
    @SerialName(value="pokemon_species") val pokemons: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    @SerialName(value = "name") val namePokemon: String,
    @SerialName(value = "url") val url: String
)