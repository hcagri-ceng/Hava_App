package com.kampplus.hava.feature.weather.data.remote

import com.kampplus.hava.core.common.AppError
import com.kampplus.hava.core.common.AppResult
import com.kampplus.hava.feature.weather.presentation.model.CityUiModel
import kotlinx.coroutines.delay

/**
 * CP4 İstenenleri 1: Suspend dönüş tipli asenkron veri çağrısı.
 * Gecikme (delay), dolu sonuç, boş sonuç ve hata senaryolarını simüle eder.
 */
class FakeWeatherRemoteDataSource {

    private var simulateError = false
    private var simulateEmpty = false

    suspend fun getCities(): AppResult<List<CityUiModel>> {
        // CP4 İstenenleri 1: Asenkron ağ gecikmesi simülasyonu
        delay(1200)

        if (simulateError) {
            simulateError = false // Bir sonraki "Tekrar Dene" çağrısında düzelmesi için
            return AppResult.Error(AppError.NetworkError)
        }

        if (simulateEmpty) {
            simulateEmpty = false
            return AppResult.Success(emptyList())
        }

        return AppResult.Success(
            listOf(
                CityUiModel("istanbul", "İstanbul", "22°C", "Güneşli"),
                CityUiModel("ankara", "Ankara", "18°C", "Parçalı Bulutlu"),
                CityUiModel("izmir", "İzmir", "25°C", "Açık"),
                CityUiModel("bursa", "Bursa", "20°C", "Açık"),
                CityUiModel("antalya", "Antalya", "28°C", "Sıcak")
            )
        )
    }

    fun triggerErrorNextTime() {
        simulateError = true
    }

    fun triggerEmptyNextTime() {
        simulateEmpty = true
    }
}
