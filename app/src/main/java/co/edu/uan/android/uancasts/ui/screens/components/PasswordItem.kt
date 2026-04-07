@Composable
fun PasswordItem(name: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1F26), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(name, color = Color.White, fontSize = 14.sp)
            Text("usuario@correo.com", color = Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.Gray)
    }
}