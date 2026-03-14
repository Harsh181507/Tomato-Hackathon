package com.example.tomato.navigation

import androidx.navigation.NavHostController

object OrderNavigation {

    var navController: NavHostController? = null

    fun goToSuccess() {
        navController?.navigate(Screen.Success.route)
    }

}