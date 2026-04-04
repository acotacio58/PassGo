package co.edu.uan.android.passgo.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object ForgotPassword : Routes("forgot_password")
    object Home : Routes("home")
    object Passwords : Routes("passwords")
    object Generator : Routes("generator")
}