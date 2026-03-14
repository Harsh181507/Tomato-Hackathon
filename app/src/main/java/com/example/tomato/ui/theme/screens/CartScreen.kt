package com.example.tomato.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.tomato.viewModel.MainViewModel
import com.razorpay.Checkout
import org.json.JSONObject
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.os.Build
import androidx.core.content.ContextCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val context = LocalContext.current

    var splitEnabled by remember { mutableStateOf(false) }
    var peopleCount by remember { mutableStateOf(1) }

    var upiInput by remember { mutableStateOf("") }
    var upiList by remember { mutableStateOf(listOf<String>()) }

    var splitMode by remember { mutableStateOf("equal") }

    var friends by remember {
        mutableStateOf(
            mutableListOf("Friend 1", "Friend 2", "Friend 3")
        )
    }

    var itemAssignments by remember {
        mutableStateOf(mutableMapOf<Int, String>())
    }

    val cartItems = viewModel.cart
    val groupedCart = cartItems.groupBy { it.id }

    val itemTotal = viewModel.totalPrice()
    val deliveryFee = 40
    val tax = 20
    val grandTotal = itemTotal + deliveryFee + tax

    val splitMessage = buildString {

        append("🍅 Tomato Bill Split\n\n")

        groupedCart.values.forEach { foodGroup ->

            val food = foodGroup.first()
            val quantity = foodGroup.size
            val assigned = itemAssignments[food.id]

            if (assigned != null) {
                append("${food.name} x$quantity → $assigned ₹${food.price * quantity}\n")
            }

        }

        append("\nTotal Bill: ₹$grandTotal\n")

        if (upiList.isNotEmpty()) {

            append("\nPay via UPI:\n")

            upiList.forEach {
                append("$it\n")
            }

        }

    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = { Text("Your Cart") },

                navigationIcon = {

                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBack, null)
                    }

                }

            )

        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {

                items(groupedCart.values.toList()) { foodGroup ->

                    val food = foodGroup.first()
                    val quantity = foodGroup.size

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = food.image,
                                contentDescription = food.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(70.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    food.name,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    "₹${food.price}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                IconButton(
                                    onClick = { viewModel.removeFromCart(food) }
                                ) {
                                    Icon(Icons.Default.Delete, null)
                                }

                                Text(
                                    quantity.toString(),
                                    modifier = Modifier.padding(horizontal = 6.dp)
                                )

                                IconButton(
                                    onClick = { viewModel.addToCart(food) }
                                ) {
                                    Icon(Icons.Default.Add, null)
                                }

                            }

                        }

                    }

                }

                item {

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    "Split Bill",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Switch(
                                    checked = splitEnabled,
                                    onCheckedChange = { splitEnabled = it }
                                )

                            }

                            if (splitEnabled) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    FilterChip(
                                        selected = splitMode == "equal",
                                        onClick = { splitMode = "equal" },
                                        label = { Text("Equal Split") }
                                    )

                                    FilterChip(
                                        selected = splitMode == "items",
                                        onClick = { splitMode = "items" },
                                        label = { Text("Assign Items") }
                                    )

                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                if (splitMode == "equal") {

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Text("People:")

                                        Spacer(modifier = Modifier.width(10.dp))

                                        IconButton(
                                            onClick = {
                                                if (peopleCount > 1) peopleCount--
                                            }
                                        ) {
                                            Icon(Icons.Default.Delete, null)
                                        }

                                        Text(
                                            peopleCount.toString(),
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )

                                        IconButton(
                                            onClick = { peopleCount++ }
                                        ) {
                                            Icon(Icons.Default.Add, null)
                                        }

                                    }

                                    val perPerson = grandTotal / peopleCount

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text("Each person pays ₹$perPerson")

                                }

                                if (splitMode == "items") {

                                    Text("Assign Items", fontWeight = FontWeight.Bold)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    groupedCart.values.forEach { foodGroup ->

                                        val food = foodGroup.first()

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Text(
                                                food.name,
                                                modifier = Modifier.weight(1f)
                                            )

                                            var expanded by remember { mutableStateOf(false) }

                                            Box {

                                                Button(onClick = { expanded = true }) {
                                                    Text(itemAssignments[food.id] ?: "Select")
                                                }

                                                DropdownMenu(
                                                    expanded = expanded,
                                                    onDismissRequest = { expanded = false }
                                                ) {

                                                    friends.forEach { friend ->

                                                        DropdownMenuItem(
                                                            text = { Text(friend) },
                                                            onClick = {

                                                                itemAssignments[food.id] = friend
                                                                expanded = false

                                                            }
                                                        )

                                                    }

                                                }

                                            }

                                        }

                                    }

                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Text("Add UPI ID")

                                Row {

                                    OutlinedTextField(
                                        value = upiInput,
                                        onValueChange = { upiInput = it },
                                        modifier = Modifier.weight(1f),
                                        placeholder = { Text("example@upi") }
                                    )

                                    Spacer(modifier = Modifier.width(6.dp))

                                    Button(
                                        onClick = {
                                            if (upiInput.isNotEmpty()) {
                                                upiList = upiList + upiInput
                                                upiInput = ""
                                            }
                                        }
                                    ) {
                                        Text("+")
                                    }

                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(
                                    onClick = {

                                        shareSplitBill(context, splitMessage)

                                        showSplitNotification(context)

                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Share Split Bill")
                                }

                            }

                        }

                    }

                }

                item {

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                "Bill Details",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            BillRow("Item Total", itemTotal)
                            BillRow("Delivery Fee", deliveryFee)
                            BillRow("Taxes", tax)

                            Divider(modifier = Modifier.padding(vertical = 10.dp))

                            BillRow(
                                "Grand Total",
                                grandTotal,
                                isBold = true
                            )

                        }

                    }

                }

            }

            Button(
                onClick = {
                    startRazorpayPayment(navController, grandTotal)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {

                Text(
                    text = "Place Order • ₹$grandTotal",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }

    }

}

@Composable
fun BillRow(
    label: String,
    value: Int,
    isBold: Boolean = false
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            label,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )

        Text(
            "₹$value",
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )

    }

}

