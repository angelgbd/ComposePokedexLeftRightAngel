package gamez.ana.composepokedex.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gamez.ana.composepokedex.R
import gamez.ana.composepokedex.ui.theme.ComposePokedexTheme
import gamez.ana.composepokedex.ui.theme.ElectricYellow

@Composable
fun PokemonNavItem(
    position: String,
    @DrawableRes image: Int,
    name: String,
    number: Int,
    modifier: Modifier = Modifier
) {
    when (position) {
        "left" -> PokemonNavItemContent(
            modifier = modifier,
            horizontalArrangement = Arrangement.Start,
            arrowDrawable = R.drawable.arrow_left,
            arrowContentDescription = "Anterior",
            image = image,
            name = name,
            number = number,
            textAlignment = Alignment.Start
        )
        "right" -> PokemonNavItemContent(
            modifier = modifier,
            horizontalArrangement = Arrangement.End,
            arrowDrawable = R.drawable.arrow_right,
            arrowContentDescription = "Siguiente",
            image = image,
            name = name,
            number = number,
            textAlignment = Alignment.End,
            arrowLast = true
        )
    }
}

@Composable
private fun PokemonNavItemContent(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    @DrawableRes arrowDrawable: Int,
    arrowContentDescription: String,
    @DrawableRes image: Int,
    name: String,
    number: Int,
    textAlignment: Alignment.Horizontal,
    arrowLast: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        if (!arrowLast) {
            Image(
                painter = painterResource(arrowDrawable),
                contentDescription = arrowContentDescription,
                modifier = Modifier.size(32.dp)
            )
        }

        Image(
            painter = painterResource(image),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(65.dp)
        )

        Column(horizontalAlignment = textAlignment) {
            Row(
                modifier = Modifier
                    .size(35.dp, 35.dp)
                    .background(ElectricYellow, RoundedCornerShape(40.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$number",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = name,
                textAlign = if (textAlignment == Alignment.End) TextAlign.End else TextAlign.Start
            )
        }

        if (arrowLast) {
            Image(
                painter = painterResource(arrowDrawable),
                contentDescription = arrowContentDescription,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonNavItemLeftPreview() {
    ComposePokedexTheme {
        PokemonNavItem(
            position = "left",
            image = R.drawable.pikachu,
            name = "Squirtle",
            number = 7
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonNavItemRightPreview() {
    ComposePokedexTheme {
        PokemonNavItem(
            position = "right",
            image = R.drawable.pikachu,
            name = "Jigglypuff",
            number = 39
        )
    }
}
