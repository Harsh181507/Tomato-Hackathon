package com.example.tomato.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tomato.model.Restaurant
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
@Composable
fun RestaurantSection(
    title: String,
    restaurants: List<Restaurant>,
    navController: NavController
) {

    Column {

        Text(
            text = title,
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

    }

}