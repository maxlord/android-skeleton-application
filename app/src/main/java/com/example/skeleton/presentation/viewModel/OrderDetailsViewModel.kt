package com.example.skeleton.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skeleton.domain.model.Order
import com.example.skeleton.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val orderRepo: OrderRepository
) : ViewModel() {

    private val orderId: Int = checkNotNull(savedStateHandle["orderId"])

    private val _state: MutableStateFlow<Order> = MutableStateFlow(Order.EMPTY)
    val state: StateFlow<Order> = _state.asStateFlow()

    init {
        Timber.d("orderId = $orderId")
        loadOrder()
    }

    fun loadOrder() {
        viewModelScope.launch {
            _state.value = orderRepo.getOrder(orderId)
        }
    }
}