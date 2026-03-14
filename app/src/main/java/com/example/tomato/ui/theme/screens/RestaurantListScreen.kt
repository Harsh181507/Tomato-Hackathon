package com.example.tomato.ui.theme.screens




import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tomato.data.DummyData
import com.example.tomato.navigation.Screen
import com.example.tomato.ui.theme.components.CategoryItem
import com.example.tomato.ui.theme.components.RestaurantCard
import com.example.tomato.ui.theme.components.RestaurantSection
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
@Composable
fun RestaurantListScreen(navController: NavController) {

    val restaurants = DummyData.restaurants
    val categories = DummyData.categories

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {

            // 🔍 Search + Notification
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search restaurants") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                )

                IconButton(onClick = { }) {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🍔 Categories
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(contentPadding = PaddingValues(16.dp)) {

                items(categories) { category ->
                    CategoryItem(category = category)
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            // 🍅 Popular Restaurants
            Text(
                text = "Popular Restaurants",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(restaurants) { restaurant ->

                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = {

                            val encodedImage =
                                URLEncoder.encode(
                                    restaurant.image,
                                    StandardCharsets.UTF_8.toString()
                                )

                            navController.navigate(
                                "menu/${restaurant.name}/$encodedImage"
                            )

                        }
                    )

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            // ⭐ Recommended
            Text(
                text = "⭐ Recommended For You",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(restaurants) { restaurant ->

                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = {

                            val encodedImage =
                                URLEncoder.encode(
                                    restaurant.image,
                                    StandardCharsets.UTF_8.toString()
                                )

                            navController.navigate(
                                "menu/${restaurant.name}/$encodedImage"
                            )

                        }
                    )

                }

            }
            RestaurantSection(
                title = "🔥 Today's Offers",
                restaurants = restaurants,
                navController = navController
            )

            RestaurantSection(
                title = "⭐ Recommended For You",
                restaurants = restaurants,
                navController = navController
            )

            RestaurantSection(
                title = "🥇 Best Sellers",
                restaurants = restaurants,
                navController = navController
            )

            RestaurantSection(
                title = "⚡ Fast Delivery (Under 30 mins)",
                restaurants = restaurants,
                navController = navController
            )

            RestaurantSection(
                title = "🍔 Top Picks Near You",
                restaurants = restaurants,
                navController = navController
            )

            RestaurantSection(
                title = "🌙 Late Night Cravings",
                restaurants = restaurants,
                navController = navController
            )

        }

    }

}