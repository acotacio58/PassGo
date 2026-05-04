package co.edu.uan.android.passgo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.uan.android.passgo.data.viewmodel.PassGoViewModel
import co.edu.uan.android.passgo.ui.screens.auth.ForgotPasswordScreen
import co.edu.uan.android.passgo.ui.screens.auth.LoginScreen
import co.edu.uan.android.passgo.ui.screens.auth.RegisterScreen
import co.edu.uan.android.passgo.ui.screens.home.HomeScreen
import co.edu.uan.android.passgo.ui.screens.passwords.PasswordsScreen
import co.edu.uan.android.passgo.ui.screens.generator.GeneratorScreen
import co.edu.uan.android.passgo.ui.screens.profile.ProfileScreen
import co.edu.uan.android.passgo.ui.screens.auth.ResetCodeScreen
import co.edu.uan.android.passgo.ui.screens.auth.ResetEmailSentScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: PassGoViewModel = viewModel()

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
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Generator.route) {
            GeneratorScreen(
                generatedPassword = viewModel.generatedPassword,
                onGenerate = { length, uppercase, lowercase, digits, symbols ->
                    viewModel.generatePassword(length, uppercase, lowercase, digits, symbols)
                },
                onSaveGeneratedPassword = { siteName, siteUsername ->
                    viewModel.saveGeneratedPassword(siteName, null, siteUsername, null)
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
                onHomeClick = {},
                onPasswordsClick = { navController.navigate(Routes.Passwords.route) },
                onGeneratorClick = { navController.navigate(Routes.Generator.route) },
                onProfileClick = { navController.navigate(Routes.Profile.route) }
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
                onProfileClick = { navController.navigate(Routes.Profile.route) }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen(
                username = viewModel.currentUser?.username ?: "Usuario",
                email = viewModel.currentUser?.email ?: "",
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
                }
            )
        }
    }
}
