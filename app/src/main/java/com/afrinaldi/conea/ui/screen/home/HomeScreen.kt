package com.afrinaldi.conea.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrinaldi.conea.di.Injection
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.components.CardItem
import com.afrinaldi.conea.ui.ViewModelFactory
import com.afrinaldi.conea.ui.common.UiState
import com.afrinaldi.conea.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.query
    var fetched by rememberSaveable { mutableStateOf(false) }

    viewModel.uiState.collectAsState(initial = UiState.Loading)
        .value.let {
            when (it) {
                is UiState.Loading -> {
                    if (!fetched) {
                        fetched = true
                        viewModel.getAllHeroes()
                    }
                }
                is UiState.Success -> {
                    HomeContent(
                        heroesData = it.data,
                        query = query,
                        onQueryChange = viewModel::searchHero,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
                is UiState.Error -> {}
            }
        }
}

@Composable
fun HomeContent(
    heroesData: List<Heroes>,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn {
        item {
            SearchBar(query = query, onQueryChange = onQueryChange)
        }
        items(heroesData, key = { it.name!! }) {
            CardItem(
                name = it.name!!,
                image = it.image!!,
                modifier = modifier
                    .clickable {
                        navigateToDetail(it.name!!)
                    }
            )
        }
    }
}