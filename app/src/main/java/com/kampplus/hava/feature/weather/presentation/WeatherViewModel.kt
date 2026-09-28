package com.kampplus.hava.feature.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kampplus.hava.core.common.AppError
import com.kampplus.hava.core.common.AppResult
import com.kampplus.hava.feature.weather.data.remote.FakeWeatherRemoteDataSource
import com.kampplus.hava.feature.weather.presentation.model.WeatherUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * CP4 İstenenleri 2 & 3: Asenkron veri akışında Loading, Success, Empty ve Error durumlarının yönetimi.
 * Eşzamanlı yinelenen istekleri Job kontrolü ile engeller.
 */
class WeatherViewModel(
    private val remoteDataSource: FakeWeatherRemoteDataSource = FakeWeatherRemoteDataSource()
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    // CP4 İstenenleri 4: Eşzamanlı yinelenen istekleri önleme kontrolü
    private var fetchJob: Job? = null

    init {
        // CP4 İstenenleri 2: ViewModel ilklendiğinde ilk veri çağrısı
        loadData()
    }

    fun loadData() {
        // CP4 İstenenleri 4: Eğer zaten bir istek yürütülüyorsa yeni isteği engelle
        if (fetchJob?.isActive == true) return

        fetchJob = viewModelScope.launch {
            // CP4 İstenenleri 3: İstek başlarken önce Loading durumunu yayımlama
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    error = null,
                    isEmpty = false
                )
            }

            try {
                when (val result = remoteDataSource.getCities()) {
                    is AppResult.Success -> {
                        val cities = result.data
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                cities = cities,
                                isEmpty = cities.isEmpty(),
                                error = null
                            )
                        }
                    }
                    is AppResult.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                    }
                }
            } catch (e: CancellationException) {
                // CP4 İstenenleri 3: İptali hata olarak sunmuyoruz
                throw e
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = AppError.UnknownError(e.message)
                    )
                }
            }
        }
    }

    fun triggerErrorAndReload() {
        remoteDataSource.triggerErrorNextTime()
        loadData()
    }

    fun triggerEmptyAndReload() {
        remoteDataSource.triggerEmptyNextTime()
        loadData()
    }

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
