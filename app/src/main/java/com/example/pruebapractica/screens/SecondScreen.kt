package com.example.pantallas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pantallas.navegacion.AppScreen
import com.example.pruebapractica.R


/**
 * Pantalla donde se muestran los datos introducidos por el usuario.
 *
 * @param navController Controlador de navegación para navegar entre pantallas.
 * @param nombre Dato a mostrar
 * @param apellidos Dato a mostrar
 * @param edad Dato a mostrar
 * @param telf Dato a mostrar
 * @param dni Dato a mostrar
 */
@Composable
fun SecondScreen(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){
    SecondBody(navController = navController, nombre, apellidos, edad, telf, dni)
}

/**
 * Cuerpo de la pantalla
 *
 * @param navController Controlador de navegación para navegar entre pantallas.
 * @param nombre Dato a mostrar
 * @param apellidos Dato a mostrar
 * @param edad Dato a mostrar
 * @param telf Dato a mostrar
 * @param dni Dato a mostrar
 */
@Composable
fun SecondBody(navController: NavController, nombre: String?, apellidos: String?, edad: String?, telf : String?, dni: String?){

    CabeceraDatos(navController)

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

@Composable
fun CabeceraDatos(navController: NavController){

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 30.dp)
            .background(color = colorResource(R.color.teal_700))
            .fillMaxWidth()
            .height(90.dp)

    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Atrás",
            tint = colorResource(R.color.white),
            modifier = Modifier
                .clickable { navController.navigate(route = AppScreen.FirstScreen.route) }
        )
        Spacer(Modifier.width(20.dp))
        Text(
            text = "Prueba Práctica - Formulario",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
