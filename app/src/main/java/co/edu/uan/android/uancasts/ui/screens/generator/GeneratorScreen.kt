package co.edu.uan.android.uancasts.ui.screens.generator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GeneratorScreen() {

    val password = remember { mutableStateOf("s0t5ibx234BS9e") }

    val upperCase = remember { mutableStateOf(true) }
    val lowerCase = remember { mutableStateOf(true) }
    val numbers = remember { mutableStateOf(true) }
    val special = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B0B))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Generar Contraseña",
            color = Color.White,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF2A2A2A),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = password.value,
                onValueChange = {},
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2A2A2A),
                    unfocusedContainerColor = Color(0xFF2A2A2A),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Generar",
                    tint = Color.White
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copiar",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Ajustes",
            color = Color(0xFF42A5F5),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Longitud de caracteres:",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        SettingRow(
            text = "Caracteres A-Z:",
            checked = upperCase.value
        ) { upperCase.value = it }

        SettingRow(
            text = "Caracteres a-z:",
            checked = lowerCase.value
        ) { lowerCase.value = it }

        SettingRow(
            text = "Caracteres 0-9:",
            checked = numbers.value
        ) { numbers.value = it }

        SettingRow(
            text = "Caracteres especiales:",
            checked = special.value
        ) { special.value = it }
    }
}

@Composable
fun SettingRow(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}