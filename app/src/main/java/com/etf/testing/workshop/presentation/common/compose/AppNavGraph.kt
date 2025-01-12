package com.etf.testing.workshop.presentation.common.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.etf.testing.workshop.domain.model.Breed
import com.etf.testing.workshop.presentation.dogs.compose.DogDetailsScreen
import com.etf.testing.workshop.presentation.dogs.compose.DogsListScreen
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dogs") {
        composable("dogs") {
            DogsListScreen(navController)
        }
        composable("dog/{breed}") { backStackEntry ->
            val breed = Json.decodeFromString<Breed>(backStackEntry.arguments?.getString("breed") ?: "")
            DogDetailsScreen(koinViewModel(parameters = { parametersOf(breed) }))
        }
    }
}