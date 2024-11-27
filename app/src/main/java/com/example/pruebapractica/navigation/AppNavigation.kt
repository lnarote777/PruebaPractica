package com.example.pantallas.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pantallas.screens.FirstScreen
import com.example.pantallas.screens.SecondScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.FirstScreen.route){
        composable(AppScreen.FirstScreen.route){
            FirstScreen(navController)
        }
//        composable(AppScreen.SecondScreen.route){
//            SecondScreen(navController)
//        }

        composable(AppScreen.SecondScreen.route + "/{nombre}"  + "/{apellidos}"  + "/{edad}" + "/{telf}" + "/{dni}",
            arguments = listOf(navArgument(name = "nombre"){
                type = NavType.StringType
            })
        ){
            SecondScreen(
                navController,
                it.arguments?.getString("nombre"),
                it.arguments?.getString("apellidos"),
                it.arguments?.getString("edad"),
                it.arguments?.getString("telf"),
                it.arguments?.getString("dni")
            )
        }
    }
}
