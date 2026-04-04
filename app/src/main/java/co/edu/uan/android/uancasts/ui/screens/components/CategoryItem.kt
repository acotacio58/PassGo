@Composable
fun CategoryItem(title: String, subtitle: String, icon: ImageVector) {

    Column(
        modifier = Modifier
            .width(110.dp)
            .background(Color(0xFF1C1F26), RoundedCornerShape(16.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF42A5F5), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(title, color = Color.White, fontSize = 12.sp)
        Text(subtitle, color = Color.Gray, fontSize = 10.sp)
    }
}