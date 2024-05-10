package com.example.movies

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.retrofit.ApiConstants
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.viewmodels.MainViewModel
import org.xmlpull.v1.XmlPullParser

class MainActivity : ComponentActivity() {

    private val mainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getMovies()

        setContent {

            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(mainViewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(mainViewModel: MainViewModel) {
    val mContext = LocalContext.current
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            if (mainViewModel.moviesList.size > 0) {
                items(mainViewModel.moviesList) { item ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxWidth(1f),
                                model = ImageRequest.Builder(mContext)
                                    .error(
                                        R.drawable.image_not_available
                                    )
                                    .data("${ApiConstants.BASE_URL.substringBeforeLast('/')}${item.posterPath}")
                                    .build(),
                                contentDescription = item.name
                            )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.name ?: "", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = item.overview ?: "", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            } else {
                item {
                    CircularProgressIndicator()
                }
            }
            item {
                Button(onClick = { mainViewModel.getMovies() }) {
                    Text(text = "Load More")
                }
            }
        }
    }
}