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
