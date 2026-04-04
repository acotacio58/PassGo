package co.edu.uan.android.passgo.ui.screens.generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.edu.uan.android.passgo.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.KeyboardArrowUp

@Composable
fun GeneratorScreen(
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val fieldColor = Color(0xFF3A3A3A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White
    val secondaryText = Color(0xFFBDBDBD)

    var passwordText by remember { mutableStateOf("s0t5ibx234BS9e") }
    var length by remember { mutableIntStateOf(14) }

    var upperCase by remember { mutableStateOf(true) }
    var lowerCase by remember { mutableStateOf(true) }
    var numbers by remember { mutableStateOf(true) }
    var specialChars by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp, vertical = 24.dp)
                .padding(bottom = 90.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Generar Contraseña",
                color = whiteColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(fieldColor, RoundedCornerShape(10.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = passwordText,
                    color = whiteColor,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_generador),
                        contentDescription = "Regenerar",
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copiar",
                        tint = whiteColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Ajustes",
                color = blueColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Longitud de caracteres:",
                    color = whiteColor,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    modifier = Modifier
                        .background(fieldColor, RoundedCornerShape(10.dp))
                        .padding(start = 12.dp, end = 8.dp, top = 6.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = length.toString(),
                        color = whiteColor
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                if (length < 32) length++
                            },
                            modifier = Modifier.size(18.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Aumentar longitud",
                                tint = blueColor
                            )
                        }

                        IconButton(
                            onClick = {
                                if (length > 4) length--
                            },
                            modifier = Modifier.size(18.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Disminuir longitud",
                                tint = blueColor
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            GeneratorSwitchRow(
                text = "Caracteres A-Z:",
                checked = upperCase,
                onCheckedChange = { upperCase = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            GeneratorSwitchRow(
                text = "Caracteres a-z:",
                checked = lowerCase,
                onCheckedChange = { lowerCase = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            GeneratorSwitchRow(
                text = "Caracteres 0-9:",
                checked = numbers,
                onCheckedChange = { numbers = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            GeneratorSwitchRow(
                text = "Caracteres especiales:",
                checked = specialChars,
                onCheckedChange = { specialChars = it }
            )
        }

        GeneratorBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = onHomeClick,
            onPasswordsClick = onPasswordsClick,
            onGeneratorClick = onGeneratorClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
fun GeneratorSwitchRow(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val whiteColor = Color.White
    val blueColor = Color(0xFF42A5F5)
    val switchOffColor = Color(0xFF3A3A3A)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = whiteColor,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = blueColor,
                checkedTrackColor = switchOffColor,
                uncheckedThumbColor = blueColor,
                uncheckedTrackColor = switchOffColor
            )
        )
    }
}

@Composable
fun GeneratorBottomBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val backgroundColor = Color(0xFF111111)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        containerColor = backgroundColor
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Principal")
            },
            label = { Text("Principal") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blueColor,
                selectedTextColor = blueColor,
                indicatorColor = Color.Transparent,
                unselectedIconColor = whiteColor,
                unselectedTextColor = whiteColor
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = onPasswordsClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_contrasenas),
                    contentDescription = "Contraseñas",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Contraseñas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blueColor,
                selectedTextColor = blueColor,
                indicatorColor = Color.Transparent,
                unselectedIconColor = whiteColor,
                unselectedTextColor = whiteColor
            )
        )

        NavigationBarItem(
            selected = true,
            onClick = onGeneratorClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_generador),
                    contentDescription = "Generador",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Generador") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blueColor,
                selectedTextColor = blueColor,
                indicatorColor = Color.Transparent,
                unselectedIconColor = whiteColor,
                unselectedTextColor = whiteColor
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            },
            label = { Text("Perfil") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = blueColor,
                selectedTextColor = blueColor,
                indicatorColor = Color.Transparent,
                unselectedIconColor = whiteColor,
                unselectedTextColor = whiteColor
            )
        )
    }
}