@Composable
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