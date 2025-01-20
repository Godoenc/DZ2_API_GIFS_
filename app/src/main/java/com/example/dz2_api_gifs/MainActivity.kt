package com.example.dz2_api_gifs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dz2_api_gifs.GifsViewModel.Image
import com.example.dz2_api_gifs.GifsViewModel.MyViewModel
import com.example.dz2_api_gifs.GifsViewModel.UiState
import com.example.dz2_api_gifs.ui.theme.DZ2_API_GIFSTheme
import androidx.compose.foundation.lazy.grid.items
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DZ2_API_GIFSTheme {
                val uiState = viewModel.uiState.collectAsState()

                when (uiState.value) {
                    is UiState.Loading -> {
                        LoadingView()
                    }
                    is UiState.Success -> {
                        val images = (uiState.value as? UiState.Success)?.data
                        images?.let { ImageGrid(it) }
                    }
                    is UiState.Error -> {
                        ErrorView(retry = { viewModel.retry() })
                    }
                }
            }
        }
    }
}

@Composable
fun ImageGrid(images: List<Image>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(images) { image ->
            AsyncImage(
                model = image.url,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
        )
    }
}


@Composable
fun ErrorView(retry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = retry) {
            Text("Попробуйте снова")
        }
    }
}

