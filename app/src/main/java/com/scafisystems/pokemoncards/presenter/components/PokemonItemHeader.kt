package com.scafisystems.pokemoncards.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.scafisystems.pokemoncards.domain.entity.PokemonItem

@Composable
fun PokemonItemHeader(
    pokemon: PokemonItem,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(pokemon.imageResource)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(164.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = pokemon.name.replaceFirstChar { it.uppercaseChar() },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemHeaderPreview() {
    PokemonItemHeader(
        pokemon = PokemonItem(
            imageResource = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            name = "pikachu"
        )
    )
}