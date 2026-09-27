## Check Point 1 (CP1)

<h1>Amaç</h1>
    Uygulamanın çalıştırılması , ilk ekranın parametre alan Compose(Technical_Terms'de anlamı mevcut) bileşenleriyle oluşturulması ve değişikliklerin git ile izlenmesi.
    
<h1>İstenenler</h1> 
    1. Uygulamanın girişini inceleyin. MainActivity , setContent ve tema ilişkisini belirleyin. Ana başlık ve açıklamasını düzenleyin. 
    2. Bileşenleri oluşturun.Arayüzü MainScreen ve tekrar kullanılabilir bir içerik bileşenine ayırın. Metinleri parametrelerle iletin.  
    3. Görünümü doğrulayın.Modifier ile boyut ve boşlukları düzenleyin. Farklı metin uzunlukları için  iki @Priview tanımlayın. 
    4. Değişiklikleri kaydedin. Uygulamayı cihazda veya emülatörde çalıştırın. Eğitimde belirlenen Git akışına uygun dal ve commit oluşturun.

<h1>Geliştirme Adımları CP1</h1>

### CP1 Geliştirme Adımları & Yapılan İşlemler 

#### 1. Proje ve Gradle Kurulumu
* `build.gradle.kts`, `settings.gradle.kts` ve `gradle/libs.versions.toml` dosyaları oluşturularak **Jetpack Compose** ve **Material3** kütüphaneleri projeye kuruldu.
* Android Studio'nun projeyi tanıması için `gradle-wrapper` araçları yapılandırıldı.

#### 2. Tema Yapısının Kurulması (`core/ui/theme`)
* **`Color.kt`**: Uygulamanın ana renk paleti tanımlandı.
* **`Type.kt`**: Yazı boyutu ve tipleri (typography) belirlendi.
* **`Theme.kt`**: Uygulamanın genel görünümünü yöneten `HavaTheme` bileşeni oluşturuldu.

#### 3. Tekrar Kullanılabilir Kart Bileşeni (`core/ui/component`)
* **`ContentCard.kt`**: Dışarıdan `title` (başlık) ve `description` (açıklama) parametrelerini alan tekrar kullanılabilir bir kart bileşeni yazıldı. `Modifier` kullanılarak iç/dış boşluklar (padding) ve kart boyutları ayarlandı.

#### 4. Ana Ekran ve Önizlemeler (`feature/weather/presentation`)
* **`MainScreen.kt`**: Ana ekran tasarımı kodlandı. İçi boş bırakılmayıp metinler parametre olarak iletildi ve `ContentCard` bileşeni ekranın içinde çağrıldı.
* **`@Preview` Tanımları**: Tasarımı emülatörsüz doğrulayabilmek için iki farklı metin uzunluğunda önizleme eklendi (`MainScreenShortTextPreview` ve `MainScreenLongTextPreview`).

#### 5. Uygulama Girişi (`MainActivity.kt`)
* `MainActivity` içinde `setContent { HavaTheme { MainScreen(...) } }` yapısı kurularak Compose ekranı Android uygulamasına bağlandı.

#### 6. Git & GitHub Akışı
* `.gitignore` eklendi, başlangıç için `cp1_baslangic` ve tamamlanmış hali için `cp1_bitis` branch'leri açıldı. Tüm kodlar GitHub'a push'landı.


## Check Point 2 (CP2)

 <h1>Amaç</h1>
    En az iki ekranın ortak arayüz bileşenleriyle oluşturulması ; ekranlar arası parametre aktarımının ve geri dönüş davranışının yönetilmesi.
 <h1>İstenenler</h1>
    1.Ekranları tanımlayın.Ana ekranı ve ikinci ekranı oluşturun. Her ekranın sorumluluğunu ve ihtiyaç duyduğu verileri belirleyin. 
    2.Geçişleri yapılandırın. Navhost ile hedefleri tanımlayın. Geçiş için gereken kimlik veya küçük bir parametreyi ikinci ekrana aktarın. 
    3.Bileşenleri ayırın. Navigasyon kararını ekran katmanında tutun. Alt bileşenleri kullanıcı aksiyonlarını callback ile bildirsin.
    4. Geri dönüşü doğrulayın. Sistem Geri işlemini ve geçersiz parametre durumunu sınayın. Her durumda anlaşılır bir geri dönüş yolu sağlayın. 

<h1>Geliştirme Adımları CP2</h1>

### CP2 Geliştirme Adımları & Yapılan İşlemler

#### 1. Jetpack Navigation Bağımlılığının Eklenmesi
* `libs.versions.toml` ve `app/build.gradle.kts` dosyalarına `androidx.navigation:navigation-compose` kütüphanesi eklendi.

#### 2. Navigasyon Hedeflerinin Oluşturulması (`core/navigation`)
* **`Destinations.kt`**: `Home` (`"home"`) ve `Detail` (`"detail/{cityId}"`) rotaları güvenli şekilde tanımlandı.
* **`HavaNavHost.kt`**: `NavHost` bileşeni kurularak ana ekran ile şehir detay ekranı birbirine bağlandı. `cityId` parametresi ikinci ekrana aktarıldı.

#### 3. Tıklanabilir Şehir Kartı Bileşeni (`core/ui/component`)
* **`CityCard.kt`**: Şehir adı, hava durumu ve sıcaklık bilgilerini gösteren kart oluşturuldu. Tıklama aksiyonu `onClick: () -> Unit` callback fonksiyonu ile üst katmana iletildi.

#### 4. Ekranların Ayrılması (`feature/weather/presentation`)
* **`MainScreen.kt`**: Şehir listesi ekranı güncellendi. Navigasyon kararları ekranın dışına alınarak `onCityClick: (String) -> Unit` callback yapısı bağlandı.
* **`DetailScreen.kt`**: Şehir detay ekranı oluşturuldu. Sol üst alana geri tuşu (`IconButton`) ve TopBar eklendi.
* **Geçersiz Parametre Yönetimi**: `cityId` parametresinin `null`, boş veya hatalı geldiği durumlar sınandı; bu durumlarda özel hata kartı ve "Ana Ekrana Dön" butonu gösterildi.

#### 5. Uygulama Girişi ve Geri Dönüş Yapısı (`MainActivity.kt`)
* `MainActivity` içerisine `rememberNavController()` bağlandı ve `HavaNavHost` üzerinden sistem geri tuşu (`popBackStack`) entegre edildi.

## Check Point 3 (CP3) 

<h1> Amaç </h1> 
    Kullanıcı etkileşimleriyle değişen ekran durumunu ortak ViewModel'de yönetilmesi ve arayüzün tek bir durum kaynağından beslenmesi.
<h1>İstenenler</h1>
    1. State modelini tanımlayın. Bir seçim veya görünüm tercihini temsil eden UiState oluşturun. Durumu StateFlow<UiState> üzerinden sunun. 
    2. Ortak Sahipliği kurun. Navhost üstünde viewModel() ile ortak örnek alın. İlgili ekranların aynı durum kaynağını kullanmasını sağlayın. 
    3. Aksiyonları işleyin. Aksiyonları ViewModel fonksiyonlarına sahip iletip yeni state üretin. Ekranda collectAsStateWithLifeCycle() ile gözlemleyin.
    4. Tutarlılığı doğrulayın. Ekran geçişinde ve cihaz döndürüldüğünde seçimin  korunduğunu doğrulayın. Türetilen bilgileri mevcut state 'ten hesaplayın. 

<h1>Geliştirme Adımları CP3</h1>

### CP3 Geliştirme Adımları & Yapılan İşlemler

#### 1. Lifecycle ve ViewModel Bağımlılıklarının Eklenmesi
* `libs.versions.toml` ve `app/build.gradle.kts` dosyalarına `lifecycle-runtime-compose` ve `lifecycle-viewmodel-compose` kütüphaneleri eklendi.

#### 2. UiState Durum Modelinin Oluşturulması (`feature/weather/presentation/model`)
* **`WeatherUiState.kt`**: Ekranın anlık durumunu tutan `WeatherUiState` ve `CityUiModel` data sınıfı yazıldı.
* **Türetilmiş Durum (Derived State)**: Mevcut state üzerinden hesaplanan `favoriteCities` (favori şehirler listesi) adında ikincil durum eklendi.

#### 3. Ortak ViewModel ve StateFlow Yapısı (`feature/weather/presentation`)
* **`WeatherViewModel.kt`**: Ekran durumunu `StateFlow<WeatherUiState>` üzerinden sunan ViewModel yazıldı.
* **Aksiyon Fonksiyonları**: Şehir seçimi (`selectCity`) ve favori ekleme/çıkarma (`toggleFavorite`) aksiyonları tanımlandı.

#### 4. Ortak ViewModel Sahipliği ve Canlı Veri Takibi (`core/navigation`)
* **`HavaNavHost.kt`**: `NavHost` seviyesinde `val weatherViewModel: WeatherViewModel = viewModel()` ile ortak ViewModel sahipliği kuruldu.
* **`collectAsStateWithLifecycle()`**: StateFlow veri akışı Compose yaşam döngüsüne duyarlı şekilde dinlendi. Hem `MainScreen` hem de `DetailScreen` aynı ViewModel'den beslendi.

#### 5. Arayüz ve Favori Entegrasyonu (`core/ui/component` & `presentation`)
* **`CityCard.kt`**: Şehir kartına kalpli favori ikonu eklendi.
* **`MainScreen.kt` & `DetailScreen.kt`**: Favori durumları canlı olarak güncellenebilir ve her iki ekrandan da senkronize yönetilebilir hale getirildi. Cihaz döndürüldüğünde durum korundu.
