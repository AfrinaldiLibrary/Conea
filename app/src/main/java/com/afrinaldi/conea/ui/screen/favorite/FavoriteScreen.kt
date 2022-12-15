package com.afrinaldi.conea.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.afrinaldi.conea.di.Injection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.ViewModelFactory
import com.afrinaldi.conea.ui.common.UiState
import com.afrinaldi.conea.ui.components.CardItem
import com.afrinaldi.conea.R

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading)
        .value.let {
            when (it) {
                is UiState.Loading -> {
                    viewModel.getFavorites()
                }
                is UiState.Success -> {
                    FavoriteContent(
                        heroesData = it.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
                is UiState.Error -> {}
            }
        }
}

@Composable
fun FavoriteContent(
    heroesData: List<Heroes>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
){
    if (heroesData.isEmpty()){
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.data_not_found), textAlign = TextAlign.Center)
        }
    } else {
        LazyColumn(modifier = modifier.padding(top = 8.dp)) {
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
}