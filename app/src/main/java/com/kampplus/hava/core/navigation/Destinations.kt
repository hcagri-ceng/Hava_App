package com.kampplus.hava.core.navigation

/**
 * CP2 İstenenleri 2: Navigasyon hedefleri (Destinations / Routes)
 */
sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Favorites : Destination("favorites")
    data object Detail : Destination("detail/{cityId}") {
        fun createRoute(cityId: String): String = "detail/$cityId"
    }
}
