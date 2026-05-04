package co.edu.uan.android.passgo.ui.screens.passwords

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.ColorFilter
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity

data class PasswordEntry(
    val name: String,
    val email: String,
    val logoRes: Int
)

@Composable
fun PasswordsScreen(
    items: List<CredentialEntity>,
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit,
    onViewAll: (String) -> Unit,
    onEditCredential: (CredentialEntity) -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val cardColor = Color(0xFF3A3A3A)
    val whiteColor = Color.White
    val secondaryText = Color(0xFFBDBDBD)

    val socialCredentials = items.filter { it.category == "Redes Sociales" }
    val appCredentials = items.filter { it.category == "Aplicaciones" }
    val walletCredentials = items.filter { it.category == "Cartera" }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Contraseñas",
                            color = whiteColor,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificaciones",
                            tint = whiteColor
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar",
                                tint = secondaryText
                            )
                        },
                        placeholder = {
                            Text("Buscar...", color = secondaryText)
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
                            disabledPlaceholderColor = secondaryText,
                            disabledLeadingIconColor = secondaryText
                        )
                    )

                    Spacer(modifier = Modifier.height(18.dp))
                }
            }

            if (socialCredentials.isNotEmpty()) {
                item {
                    PasswordSection(
                        title = "Redes Sociales",
                        items = socialCredentials,
                        onViewAll = { onViewAll("Redes Sociales") },
                        onEditCredential = onEditCredential
                    )
                }
            }

            if (appCredentials.isNotEmpty()) {
                item {
                    PasswordSection(
                        title = "Aplicaciones",
                        items = appCredentials,
                        onViewAll = { onViewAll("Aplicaciones") },
                        onEditCredential = onEditCredential
                    )
                }
            }

            if (walletCredentials.isNotEmpty()) {
                item {
                    PasswordSection(
                        title = "Cartera",
                        items = walletCredentials,
                        onViewAll = { onViewAll("Cartera") },
                        onEditCredential = onEditCredential
                    )
                }
            }
        }

        PasswordsBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = onHomeClick,
            onPasswordsClick = onPasswordsClick,
            onGeneratorClick = onGeneratorClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
fun PasswordSection(
    title: String,
    items: List<CredentialEntity>,
    onViewAll: () -> Unit,
    onEditCredential: (CredentialEntity) -> Unit
) {
    val whiteColor = Color.White
    val blueColor = Color(0xFF42A5F5)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = whiteColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(onClick = onViewAll) {
                Text(
                    text = "Ver todo",
                    color = blueColor
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        items.forEach { item ->
            PasswordAppCard(item, onEditCredential)
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun PasswordAppCard(item: CredentialEntity, onEdit: (CredentialEntity) -> Unit) {
    val cardColor = Color(0xFF3A3A3A)
    val whiteColor = Color.White
    val secondaryText = Color(0xFFD0D0D0)

    Card(
        modifier = Modifier.fillMaxWidth(),
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
                painter = painterResource(id = getLogoForSite(item.siteName)),
                contentDescription = item.siteName,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = item.siteName,
                    color = whiteColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.siteUsername,
                    color = secondaryText
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { onEdit(item) }) {
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
fun PasswordsBottomBar(
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
            selected = true,
            onClick = onPasswordsClick,
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_contrasenas),
                    contentDescription = "Contraseñas",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(blueColor)
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
            selected = false,
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

fun getLogoForSite(siteName: String): Int {
    return when (siteName.lowercase()) {
        "facebook" -> R.drawable.logo_facebook
        "instagram" -> R.drawable.logo_instagram
        "twitter", "x" -> R.drawable.logo_twitter
        "figma" -> R.drawable.logo_figma
        else -> R.drawable.logo_figma // default
    }
}
