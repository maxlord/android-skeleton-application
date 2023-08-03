package com.example.skeleton.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.skeleton.presentation.viewModel.OrderDetailsViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(navController: NavController, innerPadding: PaddingValues) {
    val viewModel: OrderDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            text = state.title
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Right,
            text = "€ ${"%.2f".format(state.price)}"
        )
    }
}