package com.example.skeleton.data.repository

import com.example.skeleton.domain.model.Order
import com.example.skeleton.domain.repository.OrderRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class OrderRepositoryImpl @Inject constructor() : OrderRepository {

    override suspend fun getOrders(): List<Order> {
        delay(2_000L)
        val orders = mutableListOf<Order>()
        for (i in 0..15) {
            orders.add(
                Order(
                    title = "Order #${orders.size + 1}",
                    price = Random.nextDouble(10.0, 100.0)
                )
            )
        }
        return orders.toList()
    }
}