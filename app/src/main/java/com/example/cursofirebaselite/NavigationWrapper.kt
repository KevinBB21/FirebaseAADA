package com.example.cursofirebaselite

import android.provider.ContactsContract.Profile
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aristidevs.cursofirebaselite.presentation.login.LoginScreen
import com.example.cursofirebaselite.presentation.home.HomeScreen
import com.example.cursofirebaselite.presentation.initial.InitialScreen
import com.example.cursofirebaselite.presentation.perfil.ProfileScreen
import com.example.cursofirebaselite.presentation.perfil.ProfileViewModel
import com.example.cursofirebaselite.presentation.signup.SignupScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth
) {

    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("signup") })
        }
        composable("login") {
            LoginScreen(auth, navHostController){ navHostController.navigate("home") }
        }
        composable("signup") {
            SignupScreen(auth, navHostController)
        }
        composable("home"){
            HomeScreen()
        }
        composable("profile"){
            ProfileScreen()
        }
    }
}