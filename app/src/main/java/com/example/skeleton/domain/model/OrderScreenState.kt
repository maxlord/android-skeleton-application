package com.example.skeleton.domain.model

data class OrderScreenState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList()
)