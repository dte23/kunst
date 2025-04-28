package com.example.kunsthandler.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

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
        fun createRoute(photoId: String) = "details/$photoId"
    }
    object Payment : Screen("payment")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: KunsthandlerViewModel,
    modifier: Modifier = Modifier
) {
    val artists by viewModel.artists.observeAsState(initial = emptyList())
    val categories by viewModel.categories.observeAsState(initial = emptyList())

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onSelectArtist    = { navController.navigate(Screen.SelectArtist.route) },
                onSelectCategory  = { navController.navigate(Screen.SelectCategory.route) },
                onPayment         = { navController.navigate(Screen.Payment.route) }
            )
        }

        composable(Screen.SelectArtist.route) {
            SelectArtistScreen(
                artists = artists,
                onNavigateToImages = { artist ->
                    navController.navigate(Screen.Images.createRoute("artist", artist.id))
                },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(Screen.SelectCategory.route) {
            SelectCategoryScreen(
                categories = categories,
                onNavigateToImages = { category ->
                    navController.navigate(Screen.Images.createRoute("category", category.id))
                },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = Screen.Images.route,
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id")   { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val id   = backStackEntry.arguments?.getString("id")   ?: ""

            val selectedArtist = if (type == "artist") {
                artists.firstOrNull { it.id == id }
            } else null

            val selectedCategory = if (type == "category") {
                categories.firstOrNull { it.id == id }
            } else null

            ImagesScreen(
                viewModel           = viewModel,
                selectedArtist      = selectedArtist,
                selectedCategory    = selectedCategory,
                onNavigateBack      = { navController.navigateUp() },
                onNavigateToDetails = { photo ->
                    navController.navigate(Screen.Details.createRoute(photo.id))
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("photoId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString("photoId") ?: ""
            val photo   = viewModel.photos.value?.firstOrNull { it.id == photoId }

            DetailsScreen(
                photo            = photo,
                onNavigateBack   = { navController.navigateUp() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                },
                viewModel = viewModel
            )
        }

        composable(Screen.Payment.route) {
            PaymentScreen(
                viewModel         = viewModel,
                onNavigateBack    = { navController.navigateUp() },
                onNavigateToHome  = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


