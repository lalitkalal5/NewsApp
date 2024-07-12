package com.example.newsnewsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route :String,val icon :ImageVector){

    object Home : Screens("Headlines", Icons.Default.Home)
    object Search: Screens("Search", Icons.Default.Search)
    object Fav : Screens("Fav", Icons.Default.Star)
    object Detail : Screens("Detail/{title}/{imageUrl}/{description}/{time}/{source}", Icons.Default.Star)
    object Cat: Screens("Cat", Icons.Default.KeyboardArrowDown)
    object a: Screens("a", Icons.Default.KeyboardArrowDown)
}