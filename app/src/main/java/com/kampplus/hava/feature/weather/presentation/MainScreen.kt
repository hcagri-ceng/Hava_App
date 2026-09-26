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
import com.kampplus.hava.core.ui.component.ContentCard
import com.kampplus.hava.core.ui.theme.HavaTheme

/**
 * CP1 İstenenleri 2: MainScreen ekran bileşeni.
 * Arayüz başlığı, açıklaması ve tekrar kullanılabilir ContentCard bileşenini içerir.
 */
@Composable
fun MainScreen(
    appTitle: String,
    appDescription: String,
    cardTitle: String,
    cardDescription: String,
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

            // Tekrar kullanılabilir İçerik Bileşeni (ContentCard)
            ContentCard(
                title = cardTitle,
                description = cardDescription
            )
        }
    }
}

/**
 * CP1 İstenenleri 3: Görünümü doğrulama - Kısa Metin Önizlemesi (@Preview)
 */
@Preview(name = "Kısa Metin Önizlemesi", showBackground = true)
@Composable
fun MainScreenShortTextPreview() {
    HavaTheme {
        MainScreen(
            appTitle = "Hava Durumu Uygulaması",
            appDescription = "KAMP+ CP1 Ekranı",
            cardTitle = "İstanbul",
            cardDescription = "Güneşli 22°C"
        )
    }
}

/**
 * CP1 İstenenleri 3: Görünümü doğrulama - Uzun Metin Önizlemesi (@Preview)
 */
@Preview(name = "Uzun Metin Önizlemesi", showBackground = true)
@Composable
fun MainScreenLongTextPreview() {
    HavaTheme {
        MainScreen(
            appTitle = "Hava Durumu & Tahmin Portalı",
            appDescription = "Bu ekran Jetpack Compose ve Material3 kullanılarak CP1 kapsamında geliştirilmiştir.",
            cardTitle = "Ankara - Detaylı Hava Tahmini",
            cardDescription = "Bugün havanın parçalı bulutlu ve yer yer sağanak yağışlı olması beklenmektedir. Rüzgar hızı güneybatı yönünden saatte 18 km hızla esecektir."
        )
    }
}
