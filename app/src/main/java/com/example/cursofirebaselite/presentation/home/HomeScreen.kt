package com.example.cursofirebaselite.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.ui.theme.Black

import com.example.cursofirebaselite.presentation.model.Album
import com.google.firebase.firestore.FirebaseFirestore

//Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {

    val albums: State<List<Album>> = viewModel.album.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
    ){
        Text("Albums Populares",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )



        LazyRow {
            items(albums.value){
                AlbumItem(it)
           }
        }
    }

}


@Composable
fun AlbumItem(album: Album){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = album.imagen,
                contentDescription = "Album",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = album.name.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${album.numberOfPhotos} fotos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



@Composable
fun AlbumItemPreview(){
    val album = Album(
        "Album 1",
        numberOfPhotos = 10,
        imagen = "https://cdn.pixabay.com/photo/2019/12/24/13/20/dogs-4716738_640.jpg"

    )
    AlbumItem(album = album)
}




/*
fun createAlbum(db: FirebaseFirestore) {
    val random = (1 until 1000).random()
    val album = Album("Album $random", numberOfPhotos =  random)
    db.collection("albums")
        .add(album)
        .addOnSuccessListener { documentReference ->
            println("DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding document $e")
        }
        .addOnCompleteListener(){
            println("Task completed")

      }
}

*/