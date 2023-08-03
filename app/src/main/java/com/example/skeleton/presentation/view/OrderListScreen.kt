package com.example.skeleton.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.skeleton.domain.model.Order
import com.example.skeleton.navigation.AppNavigation
import com.example.skeleton.presentation.viewModel.OrderListViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(navController: NavController, innerPadding: PaddingValues) {
    val viewModel: OrderListViewModel = hiltViewModel()
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            items(state.orders) {
                OrderListItem(order = it, onClick = {
                    Timber.d("Order clicked = $it")
                    navController.navigate("${AppNavigation.ORDERS}/${it.id}")
                })
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Button(
                onClick = { viewModel.refreshOrders() },
                shape = ButtonDefaults.outlinedShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                Text(text = "Refresh")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListItem(order: Order, onClick: (Order) -> Unit) {
    ElevatedCard(
        onClick = { onClick(order) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${order.title} (€ ${"%.2f".format(order.price)})")
        }
    }
}