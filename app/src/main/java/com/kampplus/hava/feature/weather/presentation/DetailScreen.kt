package com.kampplus.hava.feature.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kampplus.hava.core.ui.theme.HavaTheme

/**
 * CP2 İstenenleri 1 & 4: Şehir Detay Ekranı.
 * Geçerli parametre durumunda şehir detayını, geçersiz/boş parametre durumunda hata mesajı gösterir.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    cityId: String?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Geçersiz/Bilinmeyen Şehir Parametresi Kontrolü
    val isValidCity = !cityId.isNullOrInvalid()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isValidCity) "$cityId Detayı" else "Hata") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri"
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
                .padding(16.dp)
        ) {
            if (isValidCity) {
                // Geçerli Parametre Durumu
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Şehir: ${cityId?.replaceFirstChar { it.uppercase() }}",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Sıcaklık: 24°C",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Durum: Az Bulutlu",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Nem: %45",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Rüzgar: 12 km/s",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            } else {
                // CP2 İstenenleri 4: Geçersiz Parametre Durumu
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Geçersiz veya Bulunamayan Şehir",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Aradığınız şehre ait detay bilgileri alınamadı.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onBackClick) {
                            Text("Ana Ekrana Dön")
                        }
                    }
                }
            }
        }
    }
}

private fun String?.isNullOrInvalid(): Boolean {
    return this.isNullOrBlank() || this == "{cityId}"
}

@Preview(name = "Geçerli Parametre Önizlemesi", showBackground = true)
@Composable
fun DetailScreenValidPreview() {
    HavaTheme {
        DetailScreen(
            cityId = "istanbul",
            onBackClick = {}
        )
    }
}

@Preview(name = "Geçersiz Parametre Önizlemesi", showBackground = true)
@Composable
fun DetailScreenInvalidPreview() {
    HavaTheme {
        DetailScreen(
            cityId = null,
            onBackClick = {}
        )
    }
}
