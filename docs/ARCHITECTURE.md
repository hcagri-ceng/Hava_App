# Mimari

Tek `:app` modülü, **feature-first** paketleme; her feature kendi `data / domain / presentation` katmanına sahiptir.
Bağımlılık yönü: `presentation → domain ← data`. Domain saf Kotlin'dir; `LayerDependencyTest` domain'de Android/Retrofit/Room importunu, presentation'da data importunu yasaklar.

## Paket haritası

```
com.kampplus.hava
├── HavaApplication.kt / MainActivity.kt / HavaApp.kt
├── core/
│   ├── common/        AppResult, AppError, ErrorMapper (+ Default), dispatcher qualifier'ları, CommonModule
│   ├── network/       NetworkModule (OkHttp, Json, forecast/geocoding Retrofit'leri), NetworkErrorMapper
│   ├── database/      HavaDatabase, DatabaseModule
│   ├── ui/            theme, component (Loading/Error/Empty/Shimmer/FavoriteToggle), UiState, UiText
│   └── navigation/    Destinations, TopLevelDestination, BottomBar, HavaNavHost
└── feature/
    ├── weather/
    │   ├── domain/    model (City, Coordinates, WeatherCode, CurrentWeather, CityWeather, Forecast…),
    │   │              repository (WeatherRepository, CityRepository), usecase, policy (WeatherConditionClassifier)
    │   ├── data/      local/CityCatalog (+ TurkishCityCatalog), remote (Fake/OpenMeteo data source'ları, api, dto),
    │   │              mapper, repository, di
    │   └── presentation/ list (CityList*), detail (ForecastDetail*), model (UI modelleri, WeatherUiMapper,
    │                  WeatherConditionUiRegistry), di
    └── favorites/
        ├── domain/    FavoriteCity, FavoriteCityRepository, Observe/Toggle use case'leri
        ├── data/      FavoriteCityLocalDataSource (InMemory → Room), dao, entity, repository, di
        └── presentation/ Favorites*
```

## Veri akışı

`Retrofit/Room → DataSource → RepositoryImpl (Flow<AppResult<…>>) → UseCase → ViewModel (StateFlow<UiState>) → Route (collectAsStateWithLifecycle) → Screen (stateless)`

## Open/Closed genişleme noktaları

| Senaryo | Eklenir | Değişir | Dokunulmaz |
|---|---|---|---|
| Sabit veri → gerçek API (CP3 → CP4) | `OpenMeteoWeatherRemoteDataSource`, DTO'lar, mapper | `WeatherDataModule` (1 `@Binds`) | Domain, ViewModel, ekranlar |
| Favoriler bellek → disk (CP3 → CP4) | `RoomFavoriteCityDataSource`, entity, DAO | `FavoritesDataModule` (1 `@Binds`) | Use case'ler, ViewModel'ler |
| Hata eşleme genel → ağ (CP4) | `NetworkErrorMapper` | `CommonModule` (1 `@Binds`) | Repository'ler |
| Yeni hava durumu görünümü (ör. dolu) | `WeatherConditionUiModule`'e `@IntoMap` girdisi | — | Mapper, ekranlar |
| Farklı şehir listesi (ör. Avrupa başkentleri) | Yeni `CityCatalog` implementasyonu | `WeatherDataModule` | Tüm üst katmanlar |
| Yeni hava değişkeni (ör. UV indeksi) | DTO alanı + domain alanı (varsayılanlı) | Mapper, istek parametre listesi | ViewModel sözleşmeleri |
