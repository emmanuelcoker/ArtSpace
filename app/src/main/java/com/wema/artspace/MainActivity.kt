package com.wema.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wema.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    var artNumber by remember { mutableStateOf(1) }
    var artDescription by remember { mutableStateOf("") }
    var artAuthor by remember { mutableStateOf("") }
    var artYear by remember { mutableStateOf("") }

    val totalNumberOfArt = 5

    val artImage = when(artNumber) {
        1 -> {
            artDescription = stringResource(R.string.chinese_flower_girl)
            artAuthor = stringResource(R.string.zhang_tong)
            artYear = "1956"
            R.drawable.art1
        }
        2 -> {
            artDescription = stringResource(R.string.polygon_deer)
            artAuthor = stringResource(R.string.hapzing)
            artYear = "2022"
            R.drawable.art2
        }
        3 -> {
            artDescription = stringResource(R.string.the_iris)
            artAuthor = stringResource(R.string.jerry_king)
            artYear = "2010"
            R.drawable.art3
        }
        4 -> {
            artDescription = stringResource(R.string.bicycle_wall_art)
            artAuthor = stringResource(R.string.ken_hart)
            artYear = "1987"
            R.drawable.art4
        }
        5 -> {
            artDescription = stringResource(R.string.wood_pecker)
            artAuthor = stringResource(R.string.finny)
            artYear = "1805"
            R.drawable.art5
        }
        else -> { throw Exception("Invalid Art Number Exception")}
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        ArtImageDisplay(artImage)
        Spacer(modifier = Modifier.height(32.dp))
        ArtDescription(description = artDescription, author = artAuthor, year = artYear)
        Spacer(modifier = Modifier.height(32.dp))
        ArtNavigation(
            { if (artNumber > 1) artNumber-- },
            { if(artNumber < totalNumberOfArt) artNumber++}
        )
    }
}

//render Art Image
@Composable
fun ArtImageDisplay(image: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .width(300.dp)
            .height(300.dp),
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "art image",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun ArtDescription(description: String, author: String, year: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .width(300.dp)
                .background(colorResource(id = R.color.gray))
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = description,
                    fontSize = 20.sp,
                )
            }

           Row(
               horizontalArrangement = Arrangement.Start,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 16.dp, bottom = 16.dp)
           ) {
               Text(text = author, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
               Text(text = "(${year})", fontSize = 16.sp)
           }
        }
    }
}


@Composable
fun ArtNavigation(prev: () -> Unit, next: ()->Unit, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationButton(
            text = stringResource(R.string.previous),
            color = colorResource(id = R.color.white),
            onClickButton = prev
        )

        NavigationButton(
            text = stringResource(R.string.next),
            color = colorResource(id = R.color.white),
            onClickButton = next
        )
    }
}

@Composable
fun NavigationButton(text: String, color: Color, onClickButton: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClickButton,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = text,
            color = color,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}