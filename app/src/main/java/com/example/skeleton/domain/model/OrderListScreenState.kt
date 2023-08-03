package com.example.skeleton.domain.model

data class OrderListScreenState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList()
)