package com.example.cursofirebaselite.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cursofirebaselite.ui.theme.Black
import com.example.cursofirebaselite.presentation.model.Album
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel(), navController: NavController) {
    val albums: State<List<Album>> = viewModel.album.collectAsState()

    // Usamos Box para permitir el posicionamiento de los elementos
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        // Fila superior con el título y el botón de usuario
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Albums Populares",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                // Botón para ir a ProfileScreen
                Button(onClick = { navController.navigate("profile") }) {
                    Text("Usuario")
                }
            }

            LazyRow {
                items(albums.value) {
                    AlbumItem(it)
                }
            }
        }

        // Botón flotante para añadir un álbum
        FloatingActionButton(
            onClick = { navController.navigate("add_album") }, // Aquí se navega a la pantalla de agregar álbum
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd), // Lo coloca en la esquina inferior derecha
            contentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Añadir Álbum")
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
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
