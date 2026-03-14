package com.example.tomato.ui.theme.screens

import com.example.tomato.ui.theme.components.BottomCartBar
import com.example.tomato.ui.theme.components.FoodItemCard
import com.example.tomato.viewModel.MainViewModel


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.tomato.data.DummyData
import com.example.tomato.navigation.Screen




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavController,
    restaurantName: String,
    restaurantImage: String,
    viewModel: MainViewModel
) {

    var selectedCategory by remember { mutableStateOf("All") }

    val foods = DummyData.menu
    val cart = viewModel.cart

    // ✅ Filter foods based on category
    val filteredFoods = if (selectedCategory == "All") {
        foods
    } else {
        foods.filter { it.category == selectedCategory }
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(restaurantName)
                },

                navigationIcon = {

                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }

                },

                actions = {

                    IconButton(onClick = { }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                    }

                }

            )

        },

        bottomBar = {

            if (cart.isNotEmpty()) {

                BottomCartBar(
                    itemCount = cart.size,
                    onClick = {
                        navController.navigate(Screen.Cart.route)
                    }
                )

            }

        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

            item {

                AsyncImage(
                    model = restaurantImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        restaurantName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "⭐ 4.5 • 30 mins • ₹200 for two",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    val categories = listOf(
                        "All",
                        "Burger",
                        "Pizza",
                        "Drinks",
                        "Dessert"
                    )

                    LazyRow {

                        items(categories) { category ->

                            AssistChip(

                                onClick = {
                                    selectedCategory = category
                                },

                                label = {
                                    Text(category)
                                },

                                modifier = Modifier.padding(end = 8.dp),

                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor =
                                        if (selectedCategory == category)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.surface
                                )

                            )

                        }

                    }

                }

            }

            // ✅ Use filtered foods instead of full list
            items(filteredFoods) { food ->

                FoodItemCard(
                    food = food,
                    viewModel = viewModel
                )

            }

        }

    }

}