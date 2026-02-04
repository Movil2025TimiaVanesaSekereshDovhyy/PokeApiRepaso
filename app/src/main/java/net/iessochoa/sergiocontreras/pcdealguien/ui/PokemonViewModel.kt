package net.iessochoa.sergiocontreras.pcdealguien.ui

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.pcdealguien.data.PokemonRepository
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonResponse

class PokemonViewModel : ViewModel() {
    // Estado para la lista de pokemons
    val _uiState = MutableStateFlow(PokemonScreenUiState())
    val uiState: StateFlow<PokemonScreenUiState> = _uiState.asStateFlow()

    init {
        loadGenerations()
    }

    // Función para llamar a la API
    private fun loadGenerations() {
        viewModelScope.launch {
            try {
                // TODO 4: Llamar a RetrofitClient y actualizar _pokemonList
                // val response = RetrofitClient.service.getGeneration(generationId)
                // _pokemonList.value = ...
                // 1. Llamamos al repositorio para obtener TODAS las generaciones desde la API.
                val generations = PokemonRepository.getGenerations()

                // Guardamos la lista de generaciones
                // Ponemos el estado en Idle
                _uiState.update { current ->
                    current.copy(
                        generations = generations,
                        requestStatus = RequestStatus.Idle
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        requestStatus = RequestStatus.Error(
                            e.message ?: "Error desconocido"
                        )
                    )
                }
            }
        }
    }

    fun fetchPokemonByGeneration(generationId: Int) {
        viewModelScope.launch {
            try {
                // 1. Cambiamos el estado a "cargando"
                _uiState.update {
                    it.copy(
                        requestStatus = RequestStatus.IsLoading
                    )
                }

                // 2. Llamamos al repositorio para obtener los Pokémon de esa generación
                val pokemonList =
                    PokemonRepository.getPokemonsFromGeneration(generationId)

                // 3. Si todo va bien, actualizamos el estado con los datos recibidos
                _uiState.update { current ->
                    current.copy(requestStatus = RequestStatus.Success(pokemonList))
                }

            } catch (e: Exception) {
                _uiState.update { current ->
                    current.copy(
                        requestStatus = RequestStatus.Error(
                            e.message ?: "Error desconocido"
                        )
                    )
                }
            }
        }
    }

    // Recibe el valor del dropDown
    fun selectGeneration(generation: String) {
        _uiState.update { current ->
            current.copy(
                selectedGeneration = generation.toIntOrNull() ?: 1
            )
        }
    }


    fun retry() {
        loadGenerations()
    }
}
