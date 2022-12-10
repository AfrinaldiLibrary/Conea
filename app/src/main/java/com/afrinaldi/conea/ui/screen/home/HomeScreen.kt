package com.afrinaldi.conea.ui.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrinaldi.conea.di.Injection
import com.afrinaldi.conea.model.ArticlesItem
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.CardItem
import com.afrinaldi.conea.ui.ViewModelFactory
import com.afrinaldi.conea.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading)
        .value.let {
            when (it) {
                is UiState.Loading -> {
                    viewModel.getAllHeroes()
                }
                is UiState.Success -> {
                    HomeContent(
                        heroesData = it.data,
                        modifier = modifier
                    )
                }
                is UiState.Error -> {}
            }
        }
}

@Composable
fun HomeContent(
    heroesData: List<Heroes>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
            items(heroesData) {
                CardItem(
                    name = it.name!!,
                    image = it.image!!,
                    modifier = modifier
                )
            }
    }
}