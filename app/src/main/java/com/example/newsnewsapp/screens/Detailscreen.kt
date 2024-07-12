import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.newsnewsapp.R
import com.squareup.picasso.Picasso
import java.net.URLDecoder

@Composable
fun PicassoImage(url: String, modifier: Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            ImageView(context).apply {
                Picasso.get().load(url).into(this)
            }
        }
    )
}

@Composable
fun Detailscreen(navController: NavController, title: String, imageUrl: String, description: String, time: String, source: String) {
    val title2 = URLDecoder.decode(title,"UTF-8")
    val imageUrl2 =  URLDecoder.decode(imageUrl,"UTF-8")
//    val description2 = URLDecoder.decode(description,"UTF-8")
    val time2 = URLDecoder.decode(time,"UTF-8")
    val source2 = URLDecoder.decode(source,"UTF-8")

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
    {
        item {
            Text(
                text = title2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
        item {
            if (imageUrl2 !== null) {
                PicassoImage(
                    url = imageUrl2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 8.dp)
                )
            }
            else {
                PicassoImage(url = "https://pixsector.com/icon/free-image-icon-png-vector/891", modifier = Modifier)
            }
        }
        item {
            Text(
                text= description!!,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }


        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = time2,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = source2,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }


    }

}