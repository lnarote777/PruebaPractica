package com.example.pantallas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pantallas.navegacion.AppScreen


@Composable
fun SecondScreen(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){
    SecondBody(navController = navController, nombre, apellidos, edad, telf, dni)
}


@Composable
fun SecondBody(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //Se utiliza ?.let para controlar los nulos
        nombre?.let {
            Text(text = "Bienvenido/a $nombre $apellidos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        edad?.let {
            Text(text = "Edad: $edad",
                fontSize = 20.sp
            )
        }

        telf?.let {
            Text(text = "Teléfono de contacto: $telf",
                fontSize = 20.sp
            )
        }

        dni?.let {
            Text(text = "DNI: $dni",
                fontSize = 20.sp
            )
        }

    }
}
