package co.edu.uan.android.passgo.ui.screens.profile

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.edu.uan.android.passgo.R
import androidx.compose.ui.graphics.ColorFilter

@Composable
fun ProfileScreen(
    username: String,
    email: String,
    firstName: String,
    lastName: String,
    profileImageUri: String?,
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: ((String) -> Unit) -> Unit,
    onChangeProfileImage: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val cardColor = Color(0xFF3A3A3A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White
    val secondaryText = Color(0xFFE0E0E0)
    val redColor = Color(0xFFFF1E1E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = 90.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Perfil",
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

        Spacer(modifier = Modifier.height(8.dp))

        val context = LocalContext.current
        val profileBitmap = remember(profileImageUri) {
            profileImageUri?.let { uriString ->
                try {
                    val uri = Uri.parse(uriString)
                    ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(context.contentResolver, uri)
                    )
                } catch (_: Exception) {
                    null
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (profileBitmap != null) {
                Image(
                    bitmap = profileBitmap.asImageBitmap(),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.perfil_usuario),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar foto",
                tint = blueColor,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 120.dp)
                    .size(24.dp)
                    .clickable { onChangeProfileImage() }
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .background(cardColor, RoundedCornerShape(18.dp))
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Text(
                text = "$firstName $lastName",
                color = whiteColor,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = email,
                    color = secondaryText,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar correo principal",
                    tint = blueColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.White)

            Spacer(modifier = Modifier.height(18.dp))

            ProfileField("Nombre", firstName, true)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.White)

            Spacer(modifier = Modifier.height(18.dp))

            ProfileField("Apellido", lastName, true)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.White)

            Spacer(modifier = Modifier.height(18.dp))

            ProfileField("Correo electrónico", email, true)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.White)

            Spacer(modifier = Modifier.height(18.dp))

            ProfileField("Contraseña maestra", "***************", false)
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.White)

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onLogout,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueColor
                    )
                ) {
                    Text(
                        text = "Cerrar Sesión",
                        color = whiteColor
                    )
                }

                Button(
                    onClick = {
                        onDeleteAccount { error ->
                            println(error)
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = redColor
                    )
                ) {
                    Text(
                        text = "Eliminar Cuenta",
                        color = whiteColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    ProfileBottomBar(
        modifier = Modifier.fillMaxWidth(),
        onHomeClick = onHomeClick,
        onPasswordsClick = onPasswordsClick,
        onGeneratorClick = onGeneratorClick,
        onProfileClick = onProfileClick
    )
}

@Composable
fun ProfileField(
    label: String,
    value: String,
    editable: Boolean
) {
    val whiteColor = Color.White
    val blueColor = Color(0xFF42A5F5)
    val secondaryText = Color(0xFFE0E0E0)

    Column {
        Text(
            text = label,
            color = secondaryText
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                color = whiteColor,
                modifier = Modifier.weight(1f)
            )

            if (editable) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar $label",
                    tint = blueColor,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visibility),
                    contentDescription = "Ver contraseña",
                    tint = blueColor,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileBottomBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onPasswordsClick: () -> Unit,
    onGeneratorClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val backgroundColor = Color(0xFF111111)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationBar(
            modifier = modifier
                .align(Alignment.BottomCenter)
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
                selected = true,
                onClick = onProfileClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = blueColor
                    )
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
}
