package com.example.pantallas.screens

import android.widget.EditText
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pantallas.navegacion.AppScreen
import kotlin.coroutines.coroutineContext

@Composable
fun FirstScreen(navController: NavController){
    FirstBody(navController = navController)
}


@Composable
fun FirstBody(navController: NavController){
    var show by rememberSaveable { mutableStateOf(false) }

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var telf by rememberSaveable { mutableStateOf("") }
    var dni by rememberSaveable { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = apellidos,
            onValueChange = {apellidos = it},
            label = { Text("Apellidos") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = {edad = it},
            label = { Text("Edad") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = telf,
            onValueChange = {telf = it},
            label = { Text("Teléfono") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = dni,
            onValueChange = {dni = it},
            label = { Text("DNI") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        if (show) MensajeError()

        Button(
            onClick = {
                if (comprobarDatos(nombre, apellidos, edad, telf, dni)) {
                    show = true
                    nombre = ""
                    apellidos = ""
                    edad = ""
                    telf = ""
                    dni = ""
                } else {
                    navController.navigate(route = AppScreen.SecondScreen.route + "/$nombre"  + "/$apellidos"  + "/$edad" + "/$telf" + "/$dni")
                }
            }
        ) {
            Text("Enviar")
        }

    }
}


@Composable
fun MensajeError(){
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .shadow(elevation = 5.dp, ambientColor = Color.Red, shape = RoundedCornerShape(10.dp))
        .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = "No se pueden enviar datos vacíos o inválidos. Compruebe que los campos estén correctamente rellenados.",
            modifier = Modifier.padding(10.dp)
        )
    }
}


fun comprobarDatos(nombre: String,apellidos: String, edad: String, telf: String, dni: String): Boolean{
    return when {
        nombre.isBlank() -> true
        apellidos.isBlank() -> true
        !validarEdad(edad) -> true
        telf.isBlank() -> true
        edad.isBlank() -> true
        !validarTelf(telf) -> true
        !comprobarDni(dni) -> true
        else ->  false
    }
}

fun comprobarDni(dni: String): Boolean{
    return if(dni.length > 9 || dni.length < 9 || !dni.substring(0, 8).all{it.isDigit()}){
        false
    }else{
        val letrasValidas = listOf('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E')

        val numero = dni.substring(0, 8).toInt()
        val letraNif= dni.last().titlecaseChar()

        val resto= numero % 23

        if (letraNif != letrasValidas[resto]){
            false
        }else{
            true
        }
    }
}

fun validarTelf(telf: String): Boolean{
    return if (telf.length > 9 || telf.length < 9 || !telf.all{it.isDigit()}){
        false
    }else{
        true
    }
}

fun validarEdad(edad: String): Boolean{
    return if (edad.all { it.isDigit()}){
        true
    }else{
        false
    }
}
