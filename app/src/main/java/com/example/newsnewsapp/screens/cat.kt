package com.example.newsnewsapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.PreviewActivity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsnewsapp.Screens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Cat(navController: NavController){

    Text(
        text = "CATEGORIES",
        Modifier.padding(top = 60.dp, start = 128.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
    )

    val context = LocalContext.current
    val cats = listOf("India","us","business","entertainment","general","sports","health","science","technology")

    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(minSize = 180.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    content = {
        items(cats){cat->
            Card (modifier = Modifier.padding(2.dp),
            backgroundColor =Color(android.graphics.Color.DKGRAY),
            contentColor = Color(android.graphics.Color.WHITE,),
                onClick = { navController.navigate("a/$cat") }
        ) {
            Text(text = cat,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White)
           }
        }
    }, modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize())
}
//@Composable
//@Preview
//fun Preview(){
//    Cat()
//}