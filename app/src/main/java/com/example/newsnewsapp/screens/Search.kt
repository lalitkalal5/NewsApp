import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsnewsapp.Screens
import com.example.newsnewsapp.api.Retrofitinstance.Companion.service
import com.example.newsnewsapp.models.NewsResponce
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun Search(navController: NavController){
    var Searchstate by remember { mutableStateOf<Boolean>(false) }
    var Searchvalue by remember { mutableStateOf<String>("") }
    Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally
        )
    {
        Text(text = "Type keyword of news", fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
    }

    Row (modifier = Modifier.padding(top=20.dp), horizontalArrangement = Arrangement.SpaceAround){
        TextField(value = Searchvalue, onValueChange = {newtext -> Searchvalue = newtext}, placeholder = { Text(
            text = "virat kohli"
        )})
        Icon(Icons.Default.Search, contentDescription =null,modifier = Modifier.clickable { Searchstate =true } )
    }
    if(Searchstate){
      Funsearch(Searchvalue,navController)
    }
}

@Composable
fun Funsearch(Searchvalue:String,navController: NavController) {
var errormsg by remember { mutableStateOf("") }
    var newsresponce by remember{ mutableStateOf<NewsResponce?>(null) }
        LaunchedEffect(Unit){
            try {
                val responce = service.search(Searchvalue = Searchvalue)
                if(responce.isSuccessful){
                    newsresponce = responce.body()!!
                }
            }catch(e:Exception){
                errormsg = "Exception: ${e.message}"
            }
        }
    if (newsresponce != null) {
        LazyColumn {
            items(newsresponce!!.articles) { article ->
                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = CardDefaults.shape,
                    colors = CardDefaults.cardColors(),
                    elevation = CardDefaults.cardElevation(5.dp),
                    onClick = {
                        val encodedTitle = URLEncoder.encode(article.title, StandardCharsets.UTF_8.toString())
                        val encodedImageUrl = URLEncoder.encode(article.urlToImage, StandardCharsets.UTF_8.toString())
//                        val  = URLEncoder.encode(article.content, StandardCharsets.UTF_8.toString())
                        val encodedTime = URLEncoder.encode(article.publishedAt, StandardCharsets.UTF_8.toString())
                        val encodedDescription = article.content
                        val encodedSource = URLEncoder.encode(article.source.name, StandardCharsets.UTF_8.toString())
                        navController.navigate("Detail/$encodedTitle/$encodedImageUrl/$encodedDescription/$encodedTime/$encodedSource")
                    }
                )
//                { Row(modifier = Modifier.padding(),00000
//                    horizontalArrangement = Arrangement.spacedBy(4.dp),
//                    ){
//                        PicassoImage2(url = article.urlToImage, modifier = Modifier)
                {    Text(
                    text = article.title ?: "NO_TITLE",
                    modifier = Modifier.padding(10.dp)
                )
//                }

                }
            }
        }
    }
}
