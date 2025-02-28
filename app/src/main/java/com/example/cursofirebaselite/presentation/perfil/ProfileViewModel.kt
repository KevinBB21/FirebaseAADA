package com.example.cursofirebaselite.presentation.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursofirebaselite.presentation.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val user = auth.currentUser ?: return

        viewModelScope.launch {
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        document.toObject(UserProfile::class.java)?.let { profile ->
                            _userProfile.value = profile
                        }
                    }
                }
                .addOnFailureListener { e ->
                    println("Error al obtener el perfil: ${e.message}")
                }
        }
    }
}
