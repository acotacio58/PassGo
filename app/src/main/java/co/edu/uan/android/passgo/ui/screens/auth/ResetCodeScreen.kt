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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.uan.android.passgo.R
import androidx.compose.foundation.verticalScroll

@Composable
fun ResetCodeScreen(
    onVerify: () -> Unit,
    onResend: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val topCardColor = Color(0xFF131314)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    val codeDigits = remember { mutableStateListOf("1", "2", "3", "4", "5", "6") }

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

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    codeDigits.forEach { digit ->
                        Box(
                            modifier = Modifier
                                .size(width = 30.dp, height = 32.dp)
                                .border(1.dp, whiteColor, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = digit,
                                color = whiteColor
                            )
                        }
                    }
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
                    onClick = onVerify,
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