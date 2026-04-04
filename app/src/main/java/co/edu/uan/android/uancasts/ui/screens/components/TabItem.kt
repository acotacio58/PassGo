@Composable
fun TabItem(text: String, selected: Boolean) {

    Box(
        modifier = Modifier
            .background(
                if (selected) Color(0xFF42A5F5) else Color(0xFF2A2E35),
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text,
            color = if (selected) Color.White else Color.LightGray,
            fontSize = 12.sp
        )
    }
}