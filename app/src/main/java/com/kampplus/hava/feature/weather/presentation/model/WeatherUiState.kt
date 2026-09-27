package com.kampplus.hava.feature.weather.presentation.model

/**
 * CP3 İstenenleri 1: Arayüz durum modeli (UiState)
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
    val isLoading: Boolean = false
) {
    /**
     * CP3 İstenenleri 4: Türetilen bilgileri mevcut state'ten hesaplama.
     * Tüm şehirler arasından sadece favori olanları süzen türetilmiş özellik.
     */
    val favoriteCities: List<CityUiModel>
        get() = cities.filter { it.isFavorite || favoriteCityIds.contains(it.id) }
}
