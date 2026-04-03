package co.edu.uan.android.uancasts.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object ForgotPassword : Routes("forgot_password")
    object Home : Routes("home")
}