package com.example.skeleton.domain.repository

import com.example.skeleton.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun getOrders(): Flow<List<Order>>

    suspend fun getOrder(orderId: Int): Order
}