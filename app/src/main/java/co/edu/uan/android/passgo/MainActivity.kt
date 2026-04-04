package co.edu.uan.android.passgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.edu.uan.android.passgo.navigation.AppNavigation
import co.edu.uan.android.passgo.ui.theme.UANCastsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UANCastsTheme {
                AppNavigation()
            }
        }
    }
}