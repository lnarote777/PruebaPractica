package com.example.pantallas.navegacion

sealed class AppScreen (val route: String) {
    object FirstScreen: AppScreen("FirstScreen")
    object SecondScreen: AppScreen("SecondScreen")
}