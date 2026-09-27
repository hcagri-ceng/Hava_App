package com.kampplus.hava.feature.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.ui.component.CityCard
import com.kampplus.hava.core.ui.theme.HavaTheme
import com.kampplus.hava.feature.weather.presentation.model.CityUiModel
import com.kampplus.hava.feature.weather.presentation.model.WeatherUiState

/**
 * CP3 İstenenleri 3: UiState'i gözlemleyen ve aksiyonları (onCityClick, onFavoriteToggle)
 * ViewModel'e ileten Ana Şehir Listesi Ekranı.
 */
@Composable
fun MainScreen(
    uiState: WeatherUiState,
    onCityClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Hava Durumu & Şehirler",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Favori şehirlerinizi seçebilir, detay için tıklayabilirsiniz:",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Favori Şehir Sayısı Özeti (CP3: Türetilmiş Durum)
            if (uiState.favoriteCities.isNotEmpty()) {
                Text(
                    text = "Favori Şehir Sayınız: ${uiState.favoriteCities.size}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn {
                items(uiState.cities, key = { it.id }) { city ->
                    CityCard(
                        cityName = city.name,
                        temperature = city.temperature,
                        weatherCondition = city.condition,
                        isFavorite = city.isFavorite,
                        onFavoriteToggle = { onFavoriteToggle(city.id) },
                        onClick = { onCityClick(city.id) }
                    )
                }
            }
        }
    }
}

@Preview(name = "Ana Ekran Önizlemesi", showBackground = true)
@Composable
fun MainScreenPreview() {
    HavaTheme {
        MainScreen(
            uiState = WeatherUiState(
                cities = listOf(
                    CityUiModel("istanbul", "İstanbul", "22°C", "Güneşli", isFavorite = true),
                    CityUiModel("ankara", "Ankara", "18°C", "Parçalı Bulutlu", isFavorite = false)
                )
            ),
            onCityClick = {},
            onFavoriteToggle = {}
        )
    }
}
