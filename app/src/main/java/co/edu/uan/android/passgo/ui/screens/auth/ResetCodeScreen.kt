package co.edu.uan.android.passgo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateListOf
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
fun ResetCodeScreen(
    errorMessage: String?,
    onVerify: (String, String) -> Unit,
    onResend: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val topCardColor = Color(0xFF131314)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    var code by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

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
                .padding(horizontal = 10.dp)
                .height(720.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = topCardColor,
                        shape = RoundedCornerShape(18.dp)
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 34.dp, start = 24.dp, end = 24.dp),
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

                Spacer(modifier = Modifier.height(70.dp))

                Text(
                    text = "Introduce el código de verificación:",
                    color = whiteColor,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = topCardColor,
                        unfocusedContainerColor = topCardColor,
                        focusedBorderColor = whiteColor,
                        unfocusedBorderColor = whiteColor,
                        focusedTextColor = whiteColor,
                        unfocusedTextColor = whiteColor,
                        cursorColor = whiteColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Nueva contraseña:",
                    color = whiteColor,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = topCardColor,
                        unfocusedContainerColor = topCardColor,
                        focusedBorderColor = whiteColor,
                        unfocusedBorderColor = whiteColor,
                        focusedTextColor = whiteColor,
                        unfocusedTextColor = whiteColor,
                        cursorColor = whiteColor
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (!errorMessage.isNullOrEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                TextButton(onClick = onResend) {
                    Text(
                        text = "¿No recibiste el código? Reenviar",
                        color = whiteColor,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                Button(
                    onClick = { onVerify(code, newPassword) },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueColor
                    )
                ) {
                    Text(
                        text = "Verificar Código",
                        color = whiteColor
                    )
                }
            }
        }
    }
}