package co.edu.uan.android.uancasts.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        // HEADER
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Daniela", color = Color.White, fontSize = 18.sp)

            Spacer(modifier = Modifier.weight(1f))

            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BUSCADOR
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar...", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2A2E35),
                unfocusedContainerColor = Color(0xFF2A2E35),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Administrar", color = Color.Gray, fontSize = 12.sp)
        Text("Tus Contraseñas", color = Color.White, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // CATEGORÍAS
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CategoryItem("Redes Sociales", "14 contraseñas", Icons.Default.Group)
            CategoryItem("Aplicaciones", "4 contraseñas", Icons.Default.Apps)
            CategoryItem("Cartera", "3 contraseñas", Icons.Default.AccountBalanceWallet)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // TABS + BOTÓN
        Row(verticalAlignment = Alignment.CenterVertically) {

            TabItem("Todas", true)
            Spacer(modifier = Modifier.width(8.dp))

            TabItem("Frecuentes", false)
            Spacer(modifier = Modifier.width(8.dp))

            TabItem("Favoritas", false)

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFF42A5F5), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LISTA
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PasswordItem("Figma")
            PasswordItem("Facebook")
            PasswordItem("Instagram")
            PasswordItem("Twitter")
        }
    }
}