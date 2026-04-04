package co.edu.uan.android.passgo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginClick = { navController.navigate(Routes.Home.route) },
                onRegisterClick = { navController.navigate(Routes.Register.route) },
                onForgotPasswordClick = { navController.navigate(Routes.ForgotPassword.route) }
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Generator.route) {
            GeneratorScreen(
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
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate(Routes.ResetEmailSent.route) }
            )
        }

        composable(Routes.ResetEmailSent.route) {
            ResetEmailSentScreen(
                onContinue = { navController.navigate(Routes.ResetCode.route) }
            )
        }

        composable(Routes.ResetCode.route) {
            ResetCodeScreen(
                onVerify = { navController.navigate(Routes.Login.route) },
                onResend = {}
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onHomeClick = {},
                onPasswordsClick = { navController.navigate(Routes.Passwords.route) },
                onGeneratorClick = { navController.navigate(Routes.Generator.route) },
                onProfileClick = { navController.navigate(Routes.Profile.route) }
            )
        }

        composable(Routes.Passwords.route) {
            PasswordsScreen(
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
                onProfileClick = {}
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}