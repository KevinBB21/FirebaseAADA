package com.example.cursofirebaselite.presentation.add

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursofirebaselite.presentation.model.Album
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AddScreen(viewModel: AddAlbumViewModel = AddAlbumViewModel()) {
    // State variables for the form fields
    var name by remember { mutableStateOf("") }
    var numberOfPhotos by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    // State variable to show loading message
    var isLoading by remember { mutableStateOf(false) }

    // Get the local context to show Toast
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Agregar Nuevo Álbum", style = MaterialTheme.typography.titleLarge)

        // Name input field
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del Álbum") },
            modifier = Modifier.fillMaxWidth()
        )

        // Number of photos input field
        TextField(
            value = numberOfPhotos,
            onValueChange = { numberOfPhotos = it },
            label = { Text("Número de Fotos") },
            modifier = Modifier.fillMaxWidth(),
        )

        // Image URL input field
        TextField(
            value = image,
            onValueChange = { image = it },
            label = { Text("URL de la Imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        // Submit Button
        Button(
            onClick = {
                // Cuando el botón de enviar es clickeado, guardamos los datos del álbum
                if (name.isNotEmpty() && numberOfPhotos.isNotEmpty() && image.isNotEmpty()) {
                    val album = Album(
                        name = name,
                        numberOfPhotos = numberOfPhotos.toIntOrNull() ?: 0, // Convertir número de fotos de forma segura
                        imagen = image
                    )

                    // Usamos coroutineScope para llamar a la función suspendida
                    isLoading = true
                    viewModel.addAlbum(album) {
                        isLoading = false
                        if (it) {
                            Toast.makeText(context, "Álbum agregado con éxito", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error al agregar álbum", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(text = if (isLoading) "Cargando..." else "Agregar Álbum")
        }
    }
}

class AddAlbumViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    // Function to add the album to Firestore
    fun addAlbum(album: Album, onComplete: (Boolean) -> Unit) {
        // Ejecutar la función suspendida dentro de un coroutine
        viewModelScope.launch {
            try {
                // Obtener referencia a la colección de álbumes en Firestore
                val albumRef = db.collection("albums").document()

                // Añadir el álbum a Firestore
                albumRef.set(album).await()

                // Llamar a onComplete con éxito
                onComplete(true)
            } catch (e: Exception) {
                // Manejar errores (por ejemplo, problemas de red o errores de Firestore)
                e.printStackTrace()
                onComplete(false)
            }
        }
    }
}
