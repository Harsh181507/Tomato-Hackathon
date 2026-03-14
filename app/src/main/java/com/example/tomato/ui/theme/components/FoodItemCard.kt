package com.example.tomato.ui.theme.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.example.tomato.model.FoodItem
import com.example.tomato.viewModel.MainViewModel
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.ui.draw.scale

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.getValue

@Composable
fun FoodItemCard(
    food: FoodItem,
    viewModel: MainViewModel
) {

    val cart = viewModel.cart
    val quantity = cart.count { it.id == food.id }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = food.image,
                contentDescription = food.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = food.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Fresh & delicious",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "₹${food.price}",
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "🔥 Bestseller",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )

            }

            // 🔥 Animated Add / Quantity UI
            AnimatedContent(
                targetState = quantity,
                transitionSpec = {
                    scaleIn() + fadeIn() togetherWith scaleOut() + fadeOut()
                },
                label = "cartAnimation"
            ) { qty ->

                if (qty == 0) {

                    Button(
                        onClick = {
                            viewModel.addToCart(food)
                        },
                        shape = RoundedCornerShape(50),
                        contentPadding = PaddingValues(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        )
                    ) {
                        Text("Add")
                    }

                } else {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                RoundedCornerShape(50)
                            )
                            .padding(horizontal = 6.dp)
                    ) {

                        IconButton(
                            onClick = {
                                viewModel.removeFromCart(food)
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null
                            )
                        }

                        val scale by animateFloatAsState(
                            targetValue = if (qty > 0) 1.2f else 1f,
                            animationSpec = spring(
                                dampingRatio = 0.4f,
                                stiffness = 300f
                            ),
                            label = "quantityBounce"
                        )

                        Text(
                            text = qty.toString(),
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .scale(scale)
                        )

                        IconButton(
                            onClick = {
                                viewModel.addToCart(food)
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null
                            )
                        }

                    }

                }

            }

        }

    }

}


