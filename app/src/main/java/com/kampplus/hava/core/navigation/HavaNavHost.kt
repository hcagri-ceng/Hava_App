package com.kampplus.hava.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kampplus.hava.feature.weather.presentation.DetailScreen
import com.kampplus.hava.feature.weather.presentation.MainScreen

/**
 * CP2 İstenenleri 2: NavHost yapılandırması.
 * Ekran hedeflerini tanımlar ve şehir parametresini ikinci ekrana aktarır.
 */
@Composable
fun HavaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        // 1. Ana Ekran Hedefi
        composable(route = Destination.Home.route) {
            MainScreen(
                onCityClick = { cityId ->
                    navController.navigate(Destination.Detail.createRoute(cityId))
                }
            )
        }

        // 2. Şehir Detay Ekranı Hedefi (Parametre Alarak)
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
            DetailScreen(
                cityId = cityId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
