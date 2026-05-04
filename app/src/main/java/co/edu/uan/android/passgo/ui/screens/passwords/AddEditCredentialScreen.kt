package co.edu.uan.android.passgo.ui.screens.passwords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import android.widget.Toast
import co.edu.uan.android.passgo.data.local.entity.CredentialEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditCredentialScreen(
    credential: CredentialEntity? = null,
    userId: Long,
    onSave: (CredentialEntity) -> Unit,
    onBack: () -> Unit
) {
    val backgroundColor = Color(0xFF1E1F20)
    val cardColor = Color(0xFF3A3A3A)
    val blueColor = Color(0xFF42A5F5)
    val whiteColor = Color.White

    var siteName by remember { mutableStateOf(credential?.siteName ?: "") }
    var siteUrl by remember { mutableStateOf(credential?.siteUrl ?: "") }
    var siteUsername by remember { mutableStateOf(credential?.siteUsername ?: "") }
    var sitePassword by remember { mutableStateOf(credential?.sitePassword ?: "") }
    var category by remember { mutableStateOf(credential?.category ?: "Redes Sociales") }
    var notes by remember { mutableStateOf(credential?.notes ?: "") }
    var showSuccessToast by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val categories = listOf("Redes Sociales", "Aplicaciones", "Cartera")

    LaunchedEffect(showSuccessToast) {
        if (showSuccessToast) {
            Toast.makeText(context, "Contraseña guardada con éxito", Toast.LENGTH_SHORT).show()
            showSuccessToast = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (credential == null) "Agregar Credencial" else "Editar Credencial", color = whiteColor) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = whiteColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
            )
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = siteName,
                onValueChange = { siteName = it },
                label = { Text("Nombre del Sitio", color = whiteColor) },
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
                )
            )

            OutlinedTextField(
                value = siteUrl,
                onValueChange = { siteUrl = it },
                label = { Text("URL del Sitio (opcional)", color = whiteColor) },
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
                )
            )

            OutlinedTextField(
                value = siteUsername,
                onValueChange = { siteUsername = it },
                label = { Text("Correo/Usuario", color = whiteColor) },
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
                )
            )

            OutlinedTextField(
                value = sitePassword,
                onValueChange = { sitePassword = it },
                label = { Text("Contraseña", color = whiteColor) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = cardColor,
                    unfocusedContainerColor = cardColor,
                    focusedBorderColor = blueColor,
                    unfocusedBorderColor = cardColor,
                    focusedTextColor = whiteColor,
                    unfocusedTextColor = whiteColor,
                    cursorColor = whiteColor
                )
            )

            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría", color = whiteColor) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = cardColor,
                        unfocusedContainerColor = cardColor,
                        focusedBorderColor = blueColor,
                        unfocusedBorderColor = cardColor,
                        focusedTextColor = whiteColor,
                        unfocusedTextColor = whiteColor,
                        cursorColor = whiteColor
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat, color = whiteColor) },
                            onClick = {
                                category = cat
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notas (opcional)", color = whiteColor) },
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
                minLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newCredential = CredentialEntity(
                        id = credential?.id ?: 0,
                        userId = userId,
                        siteName = siteName,
                        siteUrl = siteUrl.takeIf { it.isNotBlank() },
                        siteUsername = siteUsername,
                        sitePassword = sitePassword,
                        category = category,
                        notes = notes.takeIf { it.isNotBlank() }
                    )
                    onSave(newCredential)
                    showSuccessToast = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = blueColor),
                enabled = siteName.isNotBlank() && siteUsername.isNotBlank() && sitePassword.isNotBlank()
            ) {
                Text("Guardar", color = whiteColor)
            }
        }
    }
}