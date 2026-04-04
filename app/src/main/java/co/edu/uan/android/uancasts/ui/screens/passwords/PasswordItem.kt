@Composable
fun PasswordItem(name: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF1C1F26), RoundedCornerShape(14.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ICONO (placeholder)
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.first().toString(),
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(name, color = Color.White, fontSize = 14.sp)
            Text("usuario@correo.com", color = Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.Gray)
    }
}