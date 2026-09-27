package com.kampplus.hava.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kampplus.hava.feature.favorites.presentation.FavoritesScreen
import com.kampplus.hava.feature.weather.presentation.DetailScreen
import com.kampplus.hava.feature.weather.presentation.MainScreen
import com.kampplus.hava.feature.weather.presentation.WeatherViewModel

/**
 * CP3 İstenenleri 2 & 3: Ortak ViewModel sahipliği ve StateFlow canlı takibi.
 * NavHost seviyesinde ortak ViewModel oluşturulur ve collectAsStateWithLifecycle() ile dinlenir.
 */
@Composable
fun HavaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel = viewModel()
) {
    // CP3 İstenenleri 3: Durumu collectAsStateWithLifecycle() ile dinleme
    val uiState by weatherViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        // 1. Ana Ekran Hedefi
        composable(route = Destination.Home.route) {
            MainScreen(
                uiState = uiState,
                onCityClick = { cityId ->
                    weatherViewModel.selectCity(cityId)
                    navController.navigate(Destination.Detail.createRoute(cityId))
                },
                onFavoriteToggle = { cityId ->
                    weatherViewModel.toggleFavorite(cityId)
                },
                onNavigateToFavorites = {
                    navController.navigate(Destination.Favorites.route)
                }
            )
        }

        // 2. Favori Şehirler Ekranı Hedefi (İkinci Ekran)
        composable(route = Destination.Favorites.route) {
            FavoritesScreen(
                favoriteCities = uiState.favoriteCities,
                onCityClick = { cityId ->
                    weatherViewModel.selectCity(cityId)
                    navController.navigate(Destination.Detail.createRoute(cityId))
                },
                onFavoriteToggle = { cityId ->
                    weatherViewModel.toggleFavorite(cityId)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Şehir Detay Ekranı Hedefi (Ortak ViewModel'den Şehir Verisini Alma)
        composable(
            route = Destination.Detail.route,
            arguments = listOf(
                navArgument("cityId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")
            val selectedCity = uiState.cities.find { it.id == cityId }

            DetailScreen(
                city = selectedCity,
                onFavoriteToggle = {
                    cityId?.let { weatherViewModel.toggleFavorite(it) }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
