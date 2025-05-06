package com.scafisystems.pokemoncards.presenter.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.scafisystems.pokemoncards.presenter.screen.PokemonDetailScreen
import com.scafisystems.pokemoncards.presenter.screen.PokemonFavoriteListScreen
import com.scafisystems.pokemoncards.presenter.screen.PokemonListScreen

@Composable
fun AppNavGraph(startDestination: String = "pokemonList") {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally(animationSpec = tween(1000)) },
        exitTransition = { slideOutHorizontally(animationSpec = tween(1000)) },
        popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) },
        popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) }
    ) {
        composable("pokemonList") {
            PokemonListScreen(
                onPokemonClick = { pokemon ->
                    navController.navigate("pokemonDetail/${pokemon.name}")
                },
                onNavigateToFavorites = {
                    navController.navigate("pokemonFavorites")
                }
            )
        }

        composable(
            route = "pokemonDetail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName").orEmpty()
            PokemonDetailScreen(pokemonName = name, navController = navController)
        }

        composable("pokemonFavorites") {
            PokemonFavoriteListScreen(
                onPokemonClick = { pokemon ->
                    navController.navigate("pokemonDetail/${pokemon.name}")
                },
                navController = navController
            )
        }
    }
}