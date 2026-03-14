package com.example.tomato.navigation


import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {

    object Login : Screen("login")

    object SignUp : Screen("signup")

    object Restaurants : Screen("restaurants")

    object Menu : Screen("menu/{name}/{image}") {

        fun createRoute(name: String, image: String): String {

            val encodedImage =
                URLEncoder.encode(image, StandardCharsets.UTF_8.toString())

            return "menu/$name/$encodedImage"
        }

    }

    object Cart : Screen("cart")

    object Success : Screen("success")

    object TrackOrder : Screen("trackOrder")
}