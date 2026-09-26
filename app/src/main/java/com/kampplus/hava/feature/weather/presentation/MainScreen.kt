package com.kampplus.hava.feature.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.ui.component.CityCard
import com.kampplus.hava.core.ui.theme.HavaTheme

/**
 * CP2 İstenenleri 1 & 3: Ana Şehir Listesi Ekranı.
 * Navigasyon kararını ekran katmanına bırakır, alt CityCard bileşenleri tıklamayı callback ile iletir.
 */
@Composable
fun MainScreen(
    onCityClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    appTitle: String = "Hava Durumu Uygulaması",
    appDescription: String = "Şehir seçerek detaylı tahminleri inceleyebilirsiniz:"
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
                text = appTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = appDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // CP2 İstenenleri 3: Tıklama aksiyonunu callback (onCityClick) ile bildiren liste ögeleri
            CityCard(
                cityName = "İstanbul",
                temperature = "22°C",
                weatherCondition = "Güneşli",
                onClick = { onCityClick("istanbul") }
            )

            CityCard(
                cityName = "Ankara",
                temperature = "18°C",
                weatherCondition = "Parçalı Bulutlu",
                onClick = { onCityClick("ankara") }
            )

            CityCard(
                cityName = "İzmir",
                temperature = "25°C",
                weatherCondition = "Açık",
                onClick = { onCityClick("izmir") }
            )
        }
    }
}

@Preview(name = "Ana Ekran Önizlemesi", showBackground = true)
@Composable
fun MainScreenPreview() {
    HavaTheme {
        MainScreen(
            onCityClick = {}
        )
    }
}
