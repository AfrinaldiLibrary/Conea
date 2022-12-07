package com.afrinaldi.conea.ui.screen.home

import android.util.Log
import androidx.compose.material.Text
import com.afrinaldi.conea.data.Result
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.afrinaldi.conea.di.Injection
import com.afrinaldi.conea.model.ArticlesItem
import com.afrinaldi.conea.ui.ViewModelFactory
import com.afrinaldi.conea.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.getAllNews().collectAsState(initial = Result.Loading)
        .value.let { result ->
            when(result){
                is Result.Loading -> {

                }
                is Result.Success -> {
                    HomeContent(news = result.data)
                }
                is Result.Error -> {}
            }
        }
}

@Composable
fun HomeContent(
    news: List<ArticlesItem>,
    modifier: Modifier = Modifier
) {
    Log.e("news", news.toString())
    Text(text = news[0].title)
}