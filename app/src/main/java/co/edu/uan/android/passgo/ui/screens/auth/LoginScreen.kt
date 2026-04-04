package co.edu.uan.android.passgo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.uan.android.passgo.R

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val screenBackground = Color(0xFF1E1F20)
    val headerBlack = Color(0xFF131314)
    val lowerGray = Color(0xFF242526)
    val fieldColor = Color(0xFF4A4A4A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackground)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header negro
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(390.dp)
                        .background(
                            color = headerBlack,
                            shape = RoundedCornerShape(
                                bottomStart = 22.dp,
                                bottomEnd = 22.dp
                            )
                        ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 36.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.passgo_logo),
                            contentDescription = "Logo PassGo",
                            modifier = Modifier
                                .width(240.dp)
                                .height(220.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Tu bóveda digital personal",
                            color = blueColor,
                            fontSize = 16.sp
                        )
                    }
                }

                // Zona gris inferior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = lowerGray)
                        .padding(top = 72.dp, start = 20.dp, end = 20.dp, bottom = 28.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Usuario",
                            color = whiteColor,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = user,
                            onValueChange = { user = it },
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

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Contraseña",
                            color = whiteColor,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
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

                        Spacer(modifier = Modifier.height(6.dp))

                        TextButton(
                            onClick = onForgotPasswordClick,
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Text(
                                text = "¿Olvidaste tu contraseña?",
                                color = whiteColor,
                                fontSize = 12.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onLoginClick,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blueColor
                            )
                        ) {
                            Text(
                                text = "Iniciar Sesión",
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
                            onClick = onRegisterClick,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Registrarse",
                                color = whiteColor,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
            }

            // Avatar superpuesto entre negro y gris
            Image(
                painter = painterResource(id = R.drawable.user_avatar),
                contentDescription = "Avatar usuario",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 335.dp)
                    .clip(CircleShape)
                    .border(4.dp, blueColor, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}