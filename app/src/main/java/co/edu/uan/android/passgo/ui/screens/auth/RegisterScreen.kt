package co.edu.uan.android.passgo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.uan.android.passgo.R
import androidx.compose.foundation.verticalScroll

@Composable
fun RegisterScreen(
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF1E1F20)
    val topCardColor = Color(0xFF131314)
    val fieldColor = Color(0xFF4A4A4A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
                .height(300.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = topCardColor,
                        shape = RoundedCornerShape(
                            bottomStart = 18.dp,
                            bottomEnd = 18.dp
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 34.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.passgo_logo),
                    contentDescription = "Logo PassGo",
                    modifier = Modifier
                        .width(180.dp)
                        .height(150.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tu bóveda digital personal",
                    color = blueColor,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(
                text = "Nombre",
                color = whiteColor,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = fieldColor,
                    unfocusedBorderColor = fieldColor,
                    focusedTextColor = whiteColor,
                    unfocusedTextColor = whiteColor,
                    cursorColor = whiteColor
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Apellido",
                color = whiteColor,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = fieldColor,
                    unfocusedBorderColor = fieldColor,
                    focusedTextColor = whiteColor,
                    unfocusedTextColor = whiteColor,
                    cursorColor = whiteColor
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Correo electrónico",
                color = whiteColor,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = fieldColor,
                    unfocusedBorderColor = fieldColor,
                    focusedTextColor = whiteColor,
                    unfocusedTextColor = whiteColor,
                    cursorColor = whiteColor
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Contraseña",
                color = whiteColor,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = fieldColor,
                    unfocusedBorderColor = fieldColor,
                    focusedTextColor = whiteColor,
                    unfocusedTextColor = whiteColor,
                    cursorColor = whiteColor
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(26.dp))

            Button(
                onClick = { },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blueColor
                )
            ) {
                Text(
                    text = "Registrarse",
                    color = whiteColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "o",
                color = whiteColor,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Iniciar Sesión",
                    color = whiteColor,
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}