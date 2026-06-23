package gamez.ana.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import gamez.ana.composepokedex.components.Ability
import gamez.ana.composepokedex.components.Chip
import gamez.ana.composepokedex.components.PokemonNavItem
import gamez.ana.composepokedex.domain.Pokemon
import gamez.ana.composepokedex.dummies.getNextPokemon
import gamez.ana.composepokedex.dummies.getPreviousPokemon
import gamez.ana.composepokedex.ui.theme.ComposePokedexTheme
import gamez.ana.composepokedex.ui.theme.ElectricYellow
import gamez.ana.composepokedex.ui.theme.White

class MainActivity : ComponentActivity() {
    val pokemon = Pokemon("Pikachu", 25,
        "eléctrico",
        "Cuando se enfada, este Pokémon descarga " +
                "la energía que almacena en el interior de las " +
                "bolsas de las mejillas",
        0.4f,
        6f,
        true,
        "Gruñido",
        R.drawable.pikachu)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        pokemon = pokemon,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonHeader(name:String, number: Int, fav:Boolean){
    Row(Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
      Column {
          Text(name)
          Text(
              "${number}",
              modifier = Modifier.align(Alignment.End)
          )
      }
        Box(){
            Image(
                painter = painterResource(R.drawable.pokeball),
                contentDescription = "Pokeball image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(160.dp)
                    .offset(30.dp, 45.dp)
            )
            Image(
                painter = painterResource(
                    if(fav) R.drawable.star_filled else R.drawable.star_outline
                ),
                contentDescription =
                    if(fav) "Yellow star filled" else "yellow star outlined",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun PokemonCard(name:String, number:Int, description:String,
                type: String, weight: Float, height: Float,
                ability:String, image: Int){
    Box(contentAlignment = Alignment.TopCenter){
        Image(
            painter = painterResource(image),
            contentDescription = "Imagen del pokemon",
            Modifier.offset(0.dp, -80.dp)
                .zIndex(2f)
                .size(130.dp)
        )
        Card(
            Modifier.fillMaxWidth().fillMaxHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = White)){
            Column(Modifier.fillMaxWidth()){
                Chip(type, ElectricYellow,
                    Modifier.padding(top = 70.dp)
                        .align(Alignment.CenterHorizontally))

                Row(Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                        Column{
                            Ability("Row", "Altura", "${height}")
                            Ability("Row", "Peso", "${weight}")
                        }
                    Ability("Column", "Habilidad", ability)
                }

                Row(Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 25.dp)){
                    Text(description)
                }
            }
        }
    }

}
@Composable
fun Greeting(pokemon: Pokemon, modifier: Modifier = Modifier) {
    val previousPokemon = getPreviousPokemon(pokemon)
    val nextPokemon = getNextPokemon(pokemon)

    Column(modifier.background(ElectricYellow, RectangleShape)) {
        PokemonHeader(pokemon.name, pokemon.number, pokemon.fav)
        PokemonCard(
            pokemon.name, pokemon.number,
            pokemon.description, pokemon.type,
            pokemon.weight, pokemon.height, pokemon.ability,
            pokemon.image
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            previousPokemon?.let {
                PokemonNavItem(
                    position = "left",
                    image = it.image,
                    name = it.name,
                    number = it.number
                )
            }
            nextPokemon?.let {
                PokemonNavItem(
                    position = "right",
                    image = it.image,
                    name = it.name,
                    number = it.number
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePokedexTheme {
        Greeting(Pokemon("Pikachu", 25,
            "eléctrico",
            "Cuando se enfada, este Pokémon descarga " +
                    "la energía que almacena en el interior de las " +
                    "bolsas de las mejillas",
            0.4f,
            6f,
            true,
            "Gruñido",
            R.drawable.pikachu))
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHeaderPreview(){
    ComposePokedexTheme {
        PokemonHeader("Pikachu", 25, true)
    }
}

