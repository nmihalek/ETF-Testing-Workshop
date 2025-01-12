package com.etf.testing.workshop.presentation.dogs.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.etf.testing.workshop.presentation.common.DataState
import com.etf.testing.workshop.presentation.dogs.DogsListViewModel
import com.etf.testing.workshop.presentation.dogs.model.DogBreedsUi
import org.koin.androidx.compose.koinViewModel
import kotlin.collections.orEmpty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsListScreen(
    navController: NavController,
    viewModel: DogsListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.fetchBreedsList()
    }
    DogsListContent(
        state = state.value.data.orEmpty(),
        navigateToDogDetails = { breed ->
            navController.navigate("dog/$breed")
        }
    )
    if (state.value is DataState.Error) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.align(Alignment.Center), text = "Error: ${(state.value as DataState.Error).error?.message}")
        }
    }
    if (state.value is DataState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun DogListItem(breed: String, onBreedClicked: () -> Unit) {
    Text(
        modifier = Modifier
            .clickable(onClick = onBreedClicked)
            .padding(8.dp)
            .fillMaxWidth(),
        text = breed
    )
    HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DogsListContent(
    state: DogBreedsUi,
    navigateToDogDetails: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Dog Breed List") }
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {

            }
            items(state.size) { index ->
                val breed = state[index]
                DogListItem(
                    breed = breed.getFullBreedName(),
                    onBreedClicked = {
                        navigateToDogDetails(breed.serialize())
                    }
                )
            }

        }
    }
}