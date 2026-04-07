package co.edu.uan.android.uancasts.ui.screens.profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B0B))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Perfil",
                color = Color.White,
                fontSize = 22.sp
            )

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.size(130.dp),
                contentAlignment = Alignment.BottomEnd
            ) {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.LightGray, CircleShape)
                        .border(3.dp, Color.White, CircleShape)
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFF42A5F5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar foto",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF3A3A3A),
                    RoundedCornerShape(16.dp)
                )
                .padding(20.dp)
        ) {

            Text(
                text = "Daniela Laurent",
                color = Color.White,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "usuario@correo.com",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = Color(0xFF42A5F5)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            DividerLine()

            ProfileField(
                label = "Nombre",
                value = "Daniela Paola"
            )

            ProfileField(
                label = "Apellido",
                value = "Laurent Rousseau"
            )

            ProfileField(
                label = "Correo electrónico",
                value = "usuario@correo.com"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Contraseña maestra",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "***************",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Ver contraseña",
                    tint = Color(0xFF42A5F5)
                )
            }

            DividerLine()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF42A5F5)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Text(
                        text = "Cerrar Sesión",
                        color = Color.White
                    )
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD50000)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    Text(
                        text = "Eliminar Cuenta",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    value: String
) {

    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {

        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = value,
                color = Color.White,
                fontSize = 16.sp
            )

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Color(0xFF42A5F5)
            )
        }

        DividerLine()
    }
}

@Composable
fun DividerLine() {

    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )

    Spacer(modifier = Modifier.height(6.dp))
}