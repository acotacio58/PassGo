package co.edu.uan.android.uancasts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.uan.android.uancasts.ui.screens.auth.ForgotPasswordScreen
import co.edu.uan.android.uancasts.ui.screens.auth.LoginScreen
import co.edu.uan.android.uancasts.ui.screens.auth.RegisterScreen
import co.edu.uan.android.uancasts.ui.screens.home.HomeScreen
import co.edu.uan.android.uancasts.ui.screens.passwords.PasswordsScreen

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

        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onHomeClick = {},
                onPasswordsClick = { navController.navigate(Routes.Passwords.route) },
                onGeneratorClick = {},
                onProfileClick = {}
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
                onGeneratorClick = {},
                onProfileClick = {}
            )
        }
    }
}