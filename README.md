# 🌤️ Hava (KAMP+) - Android Weather Application

**Hava**, modern Android geliştirme standartlarına, **Clean Architecture** ilkelerine ve **Feature-First** paketleme yapısına uygun olarak geliştirilmiş Jetpack Compose tabanlı bir hava durumu ve şehir rehberi uygulamasıdır.

Bu proje, adım adım geliştirme aşamalarını (Check Point 1 - 4) ve modern Android mimari desenlerini barındırır.

---

## 📌 Özellikler ve Check Point Aşamaları

### 🔹 Check Point 1 (CP1) - Temel Arayüz ve Bileşen Yapısı
* **Jetpack Compose & Material 3** entegrasyonu ve `HavaTheme` tasarımı.
* `MainActivity` ve `setContent` ile Compose arayüz bağlantısı.
* Tekrar kullanılabilir `ContentCard` bileşeni ve esnek parametre yapısı.
* `@Preview` önizlemeleri ile emülatörsüz arayüz doğrulaması.

### 🔹 Check Point 2 (CP2) - Çoklu Ekran ve Navigasyon
* **Jetpack Navigation Compose** (`NavHost`, `NavController`) entegrasyonu.
* `Home` (`"home"`) ve `Detail` (`"detail/{cityId}"`) rotaları üzerinden ekranlar arası veri/parametre aktarımı (`cityId`).
* Navigasyon kararlarının ekran katmanında tutulması ve alt bileşenlerin `callback` ile etkileşim bildirmesi.
* Cihazın sistem geri tuşu (`popBackStack`) ve geçersiz parametre/şehir durumlarında bilgilendirici hata ekranı.

### 🔹 Check Point 3 (CP3) - Reaktif Durum Yönetimi & ViewModel
* Ekran durumunu tek bir durum kaynağından (Single Source of Truth) yöneten **`WeatherUiState`** modeli.
* `StateFlow` ve `WeatherViewModel` yapısı ile durumların reaktif olarak sunulması.
* `NavHost` seviyesinde **Ortak ViewModel Sahipliği (Shared ViewModel)** kurularak tüm ekranların aynı veriden beslenmesi.
* **`FavoritesScreen` (Favori Şehirler Ekranı)** eklenerek favori şehirlerin canlı olarak senkronize edilmesi.
* `collectAsStateWithLifecycle()` ile yaşam döngüsüne duyarlı canlı veri takibi.
* Mevcut durumdan anlık hesaplanan **Türetilmiş Durum (`favoriteCities`)**.

### 🔹 Check Point 4 (CP4) - Asenkron Veri Akışı ve 4 Ekran Durumu
* Kotlin Coroutines ve `suspend` fonksiyonlar ile asenkron ağ çağrısı simülasyonu (`FakeWeatherRemoteDataSource`).
* Tip güvenli sonuç ve hata modeli (`AppResult.Success`, `AppResult.Error` ve `AppError`).
* `when` yapısı ile yönetilen 4 temel arayüz durumu:
  1. **Yükleniyor (`LoadingComponent`)**: Ağ isteği yürütülürken dairesel yüklenme göstergesi.
  2. **İçerik Yüklendi (`Content`)**: Başarılı şehir listesi ve detaylar.
  3. **Boş Sonuç (`EmptyComponent`)**: Veri bulunamadığında özel mesaj ve *"Verileri Yenile"* seçeneği.
  4. **Hata (`ErrorComponent`)**: Hata mesajı ve **"Tekrar Dene 🔄"** butonu.
* **Eşzamanlı İstek Engelleme (Request Guard)**: `fetchJob` kontrolü ile üst üste tekrarlayan isteklerin engellenmesi.
* Coroutine iptal yönetimi (`CancellationException`).

---

## 🏗️ Proje Mimarisi ve Paket Yapısı

Proje, tek `:app` modülü içinde **Feature-First** yaklaşımıyla paketlenmiştir:

```text
com.kampplus.hava
├── MainActivity.kt               # Uygulama giriş noktası ve NavHost bağlantısı
├── core/
│   ├── common/                   # AppResult, AppError sonuç sarmalayıcıları
│   ├── ui/
│   │   ├── theme/                # Color, Type, Theme (HavaTheme)
│   │   └── component/            # CityCard, ContentCard, LoadingComponent, ErrorComponent, EmptyComponent
│   └── navigation/               # Destinations, HavaNavHost navigasyon haritası
└── feature/
    ├── weather/
    │   ├── data/remote/          # FakeWeatherRemoteDataSource (Asenkron veri kaynağı)
    │   └── presentation/         # MainScreen, DetailScreen, WeatherViewModel, WeatherUiState
    └── favorites/
        └── presentation/         # FavoritesScreen (Favori Şehirler Ekranı)
```

---

## 🛠️ Kullanılan Teknolojiler ve Kütüphaneler

* **Dil**: Kotlin 2.0
* **Arayüz (UI)**: Jetpack Compose & Material 3
* **Mimari Desen**: MVVM / MVI, Clean Architecture, Feature-First
* **Asenkron Akış**: Kotlin Coroutines & `StateFlow`
* **Navigasyon**: Jetpack Navigation Compose (`androidx.navigation:navigation-compose`)
* **Yaşam Döngüsü**: Lifecycle Compose (`lifecycle-runtime-compose`, `lifecycle-viewmodel-compose`)
* **Derleme Sistemi**: Gradle Kotlin DSL (`build.gradle.kts`, `libs.versions.toml`)

---

## 🚀 Projeyi Çalıştırma

1. Projeyi klonlayın:
   ```bash
   git clone https://github.com/hcagri-ceng/Hava_App.git
   cd Hava_App
   ```
2. Android Studio (2024.1+) ile projeyi açın.
3. Gradle senkronizasyonunun (`Sync Project with Gradle Files`) tamamlanmasını bekleyin.
4. Bir Android Emülatörü veya fiziksel cihaz seçip **Play (▶️)** butonuna basarak çalıştırın.

---

## 📚 Dokümantasyon Bağlantıları

* 📐 [Mimari Dokümanı (ARCHITECTURE.md)](docs/ARCHITECTURE.md)
* 📋 [Geliştirme Adımları (Developing_Steps.md)](docs/Developing_Steps.md)
* 📖 [Teknik Terimler Sözlüğü (Technical_Terms.md)](docs/Technical_Terms.md)
