package com.example.tomato.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.tomato.navigation.Screen

@Composable
fun OrderSuccessScreen(navController: NavController) {

    var minutesLeft by remember { mutableStateOf(30) }
    var secondsLeft by remember { mutableStateOf(0) }

    val orderId = remember { (1000..9999).random() }

    LaunchedEffect(Unit) {

        while (minutesLeft > 0 || secondsLeft > 0) {

            delay(1000)

            if (secondsLeft == 0) {
                minutesLeft--
                secondsLeft = 59
            } else {
                secondsLeft--
            }

        }

    }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Order Confirmed!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Your food is being prepared 🍔",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Order ID #$orderId")

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Estimated Delivery")

                    Text(
                        String.format("%02d:%02d", minutesLeft, secondsLeft),
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            // Delivery Partner Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {

                        Text(
                            "Delivery Partner",
                            fontWeight = FontWeight.Bold
                        )

                        Text("Rahul Kumar")
                        Text("Bike: TN 09 AB 2345")
                        Text("⭐ 4.8 rating")

                    }

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.TrackOrder.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(Icons.Default.Info, null)

                Spacer(modifier = Modifier.width(6.dp))

                Text("Track Order")

            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.Restaurants.route) {
                        popUpTo(0)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Back to Home")

            }

        }

    }

}