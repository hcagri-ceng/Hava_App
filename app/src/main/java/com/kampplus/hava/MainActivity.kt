package com.kampplus.hava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kampplus.hava.core.ui.theme.HavaTheme
import com.kampplus.hava.feature.weather.presentation.MainScreen

/**
 * CP1 İstenenleri 1: Uygulamanın girişi.
 * MainActivity, setContent ve HavaTheme ilişkisi kurulmuştur.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HavaTheme {
                MainScreen(
                    appTitle = "Hava Durumu Uygulaması",
                    appDescription = "Jetpack Compose ile CP1 Ekranı",
                    cardTitle = "Günlük Özet",
                    cardDescription = "Arayüz bileşenleri parametreler ile başarıyla oluşturuldu."
                )
            }
        }
    }
}
