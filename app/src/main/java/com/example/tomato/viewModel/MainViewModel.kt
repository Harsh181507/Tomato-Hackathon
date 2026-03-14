package com.example.tomato.viewModel


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tomato.model.FoodItem
class MainViewModel : ViewModel() {

    private val _cart = mutableStateListOf<FoodItem>()
    val cart: List<FoodItem> = _cart

    fun addToCart(food: FoodItem) {
        _cart.add(food)
    }

    fun removeFromCart(food: FoodItem) {
        _cart.remove(food)
    }

    fun clearCart() {
        _cart.clear()
    }

    fun totalPrice(): Int {
        return _cart.sumOf { it.price }
    }
}