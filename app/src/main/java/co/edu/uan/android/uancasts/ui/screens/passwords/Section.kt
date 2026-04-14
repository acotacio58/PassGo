package co.edu.uan.android.uancasts.ui.screens.passwords

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Section(title: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Ver todo",
                color = Color(0xFF42A5F5),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        PasswordItem(name = "Figma")
        PasswordItem(name = "Facebook")
        PasswordItem(name = "Instagram")

        Spacer(modifier = Modifier.height(16.dp))
    }
}
