import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsnewsapp.Screens
import com.example.newsnewsapp.api.Retrofitinstance.Companion.service
import com.example.newsnewsapp.models.NewsResponce
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun a(navController: NavController,cat :String){
    var newsresponce by remember{ mutableStateOf<NewsResponce?>(null) }
    var errormsg by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val responce = when (cat){
                    "us" -> service.us()
                    "sports" -> service.sports()
                    "health" -> service.health()
                    "business" -> service.business()
                    "science" -> service.science()
                    "technology" -> service.technology()
                    else -> service.Gethdind()
                }
                if(responce.isSuccessful){
                    newsresponce = responce.body()
                }else{
                    errormsg = "Error: ${responce.message()}"
                }
            }catch (e: Exception) {
                errormsg = "Exception: ${e.message}"
            }
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
                        var encodedTitle = URLEncoder.encode(article.title?: "No Title Found", StandardCharsets.UTF_8.toString())
                        var encodedImageUrl = URLEncoder.encode(article.urlToImage?: "https://firebasestorage.googleapis.com/v0/b/videoapp-7e345.appspot.com/o/free%20image%20icon%20png%20vector.png?alt=media&token=c3892011-aafb-4f4f-8a27-de76e9f25d52", StandardCharsets.UTF_8.toString())
//                        val encodedDescription = URLEncoder.encode(article.content, StandardCharsets.UTF_8.toString())
                        var encodedTime = URLEncoder.encode(article.publishedAt, StandardCharsets.UTF_8.toString())
                        var encodedDescription = article.content?:"no content found"
                        var encodedSource = URLEncoder.encode(article.source.name?:"no source found", StandardCharsets.UTF_8.toString())
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
