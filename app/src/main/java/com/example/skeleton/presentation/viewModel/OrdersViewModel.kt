package com.example.skeleton.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeleton.domain.model.OrderScreenState
import com.example.skeleton.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepo: OrderRepository
) : ViewModel() {

    private val _state = mutableStateOf(OrderScreenState())
    val state: State<OrderScreenState> = _state

    init {
        refreshOrders()
    }

    fun refreshOrders() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                orders = emptyList(),
                isLoading = true
            )
            withContext(Dispatchers.IO) {
                val orders = orderRepo.getOrders()
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        orders = orders
                    )
                }
            }
        }
    }
}