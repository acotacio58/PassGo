package co.edu.uan.android.uancasts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import co.edu.uan.android.uancasts.ui.screens.auth.*
import co.edu.uan.android.uancasts.ui.screens.home.HomeScreen

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
            RegisterScreen {
                navController.popBackStack()
            }
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen {
                navController.popBackStack()
            }
        }

        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}