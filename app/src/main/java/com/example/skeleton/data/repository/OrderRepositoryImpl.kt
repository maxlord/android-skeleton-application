package com.example.skeleton.data.repository

import com.example.skeleton.domain.model.Order
import com.example.skeleton.domain.repository.OrderRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlin.random.Random

class OrderRepositoryImpl @Inject constructor() : OrderRepository {

    private val orders = List((0..49).toList().size) { index ->
        Order(
            id = index + 1,
            title = "Order #${index + 1}",
            price = Random.nextDouble(10.0, 100.0)
        )
    }

    override suspend fun getOrders(): Flow<List<Order>> {
        delay(1_000L)
        return flowOf(orders)
    }

    override suspend fun getOrder(orderId: Int): Order {
        delay(100)
        return orders.first { it.id == orderId }
    }
}