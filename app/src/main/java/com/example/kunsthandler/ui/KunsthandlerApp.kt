package com.example.kunsthandler.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandler.navigation.NavGraph
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel

@Composable
fun KunsthandlerApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val viewModel: KunsthandlerViewModel = viewModel()
    
    NavGraph(
        navController = navController,
        viewModel = viewModel,
        modifier = modifier
    )
} 