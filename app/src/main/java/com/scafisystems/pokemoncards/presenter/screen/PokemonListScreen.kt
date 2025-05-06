package com.scafisystems.pokemoncards.presenter.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.scafisystems.pokemoncards.domain.entity.PokemonItem
import com.scafisystems.pokemoncards.presenter.components.PokemonItemHeader
import com.scafisystems.pokemoncards.presenter.viewmodel.PokemonCardsHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonCardsHomeViewModel = hiltViewModel(),
    onPokemonClick: (PokemonItem) -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val pokemonItems = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pokédex") },
                actions = {
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Ver favoritos"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                items(pokemonItems.itemCount) { index ->
                    val item = pokemonItems[index]
                    item?.let {
                        val pokemonItem = PokemonItem(
                            name = it.name,
                            imageResource = it.image
                        )
                        PokemonItemHeader(
                            pokemon = pokemonItem,
                            modifier = Modifier.clickable { onPokemonClick(pokemonItem) }
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }

                pokemonItems.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                Text("Erro ao carregar mais pokémons.")
                            }
                        }
                    }
                }
            }
        }
    }
}


