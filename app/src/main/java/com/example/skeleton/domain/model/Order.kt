package com.example.skeleton.domain.model

data class Order(
    val id: Int,
    val title: String,
    val price: Double
) {

    companion object {
        val EMPTY = Order(0, "", 0.0)
    }
}