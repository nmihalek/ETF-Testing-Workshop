package com.etf.testing.workshop.presentation.dogs.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.etf.testing.workshop.presentation.common.DataState
import com.etf.testing.workshop.presentation.dogs.DogDetailsViewModel
import com.etf.testing.workshop.presentation.dogs.model.BreedUi
import com.etf.testing.workshop.presentation.dogs.model.DogUi
import com.etf.testing.workshop.presentation.dogs.model.toUi

@Composable
fun DogDetailsScreen(viewModel: DogDetailsViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.fetchBreedDetails()
    }
    DogDetailsContent(breed = viewModel.breed.toUi(), state = state.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailsContent(breed: BreedUi, state: DataState<DogUi> ) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Breed: ${breed.getFullBreedName()}")
            }
        )
        when(state) {
            is DataState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is DataState.Success -> {
                DogDetailsContentSuccess(breed = state.data!!)
            }
            is DataState.Error -> {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                    text = "Error: ${state.error?.message}",
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
private fun DogDetailsContentSuccess(breed: DogUi) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = gridCellCount()
    ) {
        items(breed.images.size) { index ->
            AsyncImage(
                model = breed.images[index],
                contentDescription = "${breed.breed} image #$index",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun gridCellCount(): StaggeredGridCells {
    val configuration = LocalConfiguration.current
    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            StaggeredGridCells.Fixed(3)
        }
        else -> {
            StaggeredGridCells.Fixed(2)
        }
    }
}
