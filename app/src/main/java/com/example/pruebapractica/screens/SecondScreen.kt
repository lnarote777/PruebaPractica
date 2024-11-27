package com.example.pantallas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pantallas.navegacion.AppScreen


@Composable
fun SecondScreen(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){
    SecondBody(navController = navController, nombre, apellidos, edad,telf, dni)
}


@Composable
fun SecondBody(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        nombre?.let {Text("Bienvenido/a $nombre $apellidos")}

        edad?.let {Text("Edad $edad")}

        telf?.let {Text("Tlfno: $telf")}

        dni?.let {Text("DNI: $dni")}




    }
}
