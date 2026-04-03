package co.edu.uan.android.uancasts.ui.screens.auth

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B0B))
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF151515),
                    shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
                )
                .padding(vertical = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Aquí luego pondremos el logo real
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color(0xFF1E88FF), shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PG",
                        color = Color.White,
                        fontSize = 28.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "PassGo",
                    color = Color(0xFF1E88FF),
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tu bóveda digital personal",
                    color = Color(0xFF1E88FF),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(Color(0xFFBDBDBD), CircleShape)
                        .border(3.dp, Color(0xFF42A5F5), CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Usuario",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF3A3A3A),
                    unfocusedContainerColor = Color(0xFF3A3A3A),
                    focusedBorderColor = Color(0xFF3A3A3A),
                    unfocusedBorderColor = Color(0xFF3A3A3A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Contraseña",
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF3A3A3A),
                    unfocusedContainerColor = Color(0xFF3A3A3A),
                    focusedBorderColor = Color(0xFF3A3A3A),
                    unfocusedBorderColor = Color(0xFF3A3A3A),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A5F5)
                )
            ) {
                Text(
                    text = "Iniciar Sesión",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "o",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            TextButton(
                onClick = onRegisterClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White
                )
            }
        }
    }
}