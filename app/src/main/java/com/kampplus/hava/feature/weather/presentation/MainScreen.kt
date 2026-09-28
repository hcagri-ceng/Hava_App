package com.kampplus.hava.feature.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.common.AppError
import com.kampplus.hava.core.ui.component.CityCard
import com.kampplus.hava.core.ui.component.EmptyComponent
import com.kampplus.hava.core.ui.component.ErrorComponent
import com.kampplus.hava.core.ui.component.LoadingComponent
import com.kampplus.hava.core.ui.theme.HavaTheme
import com.kampplus.hava.feature.weather.presentation.model.CityUiModel
import com.kampplus.hava.feature.weather.presentation.model.WeatherUiState

/**
 * CP4 İstenenleri 4: Arayüzü when kontrolü ile 4 duruma bağlama (Loading, Success, Empty, Error).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: WeatherUiState,
    onCityClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    onNavigateToFavorites: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hava Durumu & Şehirler") },
                actions = {
                    IconButton(onClick = onRetry) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Yenile"
                        )
                    }
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorilerim Ekranına Git",
                            tint = if (uiState.favoriteCities.isNotEmpty()) Color.Red else MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // CP4 İstenenleri 4: when ile 4 arayüz durumunun (Loading, Error, Empty, Content) gösterilmesi
            when {
                uiState.isLoading -> {
                    LoadingComponent()
                }

                uiState.error != null -> {
                    val errorMessage = when (uiState.error) {
                        is AppError.NetworkError -> "Ağ bağlantısı kurulamadı. İnternet erişiminizi kontrol edin."
                        is AppError.NotFoundError -> "Aranan şehir verisi bulunamadı."
                        is AppError.UnknownError -> uiState.error.message ?: "Bilinmeyen bir hata oluştu."
                    }
                    ErrorComponent(
                        errorMessage = errorMessage,
                        onRetry = onRetry
                    )
                }

                uiState.isEmpty -> {
                    EmptyComponent(
                        onRefresh = onRetry
                    )
                }

                else -> {
                    // İçerik Yüklendi (Content/Success) Durumu
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Şehir Seçimi & Tahminler",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Favori şehirlerinizi seçebilir veya detay için tıklayabilirsiniz:",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (uiState.favoriteCities.isNotEmpty()) {
                            Text(
                                text = "Favori Şehir Sayınız: ${uiState.favoriteCities.size} (Favoriler ekranı için kalbe basın)",
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
        }
    }
}

@Preview(name = "Ana Ekran - Başarılı Önizleme", showBackground = true)
@Composable
fun MainScreenSuccessPreview() {
    HavaTheme {
        MainScreen(
            uiState = WeatherUiState(
                cities = listOf(
                    CityUiModel("istanbul", "İstanbul", "22°C", "Güneşli", isFavorite = true),
                    CityUiModel("ankara", "Ankara", "18°C", "Parçalı Bulutlu", isFavorite = false)
                )
            ),
            onCityClick = {},
            onFavoriteToggle = {},
            onNavigateToFavorites = {},
            onRetry = {}
        )
    }
}
