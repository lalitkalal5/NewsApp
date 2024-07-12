package com.example.newsnewsapp.screens

import Detailscreen
import Fav
import Home
import Search
import a
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsnewsapp.Screens
import com.example.newsnewsapp.ui.theme.NEWSNEWSappTheme
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            NEWSNEWSappTheme {
                    Greeting()
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting()
{
    val navigationcontroller = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(title = {Text(text = "परसो तक", modifier = Modifier.padding(start = 128.dp))})},
        bottomBar = { Bottomfun(navigationcontroller) }
    )
    {innerpadding->
        NavHost(navController = navigationcontroller, startDestination = Screens.Cat.route, modifier = Modifier.padding(innerpadding) ){
            composable(Screens.Home.route){ Home(navigationcontroller) }
            composable(Screens.Search.route){ Search(navigationcontroller) }
            composable(Screens.Fav.route){ Fav(navigationcontroller) }
            composable(Screens.Cat.route){ Cat(navigationcontroller) }
            composable(Screens.Detail.route,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("imageUrl") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("time") { type = NavType.StringType },
                    navArgument("source") { type = NavType.StringType }
                )
                ){ backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                val description = backStackEntry.arguments?.getString("description") ?: ""
                val time = backStackEntry.arguments?.getString("time") ?: ""
                val source = backStackEntry.arguments?.getString("source") ?: ""
                Detailscreen(navigationcontroller, title, imageUrl, description, time, source)}
            composable("a/{cat}",
                arguments = listOf(navArgument("cat") { type = NavType.StringType }))
            { backStackEntry -> backStackEntry.arguments?.getString("cat")
                ?.let { a(navigationcontroller, it) } }
        }
    }
}
@Composable
fun Bottomfun(navController: NavController) {
    val items = listOf(
        Screens.Cat, Screens.Home, Screens.Fav,Screens.Search
 )
    NavigationBar {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(selected = currentRoute == screen.route,
                icon = { Icon(screen.icon, contentDescription = screen.route)},
                label = { Text(text = screen.route)},
                onClick = { navController.navigate(screen.route){
                    popUpTo(navController.graph.startDestinationId){}
                    launchSingleTop = true
                    restoreState = true
                } })
        }
    }
}
