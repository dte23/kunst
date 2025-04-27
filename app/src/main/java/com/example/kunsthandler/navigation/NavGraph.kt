package com.example.kunsthandler.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.kunsthandler.models.Category
import com.example.kunsthandler.ui.screens.*
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SelectArtist : Screen("selectArtist")
    object SelectCategory : Screen("selectCategory")
    object Images : Screen("images/{type}/{id}") {
        fun createRoute(type: String, id: String) = "images/$type/$id"
    }
    object Details : Screen("details/{photoId}") {
        fun createRoute(photoId: Long) = "details/$photoId"
    }
    object Payment : Screen("payment")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: KunsthandlerViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onSelectArtist = { navController.navigate(Screen.SelectArtist.route) },
                onSelectCategory = { navController.navigate(Screen.SelectCategory.route) },
                onPayment = { navController.navigate(Screen.Payment.route) },
                viewModel = viewModel
            )
        }
        
        composable(Screen.SelectArtist.route) {
            SelectArtistScreen(
                onNavigateToImages = { artist ->
                    navController.navigate(Screen.Images.createRoute("artist", artist.id.toString()))
                },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        
        composable(Screen.SelectCategory.route) {
            SelectCategoryScreen(
                onNavigateToImages = { category ->
                    navController.navigate(Screen.Images.createRoute("category", category.name))
                },
                onNavigateBack = { navController.navigateUp() }
            )
        }
        
        composable(
            route = Screen.Images.route,
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")
            val id = backStackEntry.arguments?.getString("id")
            
            val selectedArtist = if (type == "artist") {
                ArtDataSource.artists.find { artist -> artist.id.toString() == id }
            } else null
            
            val selectedCategory = if (type == "category") {
                Category.entries.find { it.name == id }
            } else null
            
            ImagesScreen(
                onNavigateToDetails = { photo -> 
                    navController.navigate(Screen.Details.createRoute(photo.id))
                },
                onNavigateBack = { navController.navigateUp() },
                selectedArtist = selectedArtist,
                selectedCategory = selectedCategory,
                viewModel = viewModel
            )
        }
        
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("photoId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getLong("photoId")
            val photo = photoId?.let { ArtDataSource.photoById(it) }
            
            DetailsScreen(
                photo = photo,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                    }
                },
                viewModel = viewModel
            )
        }
        
        composable(Screen.Payment.route) {
            PaymentScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                },
                viewModel = viewModel
            )
        }
    }
}