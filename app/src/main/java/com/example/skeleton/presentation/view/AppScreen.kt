package com.example.skeleton.presentation.view

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.skeleton.R
import com.example.skeleton.navigation.AppNavigation
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkeletonAppBar(
    screenTitle: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(screenTitle) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    var actionBarTitle by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            Timber.d("BackStackEntry is changed to ${backStackEntry.destination.route}")
            actionBarTitle = getTitleByRoute(context, backStackEntry.destination.route)
        }
    }

    Scaffold(
        topBar = {
            SkeletonAppBar(
                screenTitle = actionBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "orders") {
            composable(AppNavigation.ORDERS) { OrderListScreen(navController, innerPadding) }
            composable(
                "${AppNavigation.ORDERS}/{orderId}",
                arguments = listOf(navArgument("orderId") { type = NavType.IntType })
            ) { OrderDetailsScreen(navController, innerPadding) }
        }
    }
}

fun getTitleByRoute(context: Context, route: String?): String {
    return when (route) {
        AppNavigation.ORDERS -> "Orders"
        else -> {
            if (route?.startsWith(AppNavigation.ORDERS) == true) {
                "Order Details"
            } else {
                context.getString(R.string.app_name)
            }
        }
    }
}
