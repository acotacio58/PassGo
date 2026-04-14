@Composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.uan.android.passgo.R

fun Section(title: String) {

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(title, color = Color.White, fontSize = 14.sp)

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Ver todo",
                color = Color(0xFF42A5F5),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        PasswordItem(
            name = "Figma",
            icon = R.drawable.figma
        )

        PasswordItem(
            name = "Facebook",
            icon = R.drawable.facebook
        )

        PasswordItem(
            name = "Instagram",
            icon = R.drawable.instagram
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
