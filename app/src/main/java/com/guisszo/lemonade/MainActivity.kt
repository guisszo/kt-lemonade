package com.guisszo.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           Scaffold(
               topBar = {
                   LimonadeHeader()
               },
               content = {
                   LimonadeApp()
               }
           )
        }
    }
}

@Composable
@Preview
fun LimonadeHeader(modifier: Modifier = Modifier){
    Row(modifier = modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.Yellow)
        .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
           text =  stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }
}

@Composable
@Preview
fun LimonadeApp(modifier: Modifier = Modifier){

    var result by remember {mutableStateOf(1) }
    var clickCounter by remember { mutableIntStateOf((2..4).random()) }

  val ressourceRes =   when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val resourceDesc = when (result) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(200.dp)
                .padding(20.dp)
                .border(
                    width = 2.dp,
                    color = Color(105, 205, 216), // Couleur RVB pour la bordure
                    shape = RoundedCornerShape(10.dp)
                )
                .background(
                    colorResource(id = R.color.green_light),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    when (result) {
                        1, 3 -> result++
                        2 -> if (clickCounter < result) {
                            clickCounter++
                        } else {
                            clickCounter = 0
                            result++
                        }
                        else -> result = 1
                    }
                }
        ){
            Image(
                painter = painterResource(id = ressourceRes),
                contentDescription =  stringResource(resourceDesc),
            )
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            stringResource(id = resourceDesc),
            fontSize = 18.sp
        )
    }
}