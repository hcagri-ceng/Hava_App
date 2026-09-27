package com.kampplus.hava.feature.favorites.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.kampplus.hava.core.ui.component.CityCard
import com.kampplus.hava.core.ui.theme.HavaTheme
import com.kampplus.hava.feature.weather.presentation.model.CityUiModel

/**
 * Favori Şehirler Ekranı.
 * Yalnızca favori olarak işaretlenen şehirleri listeler ve boş durumda (Empty State) bilgilendirme mesajı verir.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    favoriteCities: List<CityUiModel>,
    onCityClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favori Şehirlerim (${favoriteCities.size})") },
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
            if (favoriteCities.isEmpty()) {
                // Empty State (Boş Durum Bileşeni)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Henüz Favori Şehir Yok",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ana ekrandaki şehir listesinde kalp ikonuna basarak favori şehirlerinizi buraya ekleyebilirsiniz.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn {
                    items(favoriteCities, key = { it.id }) { city ->
                        CityCard(
                            cityName = city.name,
                            temperature = city.temperature,
                            weatherCondition = city.condition,
                            isFavorite = true,
                            onFavoriteToggle = { onFavoriteToggle(city.id) },
                            onClick = { onCityClick(city.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Favoriler Ekranı Önizlemesi", showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    HavaTheme {
        FavoritesScreen(
            favoriteCities = listOf(
                CityUiModel("istanbul", "İstanbul", "22°C", "Güneşli", isFavorite = true),
                CityUiModel("izmir", "İzmir", "25°C", "Açık", isFavorite = true)
            ),
            onCityClick = {},
            onFavoriteToggle = {},
            onBackClick = {}
        )
    }
}
