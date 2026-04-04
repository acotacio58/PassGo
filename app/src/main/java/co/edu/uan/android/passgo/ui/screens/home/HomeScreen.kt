package co.edu.uan.android.passgo.ui.screens.home

import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.edu.uan.android.passgo.R

data class PasswordCategory(
    val title: String,
    val count: String,
    val iconRes: Int
)

data class PasswordItem(
    val name: String,
    val email: String,
    val logoRes: Int
)

@Composable
fun HomeScreen(
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val headerColor = Color(0xFF131314)
    val cardColor = Color(0xFF3A3A3A)
    val chipColor = Color(0xFF353535)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White
    val textSecondary = Color(0xFFBDBDBD)

    val categories = listOf(
        PasswordCategory("Redes Sociales", "14 contraseñas", R.drawable.icon_redes),
        PasswordCategory("Aplicaciones", "4 contraseñas", R.drawable.icon_aplicaciones),
        PasswordCategory("Cartera", "3 contraseñas", R.drawable.icon_cartera)
    )

    val passwords = listOf(
        PasswordItem("Figma", "usuario@correo.com", R.drawable.logo_figma),
        PasswordItem("Facebook", "usuario@correo.com", R.drawable.logo_facebook),
        PasswordItem("Instagram", "usuario@correo.com", R.drawable.logo_instagram),
        PasswordItem("Twitter", "usuario@correo.com", R.drawable.logo_twitter)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = headerColor,
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.daniela_avatar),
                            contentDescription = "Foto de Daniela",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Daniela",
                            color = whiteColor
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = whiteColor
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar",
                                tint = textSecondary
                            )
                        },
                        placeholder = {
                            Text("Buscar...", color = textSecondary)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = cardColor,
                            unfocusedContainerColor = cardColor,
                            focusedBorderColor = cardColor,
                            unfocusedBorderColor = cardColor,
                            focusedTextColor = whiteColor,
                            unfocusedTextColor = whiteColor,
                            disabledContainerColor = cardColor,
                            disabledBorderColor = cardColor,
                            disabledTextColor = whiteColor,
                            disabledPlaceholderColor = textSecondary,
                            disabledLeadingIconColor = textSecondary
                        )
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Administrar",
                        color = textSecondary
                    )

                    Text(
                        text = "Tus Contraseñas",
                        color = whiteColor,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        categories.forEach { category ->
                            CategoryCard(
                                title = category.title,
                                count = category.count,
                                iconRes = category.iconRes
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                ) {
                    Text(
                        text = "Mis contraseñas",
                        color = whiteColor,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilterChip("Todas", true, blueColor, chipColor)
                        Spacer(modifier = Modifier.width(10.dp))
                        FilterChip("Frecuentes", false, blueColor, chipColor)
                        Spacer(modifier = Modifier.width(10.dp))
                        FilterChip("Favoritas", false, blueColor, chipColor)

                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(blueColor, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Agregar",
                                tint = whiteColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            items(passwords) { item ->
                PasswordCard(item)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        BottomMenuBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedItem = "home",
            onHomeClick = onHomeClick,
            onPasswordsClick = onPasswordsClick,
            onGeneratorClick = onGeneratorClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
fun CategoryCard(
    title: String,
    count: String,
    iconRes: Int
) {
    val cardColor = Color(0xFF3A3A3A)
    val whiteColor = Color.White
    val secondaryText = Color(0xFFCCCCCC)

    Card(
        modifier = Modifier
            .width(108.dp)
            .height(110.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // ICONO
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Fit
            )

            // TEXTO
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = whiteColor,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = count,
                    color = secondaryText,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    blueColor: Color,
    chipColor: Color
) {
    val bg = if (selected) blueColor else chipColor

    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(10.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun PasswordCard(item: PasswordItem) {
    val cardColor = Color(0xFF3A3A3A)
    val whiteColor = Color.White
    val textSecondary = Color(0xFFD0D0D0)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.logoRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = item.name,
                    color = whiteColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.email,
                    color = textSecondary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Más opciones",
                    tint = whiteColor
                )
            }
        }
    }
}

@Composable
fun BottomMenuBar(
    modifier: Modifier = Modifier,
    selectedItem: String,
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
            selected = selectedItem == "home",
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
            selected = selectedItem == "passwords",
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
            selected = selectedItem == "generator",
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
            selected = selectedItem == "profile",
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