package co.edu.uan.android.passgo.navigation

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.uan.android.passgo.data.viewmodel.PassGoViewModel
import co.edu.uan.android.passgo.ui.screens.auth.ForgotPasswordScreen
import co.edu.uan.android.passgo.ui.screens.auth.LoginScreen
import co.edu.uan.android.passgo.ui.screens.auth.RegisterScreen
import co.edu.uan.android.passgo.ui.screens.auth.RegisterVerificationScreen
import co.edu.uan.android.passgo.ui.screens.auth.ResetEmailSentScreen
import co.edu.uan.android.passgo.ui.screens.home.HomeScreen
import co.edu.uan.android.passgo.ui.screens.passwords.PasswordsScreen
import co.edu.uan.android.passgo.ui.screens.generator.GeneratorScreen
import co.edu.uan.android.passgo.ui.screens.profile.ProfileScreen
import co.edu.uan.android.passgo.ui.screens.auth.ResetCodeScreen
import co.edu.uan.android.passgo.ui.screens.passwords.AddEditCredentialScreen

@Composable
fun AppNavigation(
    viewModel: PassGoViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                errorMessage = viewModel.authMessage,
                onLoginClick = { user, password ->
                    viewModel.login(user, password) {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.Register.route) },
                onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) }
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                errorMessage = viewModel.authMessage,
                onRegister = { firstName, lastName, email, password ->
                    viewModel.register(firstName, lastName, email, password) {
                        // Después de registrar, navegar a verificación
                        navController.navigate(Routes.RegisterVerification.route)
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.RegisterVerification.route) {
            val context = LocalContext.current
            val email = viewModel.currentUser?.email ?: ""
            val verificationCode = viewModel.verificationCode ?: ""
            fun sendVerificationEmail() {
                if (email.isBlank() || verificationCode.isBlank()) return
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, "Código de verificación PassGo")
                    putExtra(Intent.EXTRA_TEXT, "Tu código de verificación es: $verificationCode")
                    setPackage("com.google.android.gm") // Específico para Gmail
                }
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    // Fallback a cualquier app de correo
                    val fallbackIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                        putExtra(Intent.EXTRA_SUBJECT, "Código de verificación PassGo")
                        putExtra(Intent.EXTRA_TEXT, "Tu código de verificación es: $verificationCode")
                    }
                    if (fallbackIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(fallbackIntent)
                    }
                }
            }

            RegisterVerificationScreen(
                email = email,
                verificationCode = verificationCode,
                onSendEmail = { sendVerificationEmail() },
                onVerify = { code ->
                    viewModel.verifyEmailCode(code) {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onResend = {
                    viewModel.resendVerificationCode {
                        sendVerificationEmail()
                    }
                },
                onBack = { navController.popBackStack() },
                errorMessage = viewModel.verificationMessage
            )
        }

        composable(Routes.Generator.route) {
            GeneratorScreen(
                generatedPassword = viewModel.generatedPassword,
                onGenerate = { length, uppercase, lowercase, digits, symbols ->
                    viewModel.generatePassword(length, uppercase, lowercase, digits, symbols)
                },
                onSaveGeneratedPassword = { siteName, siteUsername ->
                    viewModel.saveGeneratedPassword(siteName, null, siteUsername, "Aplicaciones", null)
                },
                onHomeClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
                onPasswordsClick = {
                    navController.navigate(Routes.Passwords.route) {
                        popUpTo(Routes.Passwords.route) { inclusive = true }
                    }
                },
                onGeneratorClick = {},
                onProfileClick = { navController.navigate(Routes.Profile.route) }
            )
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(
                errorMessage = viewModel.recoveryMessage,
                onContinue = { email ->
                    viewModel.requestPasswordRecovery(email) {
                        navController.navigate(Routes.ResetEmailSent.route)
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ResetEmailSent.route) {
            ResetEmailSentScreen(
                onContinue = { navController.navigate(Routes.ResetCode.route) }
            )
        }

        composable(Routes.ResetCode.route) {
            ResetCodeScreen(
                errorMessage = viewModel.recoveryMessage,
                onVerify = { code, newPassword ->
                    viewModel.resetPassword(code, newPassword) {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onResend = {
                    viewModel.showRecoveryResendMessage()
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                username = viewModel.currentUser?.username ?: "Usuario",
                credentialsCount = viewModel.credentials.size,
                categoryCounts = viewModel.categoryCounts,
                credentials = viewModel.credentials,
                favoriteCredentials = viewModel.favoriteCredentials,
                onHomeClick = {},
                onPasswordsClick = { navController.navigate(Routes.Passwords.route) },
                onGeneratorClick = { navController.navigate(Routes.Generator.route) },
                onProfileClick = { navController.navigate(Routes.Profile.route) },
                onAddCredential = { navController.navigate(Routes.AddEditCredential.route) },
                onEditCredential = { credential ->
                    // TODO: Pasar credential a la pantalla
                    navController.navigate(Routes.AddEditCredential.route)
                },
                onToggleFavorite = { credential ->
                    viewModel.toggleFavorite(credential)
                }
            )
        }

        composable(Routes.Passwords.route) {
            PasswordsScreen(
                items = viewModel.credentials,
                onHomeClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
                onPasswordsClick = {},
                onGeneratorClick = { navController.navigate(Routes.Generator.route) },
                onProfileClick = { navController.navigate(Routes.Profile.route) },
                onViewAll = { category ->
                    // TODO: Implementar vista de categoría completa
                },
                onEditCredential = { credential ->
                    // TODO: Pasar credential a edición
                    navController.navigate(Routes.AddEditCredential.route)
                }
            )
        }

        composable(Routes.Profile.route) {
            val user = viewModel.currentUser
            val parts = user?.username?.split("_") ?: listOf("", "")
            val firstName = parts.getOrNull(0)?.replaceFirstChar { it.uppercase() } ?: ""
            val lastName = parts.getOrNull(1)?.replaceFirstChar { it.uppercase() } ?: ""
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    viewModel.updateProfileImage(it.toString())
                }
            }

            ProfileScreen(
                username = user?.username ?: "Usuario",
                email = user?.email ?: "",
                firstName = firstName,
                lastName = lastName,
                profileImageUri = user?.profileImageUri,
                onHomeClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                },
                onPasswordsClick = {
                    navController.navigate(Routes.Passwords.route) {
                        popUpTo(Routes.Passwords.route) { inclusive = true }
                    }
                },
                onGeneratorClick = {
                    navController.navigate(Routes.Generator.route) {
                        popUpTo(Routes.Generator.route) { inclusive = true }
                    }
                },
                onProfileClick = {},
                onLogout = {
                    viewModel.logout {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onDeleteAccount = { onError ->
                    viewModel.deleteAccount({
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }, onError)
                },
                onChangeProfileImage = {
                    launcher.launch("image/*")
                }
            )
        }

        composable(Routes.AddEditCredential.route) {
            AddEditCredentialScreen(
                credential = null, // TODO: Pasar credential si es edición
                userId = viewModel.currentUser?.id ?: 0,
                onSave = { credential ->
                    if (credential.id == 0L) {
                        viewModel.addCredential(
                            credential.siteName,
                            credential.siteUrl,
                            credential.siteUsername,
                            credential.sitePassword,
                            credential.category,
                            credential.notes
                        )
                    } else {
                        viewModel.updateCredential(credential)
                    }
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
