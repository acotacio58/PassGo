package co.edu.uan.android.passgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import co.edu.uan.android.passgo.data.viewmodel.PassGoViewModel
import co.edu.uan.android.passgo.navigation.AppNavigation
import co.edu.uan.android.passgo.ui.theme.UANCastsTheme

class MainActivity : ComponentActivity() {

    private val passGoViewModel: PassGoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UANCastsTheme {
                AppNavigation(viewModel = passGoViewModel)
            }
        }
    }
}
