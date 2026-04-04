package co.edu.uan.android.uancasts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import co.edu.uan.android.uancasts.ui.screens.auth.*
import co.edu.uan.android.uancasts.ui.screens.home.HomeScreen
import co.edu.uan.android.uancasts.ui.screens.passwords.PasswordsScreen

object Routes {
    val Login = Screen("login")
    val Register = Screen("register")
    val ForgotPassword = Screen("forgot_password")
    val Home = Screen("home")
    val Passwords = Screen("passwords")
}

data class Screen(val route: String)

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Routes.Register.route)
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.ForgotPassword.route)
                }
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

        composable(Routes.Passwords.route) {
            PasswordsScreen()
        }
    }
}