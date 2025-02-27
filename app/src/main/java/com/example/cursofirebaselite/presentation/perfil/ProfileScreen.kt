package com.example.cursofirebaselite.presentation.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = ProfileViewModel()) {
    val userProfile by viewModel.userProfile.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (userProfile != null) {
            Text(text = "Nombre: ${userProfile!!.name}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Correo: ${userProfile!!.email}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = userProfile!!.photoUrl,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(100.dp)
            )
        } else {
            Text(text = "Cargando perfil...", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

class ProfileViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val user = auth.currentUser
        user?.let {
            val uid = user.uid
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val profile = document.toObject(UserProfile::class.java)
                        _userProfile.value = profile
                    }
                }
                .addOnFailureListener {
                    // Manejar el error
                }
        }
    }

    fun updateUserProfile(name: String, photoUrl: String) {
        val user = auth.currentUser
        user?.let {
            val uid = user.uid
            val profile = UserProfile(uid, name, user.email ?: "", photoUrl)
            db.collection("users").document(uid).set(profile)
                .addOnSuccessListener {
                    _userProfile.value = profile
                }
                .addOnFailureListener {
                    // Manejar el error
                }
        }
    }
}
