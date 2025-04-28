//package com.example.kunsthandler.ui
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.rememberNavController
//import com.example.kunsthandler.navigation.NavGraph
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
//
//@Composable
//fun KunsthandlerApp(
//    modifier: Modifier = Modifier
//) {
//    val navController = rememberNavController()
//    val viewModel: KunsthandlerViewModel = viewModel()
//
//    NavGraph(
//        navController = navController,
//        viewModel = viewModel,
//        modifier = modifier
//    )
//}

// app/src/main/java/com/example/kunsthandler/ui/KunsthandlerApp.kt
package com.example.kunsthandler.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.kunsthandler.navigation.NavGraph
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import androidx.activity.viewModels

/**
 * The root Composable of your app.
 *
 * @param viewModel your injected ViewModel from MainActivity
 */
@Composable
fun KunsthandlerApp(viewModel: KunsthandlerViewModel) {
    // 1) Create a NavController
    val navController = rememberNavController()

    // 2) Wrap in a Surface (or whatever root layout you like)
    Surface {
        // 3) Hand off to your NavGraph, passing along the VM
        NavGraph(
            navController = navController,
            viewModel     = viewModel
        )
    }
}
