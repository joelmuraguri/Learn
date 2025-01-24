package com.joel.typesafenavigation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.joel.typesafenavigation.ui.theme.TypeSafeNavigationTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Locale
import kotlin.reflect.typeOf

@Serializable
data object MainScreen

@Serializable
data object CustomTypesScreen

@Serializable
data class CustomTypesDetailsScreen(
    val hero : Hero
)

@Serializable
data object BasicsScreen

object CustomNavType{

    val heroType = object : NavType<Hero>(
        isNullableAllowed = true
    ){
        override fun get(bundle: Bundle, key: String): Hero? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Hero {
             return Json.decodeFromString(Uri.decode(value))
        }

        override fun put(bundle: Bundle, key: String, value: Hero) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value: Hero): String {
            return Uri.encode(Json.encodeToString(value))
        }

    }
}

@Serializable
data class Hero(
    val id : Int,
    val name : String,
    val image : Int
)

val heroes = listOf(
    Hero(
        id = 1,
        name = "Invincible",
        image = R.drawable.invincible
    ),Hero(
        id = 2,
        name = "Superman",
        image = R.drawable.superman
    ),Hero(
        id = 3,
        name = "IronMan",
        image = R.drawable.ironman
    ),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TypeSafeNavigationTheme {
                MainGraph()
            }
        }
    }
}

@Composable
fun MainGraph(){

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = MainScreen
    ){
        composable<MainScreen> {
            MainScreen(
                onNavigateToBasics = {
                    navHostController.navigate(BasicsScreen)
                },
                onNavigateToCustomTypes = {
                    navHostController.navigate(CustomTypesScreen)
                }
            )
        }
        composable<BasicsScreen> {
            BasicsScreen()

        }
        composable<CustomTypesScreen> {
            CustomListScreen(
                onCardClick = { hero ->
                    navHostController.navigate(
                        CustomTypesDetailsScreen(
                            hero = hero
                        )
                    )
                }
            )
        }

        composable<CustomTypesDetailsScreen>(
            typeMap = mapOf(
                typeOf<Hero>() to CustomNavType.heroType
            )
        ) {
            val args = it.toRoute<CustomTypesDetailsScreen>()
            CustomDetailsScreen(
                hero = args.hero
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToBasics : () -> Unit,
    onNavigateToCustomTypes : () -> Unit
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Type Safe Navigation") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .clickable { onNavigateToBasics() },
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "Basics",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Card(
                    modifier = Modifier
                        .clickable { onNavigateToCustomTypes() },
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "CustomTypes",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicsScreen(){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Basics") })
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
        ) {

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomListScreen(
    onCardClick : (Hero) -> Unit
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Custom Types") })
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(12.dp)
            ) {
                items(heroes){
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable { onCardClick(it) },
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(it.image), contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                it.name,
                                color = Color.Green,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomDetailsScreen(
    hero: Hero
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                hero.name.uppercase(Locale.getDefault()),
                color = Color.Green,
            )
            Image(
                painter = painterResource(hero.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
    }
}