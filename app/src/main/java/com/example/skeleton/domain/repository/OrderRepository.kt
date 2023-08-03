package com.example.skeleton.domain.repository

import com.example.skeleton.domain.model.Order

interface OrderRepository {

    suspend fun getOrders(): List<Order>
}