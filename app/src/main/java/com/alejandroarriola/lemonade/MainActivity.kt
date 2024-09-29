package com.alejandroarriola.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandroarriola.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Limonada(modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun Limonada(modifier: Modifier = Modifier) {
    var estado by remember { mutableStateOf( 1) }
    var squeezes by remember { mutableStateOf( 1) }

    val imageResource = when(estado) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val texto = when(estado) {
        1 -> stringResource(R.string.estado_limon_1)
        2 -> stringResource(R.string.estado_limon_2)
        3 -> stringResource(R.string.estado_limon_3)
        else -> stringResource(R.string.estado_limon_4)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource),
            contentDescription = estado.toString(),
            modifier = Modifier
                .clickable {
                    if(estado == 1) {
                        estado++
                        squeezes = (2..4).random()
                    } else if(estado == 2) {
                        if(squeezes >= 2)
                            squeezes--
                        else
                            estado++
                    } else if(estado == 3) {
                        estado++
                    } else {
                        estado = 1
                    }
                }
        )

        Text(text = texto)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Limonada(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}