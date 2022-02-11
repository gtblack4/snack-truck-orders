package com.snacktruck.orders.data

import com.snacktruck.orders.model.Snack

object SnackList {
    val allSnacks: List<Snack> = listOf(
        Snack(
            "French fries",
            "Veggie",
        ),
        Snack(
            "Veggie Burger",
            "Veggie",
        ),
        Snack(
            "Carrots",
            "Veggie",
        ),
        Snack(
            "Apple",
            "Veggie",
        ),
        Snack(
            "Banana",
            "Veggie",
        ),
        Snack(
            "Milkshake",
            "Veggie",
        ),
        Snack(
            "Cheeseburger",
            "Non-veggie",
        ),
        Snack(
            "Hamburger",
            "Non-veggie",
        ),
        Snack(
            "Hotdog",
            "Non-veggie",
        ),
    )
}