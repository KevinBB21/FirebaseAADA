package com.example.cursofirebaselite.presentation.home

import android.provider.MediaStore.Audio.Artists.Albums
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursofirebaselite.presentation.model.Album
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel:ViewModel() {

    private val _album = MutableStateFlow<List<Album>>(emptyList())
    val album: StateFlow<List<Album>> = _album
    private var db: FirebaseFirestore

    init {
        db = Firebase.firestore// Initialize Firestore
        getAlbums()

    }

    private fun getAlbums() {
        viewModelScope.launch {
            val albums: List<Album> = withContext(Dispatchers.IO) {
                getAllAlbums()
            }
                _album.value = albums
            }
        }



    suspend fun getAllAlbums():List<Album> {
        return try {
        db.collection("albums")
            .get()
            .await()
            .documents
            .mapNotNull { snapshot ->
                snapshot.toObject(Album::class.java)
            }} catch (e: Exception)
            {
                emptyList()
            }
    }
}
