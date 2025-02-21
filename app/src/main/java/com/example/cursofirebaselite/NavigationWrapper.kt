package com.example.cursofirebaselite

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursofirebaselite.presentation.initial.InitialScreen
import com.example.cursofirebaselite.presentation.login.LoginScreen
import com.example.cursofirebaselite.presentation.signup.SignupScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "initial"){
        composable("initial"){
            InitialScreen()
        }
        composable("login"){
            LoginScreen()
        }
        composable("signup"){
            SignupScreen()
        }
    }

}

fun composable(s: String, function: () -> Unit) {

}
