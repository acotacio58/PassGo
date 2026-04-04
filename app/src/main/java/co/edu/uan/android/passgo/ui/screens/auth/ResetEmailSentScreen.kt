package co.edu.uan.android.passgo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.uan.android.passgo.R
import androidx.compose.foundation.verticalScroll

@Composable
fun ResetEmailSentScreen(
    onContinue: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val topCardColor = Color(0xFF131314)
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
                .height(760.dp),
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
                    .padding(top = 34.dp, start = 28.dp, end = 28.dp),
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

                Spacer(modifier = Modifier.height(90.dp))

                Text(
                    text = "Hemos enviado una solicitud a tu correo electrónico para restablecer tu contraseña, haz click en el siguiente botón para continuar con el restablecimiento:",
                    color = whiteColor,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(42.dp))

                Button(
                    onClick = onContinue,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueColor
                    )
                ) {
                    Text(
                        text = "Continuar",
                        color = whiteColor
                    )
                }
            }
        }
    }
}