package com.example.skeleton.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeleton.domain.model.Order
import com.example.skeleton.domain.model.OrderScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class OrdersViewModel : ViewModel() {

    private val _state = mutableStateOf(OrderScreenState())
    val state: State<OrderScreenState> = _state

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(5_000L)
            _state.value = _state.value.copy(
                isLoading = false,
                orders = _state.value.orders.toMutableList().also {
                    for (i in 0..15) {
                        it.add(
                            Order(
                                title = "Order #${it.size + 1}",
                                price = Random.nextDouble(10.0, 100.0)
                            )
                        )
                    }
                }
            )
        }
    }
}