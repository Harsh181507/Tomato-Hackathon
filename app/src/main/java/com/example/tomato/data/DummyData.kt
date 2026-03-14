package com.example.tomato.data

import com.example.tomato.model.Category
import com.example.tomato.model.FoodItem
import com.example.tomato.model.Restaurant

object DummyData {

    val restaurants = listOf(

        Restaurant(
            1,
            "Pizza Palace",
            "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4"
        ),

        Restaurant(
            2,
            "Burger Hub",
            "https://images.unsplash.com/photo-1555992336-03a23c7b20ee"
        ),

        Restaurant(
            3,
            "South Indian Delight",
            "https://images.unsplash.com/photo-1589302168068-964664d93dc0"
        ),

        Restaurant(
            4,
            "Dragon Chinese",
            "https://images.unsplash.com/photo-1552566626-52f8b828add9"
        ),

        Restaurant(
            5,
            "Sweet Tooth Desserts",
            "https://images.unsplash.com/photo-1551024601-bec78aea704b"
        ),

        Restaurant(
            6,
            "Tandoori Treats",
            "https://images.unsplash.com/photo-1600891964599-f61ba0e24092"
        ),

        Restaurant(
            7,
            "Cafe Latte",
            "https://images.unsplash.com/photo-1509042239860-f550ce710b93"
        ),

        Restaurant(
            8,
            "Sushi Master",
            "https://images.unsplash.com/photo-1579584425555-c3ce17fd4351"
        ),

        Restaurant(
            9,
            "Biryani House",
            "https://images.unsplash.com/photo-1633945274309-2c16b2210b1f"
        ),

        Restaurant(
            10,
            "Healthy Bites",
            "https://images.unsplash.com/photo-1490645935967-10de6ba17061"
        ),

        Restaurant(
            11,
            "Mexican Fiesta",
            "https://images.unsplash.com/photo-1552332386-f8dd00dc2f85"
        ),

        Restaurant(
            12,
            "Street Food Corner",
            "https://images.unsplash.com/photo-1551782450-a2132b4ba21d"
        )
    )
    val menu = listOf(

        FoodItem(
            1,
            "Cheese Burger",
            120,
            "https://images.unsplash.com/photo-1550547660-d9450f859349",
            "Burger"
        ),

        FoodItem(
            2,
            "Pepperoni Pizza",
            250,
            "https://images.unsplash.com/photo-1548365328-5c620f4e1f0d",
            "Pizza"
        ),

        FoodItem(
            3,
            "Creamy Pasta",
            180,
            "https://images.unsplash.com/photo-1521389508051-d7ffb5dc8a4e",
            "Pizza"
        ),

        FoodItem(
            4,
            "French Fries",
            90,
            "https://images.unsplash.com/photo-1576107232684-1279f390859f",
            "Burger"
        ),

        FoodItem(
            5,
            "Cappuccino",
            120,
            "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
            "Drinks"
        )

    )



    val categories = listOf(

        Category(
            "Pizza",
            "https://images.unsplash.com/photo-1513104890138-7c749659a591"
        ),

        Category(
            "Burger",
            "https://images.unsplash.com/photo-1550547660-d9450f859349"
        ),

        Category(
            "South Indian",
            "https://images.unsplash.com/photo-1631515243349-e0cb75fb8d3a"
        ),

        Category(
            "Chinese",
            "https://images.unsplash.com/photo-1585032226651-759b368d7246"
        ),

        Category(
            "Dessert",
            "https://images.unsplash.com/photo-1563805042-7684c019e1cb"
        ),

        Category(
            "Biryani",
            "https://images.unsplash.com/photo-1633945274309-2c16b2210b1f"
        ),

        Category(
            "Drinks",
            "https://images.unsplash.com/photo-1509042239860-f550ce710b93"
        ),

        Category(
            "Healthy",
            "https://images.unsplash.com/photo-1490645935967-10de6ba17061"
        )
    )

}