package net.iessochoa.sergiocontreras.pcdealguien.ui

import net.iessochoa.sergiocontreras.pcdealguien.network.GenerationDto
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonDto


data class PokemonScreenUiState (
    // Datos de la pantalla
    val selectedGeneration: Int = 1,
    val generations: List<GenerationDto> = emptyList(),
    val requestStatus: RequestStatus = RequestStatus.Idle

    /*
    // Un booleano para saber si estamos cargando (Opcional pero recomendado)
    val isLoading: Boolean = false,

    // Podríamos añadir un mensaje de error si quisiéramos
    val errorMessage: String? = null

     */
)
sealed interface RequestStatus {
    data object Idle : RequestStatus
    data object IsLoading : RequestStatus
    data class Success(val characters: List<PokemonDto>) : RequestStatus //Characters = Pokemons)
    data class Error(val message: String = "Error desconocido") : RequestStatus
}

data class PokemonScreenState(
    val generations: List<GenerationDto> = emptyList(),
    val pokemons: List<PokemonDto> = emptyList(),
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val selectedGeneration: Int = 1
)
