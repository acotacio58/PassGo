package co.edu.uan.android.passgo.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object RegisterVerification : Routes("register_verification")
    object ForgotPassword : Routes("forgot_password")
    object ResetEmailSent : Routes("reset_email_sent")
    object ResetCode : Routes("reset_code")
    object Home : Routes("home")
    object Passwords : Routes("passwords")
    object Generator : Routes("generator")
    object Profile : Routes("profile")
    object AddEditCredential : Routes("add_edit_credential")
}