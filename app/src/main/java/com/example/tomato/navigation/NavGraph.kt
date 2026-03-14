package com.example.tomato.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tomato.ui.theme.screens.CartScreen
import com.example.tomato.ui.theme.screens.LoginScreen
import com.example.tomato.ui.theme.screens.MenuScreen
import com.example.tomato.ui.theme.screens.OrderSuccessScreen
import com.example.tomato.ui.theme.screens.RestaurantListScreen
import com.example.tomato.ui.theme.screens.SignUpScreen
import com.example.tomato.ui.theme.screens.TrackOrderScreen
import com.example.tomato.viewModel.MainViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(viewModel: MainViewModel) {

    val navController = rememberNavController()
    OrderNavigation.navController = navController

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(Screen.Restaurants.route) {
            RestaurantListScreen(navController)
        }

        composable(Screen.Cart.route) {
            CartScreen(navController, viewModel)
        }

        composable(Screen.Success.route) {
            OrderSuccessScreen(navController)
        }

        composable(
            route = Screen.Menu.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val restaurantName =
                backStackEntry.arguments?.getString("name") ?: ""

            val encodedImage =
                backStackEntry.arguments?.getString("image") ?: ""

            val restaurantImage =
                URLDecoder.decode(encodedImage, StandardCharsets.UTF_8.toString())

            MenuScreen(
                navController = navController,
                restaurantName = restaurantName,
                restaurantImage = restaurantImage,
                viewModel = viewModel
            )

        }
        composable(Screen.TrackOrder.route) {
            TrackOrderScreen(navController)
        }

    }

}