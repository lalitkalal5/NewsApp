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
import androidx.navigation.NavController
import com.example.newsnewsapp.Screens
import com.example.newsnewsapp.api.Retrofitinstance.Companion.service
import com.example.newsnewsapp.models.NewsResponce
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun Home(navController: NavController){
    var newsresponce by remember{ mutableStateOf<NewsResponce?>(null) }
    var errormsg by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val responce = service.Gethdind()
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
                        val encodedTitle = URLEncoder.encode(article.title, StandardCharsets.UTF_8.toString())
                        val encodedImageUrl = URLEncoder.encode(article.urlToImage, StandardCharsets.UTF_8.toString())
//                        val encodedDescription = URLEncoder.encode(article.content, StandardCharsets.UTF_8.toString())
                        val encodedTime = URLEncoder.encode(article.publishedAt, StandardCharsets.UTF_8.toString())
                        val encodedDescription = article.content
                        val encodedSource = URLEncoder.encode(article.source.name, StandardCharsets.UTF_8.toString())
                        navController.navigate("Detail/$encodedTitle/$encodedImageUrl/$encodedDescription/$encodedTime/$encodedSource")
                    }
                ) {
                    Text(
                        text = article.title ?: "NO_TITLE",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