fun startRazorpayPayment(
    navController: NavController,
    amount: Int
) {

    val activity = navController.context as Activity
    val checkout = Checkout()

    checkout.setKeyID("rzp_test_D751dYldPU151G")

    try {

        val options = JSONObject()

        options.put("name", "Tomato")
        options.put("description", "Food Order Payment")
        options.put("currency", "INR")
        options.put("amount", amount * 100)

        val retry = JSONObject()
        retry.put("enabled", true)
        retry.put("max_count", 4)
        options.put("retry", retry)

        val prefill = JSONObject()
        prefill.put("email", "test@razorpay.com")
        prefill.put("contact", "9876543210")
        options.put("prefill", prefill)

        checkout.open(activity, options)

    } catch (e: Exception) {

        Toast.makeText(activity, "Payment error: ${e.message}", Toast.LENGTH_LONG).show()

    }

}

fun shareSplitBill(context: android.content.Context, message: String) {

    val intent = Intent(Intent.ACTION_SEND)

    intent.type = "text/plain"

    intent.putExtra(Intent.EXTRA_TEXT, message)

    context.startActivity(
        Intent.createChooser(intent, "Send Split Bill")
    )

}
fun showSplitNotification(context: android.content.Context) {

    val notificationManager =
        context.getSystemService(android.content.Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

    val channelId = "tomato_split_channel"

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

        val channel = android.app.NotificationChannel(
            channelId,
            "Split Notifications",
            android.app.NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)
    }

    val notification = androidx.core.app.NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("🍅 Tomato")
        .setContentText("Split bill shared with friends!")
        .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
        .build()

    notificationManager.notify(1, notification)
}