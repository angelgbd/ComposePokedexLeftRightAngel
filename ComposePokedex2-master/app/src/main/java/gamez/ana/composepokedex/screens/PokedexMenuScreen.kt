package gamez.ana.composepokedex.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gamez.ana.composepokedex.components.PokemonGridItem
import gamez.ana.composepokedex.domain.Pokemon
import gamez.ana.composepokedex.dummies.showPokemonList

@Composable
fun PokedexMenuScreen(pokemons: List<Pokemon>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp,5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pokemons){
            pokemon ->
            PokemonGridItem(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexMenuScreenPreview(){
    PokedexMenuScreen(showPokemonList())
}