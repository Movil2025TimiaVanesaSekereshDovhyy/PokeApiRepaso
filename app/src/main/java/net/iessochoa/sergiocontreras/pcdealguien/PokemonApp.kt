package net.iessochoa.sergiocontreras.pcdealguien

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.sergiocontreras.pcdealguien.ui.PokemonScreen
import net.iessochoa.sergiocontreras.pcdealguien.ui.PokemonViewModel

@Composable
fun PokemonApp() {
    val viewModel: PokemonViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        //TODO
        PokemonScreen (
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onClickGeneration = { genId -> viewModel.fetchPokemonByGeneration(genId) },
            onGenerationSelection = { genStr -> viewModel.selectGeneration(genStr) }
        )
    }
}