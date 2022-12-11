package com.afrinaldi.conea.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.afrinaldi.conea.di.Injection
import com.afrinaldi.conea.ui.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrinaldi.conea.ui.common.UiState
import com.afrinaldi.conea.ui.components.DetailItem

@Composable
fun DetailScreen(
    name: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading)
        .value.let {
            when (it) {
                is UiState.Loading -> {
                    viewModel.getSelectedHero(name)
                }
                is UiState.Success -> {
                    val selected = it.data
                    DetailContent(
                        image = selected.image!!,
                        name = selected.name!!,
                        detail = selected.detail!!,
                    )
                }
                is UiState.Error -> {}
            }
        }
}

@Composable
fun DetailContent(
    image: Int,
    name: String,
    detail: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        DetailItem(image = image, name = name, detail = detail)
    }
}