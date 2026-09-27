package com.kampplus.hava.feature.weather.presentation

import androidx.lifecycle.ViewModel
import com.kampplus.hava.feature.weather.presentation.model.CityUiModel
import com.kampplus.hava.feature.weather.presentation.model.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * CP3 İstenenleri 1 & 3: Ortak ViewModel.
 * Ekran durumunu StateFlow<WeatherUiState> üzerinden sunar, aksiyonları işleyip yeni state üretir.
 */
class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        WeatherUiState(
            cities = listOf(
                CityUiModel("istanbul", "İstanbul", "22°C", "Güneşli"),
                CityUiModel("ankara", "Ankara", "18°C", "Parçalı Bulutlu"),
                CityUiModel("izmir", "İzmir", "25°C", "Açık")
            )
        )
    )
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun selectCity(cityId: String?) {
        _uiState.update { currentState ->
            currentState.copy(selectedCityId = cityId)
        }
    }

    fun toggleFavorite(cityId: String) {
        _uiState.update { currentState ->
            val currentFavorites = currentState.favoriteCityIds.toMutableSet()
            if (currentFavorites.contains(cityId)) {
                currentFavorites.remove(cityId)
            } else {
                currentFavorites.add(cityId)
            }

            val updatedCities = currentState.cities.map { city ->
                if (city.id == cityId) {
                    city.copy(isFavorite = currentFavorites.contains(cityId))
                } else city
            }

            currentState.copy(
                favoriteCityIds = currentFavorites,
                cities = updatedCities
            )
        }
    }
}
