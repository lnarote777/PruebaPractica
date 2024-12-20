package com.example.pantallas.screens

import android.preference.PreferenceActivity.Header
import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pantallas.navegacion.AppScreen
import com.example.pruebapractica.R
import kotlin.coroutines.coroutineContext

/**
 * Pantalla principal de la aplicación que muestra el formulario.
 *
 * @param navController Controlador de navegación para navegar entre pantallas.
 */
@Composable
fun FirstScreen(navController: NavController){
    FirstBody(navController = navController)
}

/**
 * Cuerpo de la pantalla principal que contiene el encabezado y los campos del formulario.
 *
 * @param navController Controlador de navegación para navegar entre pantallas.
 */
@Composable
fun FirstBody(navController: NavController){
    var show by rememberSaveable { mutableStateOf(false) }

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var telf by rememberSaveable { mutableStateOf("") }
    var dni by rememberSaveable { mutableStateOf("") }

    Cabecera()

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

        MensajeError(show)

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.width(100.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_700)),
            onClick = {
                if (comprobarDatos(nombre, apellidos, edad, telf, dni)) {
                    show = true
                    nombre = ""
                    apellidos = ""
                    edad = ""
                    telf = ""
                    dni = ""
                } else {
                    //navega hacia la pantalla secundaria pasando los argumentos que debe mostrar
                    navController.navigate(route = AppScreen.SecondScreen.route + "/$nombre"  + "/$apellidos"  + "/$edad" + "/$telf" + "/$dni")
                }
            }
        ) {
            Text(
                text = "Enviar",
                fontSize = 20.sp
            )
        }

    }
}

/**
 * Encabezado de la pantalla principal.
 */
@Composable
fun Cabecera(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(top = 30.dp)
        .background(color = colorResource(R.color.teal_700)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Prueba Práctica - Formulario",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/**
 * Mensaje de error
 *
 * @param show Boolean para mostrar el mensaje de error
 */
@Composable
fun MensajeError(show: Boolean){
    if (show){
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
}

/**
 * Comprueba que los datos sean correctos
 *
 * @param nombre nombre del usuario
 * @param apellidos apellidos del usuario
 * @param edad edad del usuario
 * @param telf numero de teléfono del usuario
 * @param dni dni del usuario
 * @return True si el dato es incorrecto o false si todos los campos estan correctamente puestos
 */
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

/**
 * comprueba que el dni sea válido según el citerio que se usa para crear los Nif
 * @param dni String introducido por el usuario
 * @return true si es correcto false si es invalidos
 */
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

/**
 * Comprueba un telefono de 9 dígitos sin el prefijo del país
 * @param telf String introducido por el usuario
 * @return true si es correcto false si es invalidos
 */
fun validarTelf(telf: String): Boolean{
    return if (telf.length > 9 || telf.length < 9 || !telf.all{it.isDigit()}){
        false
    }else{
        true
    }
}

/**
 * Valida que la edad sea un número
 * @param edad String introducido por el usuario
 * @return true si es correcto false si es invalidos
 */
fun validarEdad(edad: String): Boolean{
    return if (edad.all { it.isDigit()}){
        true
    }else{
        false
    }
}
