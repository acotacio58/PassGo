package co.edu.uan.android.passgo.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterVerificationScreen(
    email: String,
    verificationCode: String,
    onSendEmail: () -> Unit,
    onVerify: (String) -> Unit,
    onResend: () -> Unit,
    onBack: () -> Unit,
    errorMessage: String? = null
) {
    var code by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF1E1F20)
    val cardColor = Color(0xFF3A3A3A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Verificación de Correo",
            color = whiteColor,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Se ha enviado un código de verificación a $email",
            color = whiteColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (verificationCode.isBlank()) {
                "Presiona el botón para solicitar el correo de verificación."
            } else {
                "Presiona el botón para abrir tu aplicación de correo y ver el código."
            },
            color = whiteColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSendEmail,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = cardColor)
        ) {
            Text("Abrir aplicación de correo", color = whiteColor)
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Código de verificación", color = whiteColor) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = cardColor,
                unfocusedContainerColor = cardColor,
                focusedBorderColor = blueColor,
                unfocusedBorderColor = cardColor,
                focusedTextColor = whiteColor,
                unfocusedTextColor = whiteColor,
                cursorColor = whiteColor
            ),
            singleLine = true
        )

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onVerify(code) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blueColor)
        ) {
            Text("Verificar", color = whiteColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onResend) {
            Text("Reenviar código", color = blueColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onBack) {
            Text("Volver", color = whiteColor)
        }
    }
}