package com.scafisystems.pokemoncards.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.entity.PokemonItem
import com.scafisystems.pokemoncards.domain.entity.PokemonListDetails
import com.scafisystems.pokemoncards.domain.entity.Stats
import com.scafisystems.pokemoncards.domain.entity.Types

@Composable
fun PokemonCard(
    pokemon: PokemonDetails,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        pokemon.image?.let { imageUrl ->
            PokemonItemHeader(
                pokemon = PokemonItem(
                    name = pokemon.name,
                    imageResource = imageUrl
                )
            )
        }

        Text(
            text = "Types: ${pokemon.types.joinToString(", ") { it.type?.name.orEmpty() }}",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(
                text = "Stats:",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            pokemon.stats.forEach { stat ->
                val statName = stat.stat?.name ?: "Unknown"
                val statValue = stat.baseStat ?: 0
                Text(
                    text = "- $statName: $statValue",
                    fontSize = 14.sp
                )
            }
        }

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    val mockPokemon = PokemonDetails(
        name = "pikachu",
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        types = listOf(
            Types(type = PokemonListDetails("electric", ""))
        ),
        stats = listOf(
            Stats(baseStat = 35, stat = PokemonListDetails("hp", "")),
            Stats(baseStat = 55, stat = PokemonListDetails("attack", "")),
            Stats(baseStat = 90, stat = PokemonListDetails("speed", ""))
        )
    )

    PokemonCard(pokemon = mockPokemon, isFavorite = true, onFavoriteClick = {})
}