package com.kampplus.hava.feature.weather.presentation.model

import com.kampplus.hava.core.common.AppError

/**
 * CP3 & CP4: Arayüz durum modeli (UiState).
 * Asenkron veri akışında Loading, Content, Empty ve Error durumlarını temsil eder.
 */
data class CityUiModel(
    val id: String,
    val name: String,
    val temperature: String,
    val condition: String,
    val isFavorite: Boolean = false
)

data class WeatherUiState(
    val cities: List<CityUiModel> = emptyList(),
    val selectedCityId: String? = null,
    val favoriteCityIds: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val error: AppError? = null
) {
    /**
     * CP3 İstenenleri 4: Türetilen bilgileri mevcut state'ten hesaplama.
     */
    val favoriteCities: List<CityUiModel>
        get() = cities.filter { it.isFavorite || favoriteCityIds.contains(it.id) }
}
