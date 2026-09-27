# Teknik Terimler Sözlüğü

Bu doküman, `Developing_Steps.md` rehberinde (CP1 ve CP2) geçen teknik terimlerin en basit ve anlaşılır açıklamalarını içerir.

---

## 📘 Check Point 1 (CP1) Teknik Terimleri

### 1. Jetpack Compose (Compose)
> Android'de ekran arayüzünü (UI) XML dosyaları yazmadan, doğrudan **Kotlin kodları** ile hızlı ve esnek şekilde oluşturmamızı sağlayan modern arayüz kütüphanesidir.

### 2. Bileşen (Component / Composable)
> Ekran üzerindeki buton, metin, resim gibi her bir arayüz parçasıdır. Compose'da `@Composable` etiketi verilmiş Kotlin fonksiyonları ile yazılır ve tekrar tekrar kullanılabilir.

### 3. MainActivity
> Uygulama telefonda ilk açıldığında çalışan ana ekrandır. Uygulamanın yaşam döngüsünü ve kullanıcının gördüğü ilk pencereyi yönetir.

### 4. setContent
> Jetpack Compose ile hazırladığımız arayüz bileşenlerini Android'in geleneksel ekran yapısına bağlayan ve ekranda çizilmesini/görüntülenmesini sağlayan fonksiyondur.

### 5. Tema (Theme)
> Uygulamanın tüm ekranlarında ortak renkleri, yazı tiplerini (typography) ve bileşen şekillerini (shape) tek bir merkezden tutarlı şekilde yönetmeyi sağlayan yapıdır.

### 6. Parametre (Parameter)
> Bir bileşene dışarıdan veri aktarmak için kullanılan değişkenlerdir. Örneğin bir metin kutusuna gösterilmesi istenen yazıyı parametre olarak iletiriz.

### 7. Modifier
> Compose bileşenlerinin boyutunu (genişlik/yükseklik), boşluklarını (padding), hizalamasını, arka plan rengini veya tıklanabilirlik gibi davranışlarını ayarlayan şekillendiricidir.

### 8. @Preview
> Yazdığımız arayüz kodunun nasıl göründüğünü uygulamayı emülatörde veya gerçek cihazda çalıştırmadan, Android Studio editöründe anlık önizlememizi sağlayan özel bir etikettir (annotation).

### 9. Emülatör (Emulator)
> Bilgisayarınızın içinde sanal bir Android telefon çalıştırarak uygulamanızı test etmenizi sağlayan simülatör yazılımdır.

### 10. Git
> Kodlarınızda yaptığınız tüm değişiklikleri adım adım kaydeden, geriye dönük sürümleri saklayan ve projeyi güvenle yönetmenizi sağlayan versiyon kontrol sistemidir.

### 11. Branch (Dal)
> Ana projenin koduna zarar vermeden yeni bir özellik geliştirmek veya denemeler yapmak üzere açılan bağımsız çalışma alanıdır (örneğin: `cp2_baslangic`).

### 12. Commit
> Yapılan değişikliklerin o anki halini bir açıklama mesajı ile birlikte Git kaydı olarak dondurma/kaydetme işlemidir (kayıt noktası).

---

## 📗 Check Point 2 (CP2) Teknik Terimleri

### 13. NavHost (Jetpack Navigation)
> Ekranlar arası geçişleri (yönlendirmeleri) yöneten, uygulamanın haritasını tutan ve o an hangi ekranın gösterileceğini belirleyen ana navigasyon bileşenidir.

### 14. Hedef (Destination / Route)
> Uygulama içinde yönlendirilebilecek her bir ekranın/sayfanın navigasyon haritasındaki benzersiz adresi veya kimliğidir.

### 15. Parametre Aktarımı (Arguments / Route Parameters)
> Bir ekrandan diğer bir ekrana geçerken (örneğin şehir listesinden şehir detay ekranına geçerken `cityId` gibi) veri taşınması işlemidir.

### 16. Callback (Geri Çağırma Fonksiyonu)
> Alt bileşenlerin (örneğin bir butonun) tıklama gibi olayları üst bileşene/ekrana bildirmesini sağlayan fonksiyon parametreleridir (örneğin `onCityClick: (String) -> Unit`).

### 17. Backstack (Geri Dönüş Yığını) & Sistem Geri İşlemi
> Kullanıcının ziyaret ettiği ekranların sırayla arka planda hafızada tutulması ve cihazın geri butonuna basıldığında bir önceki ekrana güvenle dönülmesini sağlayan yapıdır.

### 18. Geçersiz Parametre Durumu (Invalid / Null Parameter Handling)
> Bir ekrana eksik, hatalı veya bulunamayan bir veri aktarıldığında uygulamanın çökmesini önleyen güvenlik ve hata yönetimi mekanizmasıdır.

---

## 📙 Check Point 3 (CP3) Teknik Terimleri

### 19. ViewModel
> Ekranın durumunu (state) tutan, cihaz döndürüldüğünde dahi verilerin kaybolmamasını sağlayan ve iş mantığını (business logic) yöneten Android mimari bileşenidir.

### 20. UiState (Arayüz Durum Modeli)
> Ekranın o anki tüm görsel durumunu (örneğin seçili şehir, favoriler listesi, yüklenme durumu) tek bir veri sınıfı (data class) içinde temsil eden durum kaynağıdır (Single Source of Truth).

### 21. StateFlow
> Jetpack Compose arayüzünün verilerdeki anlık değişiklikleri canlı olarak dinlemesini ve ekranın otomatik güncellenmesini (recomposition) sağlayan reaktif veri akışıdır.

### 22. collectAsStateWithLifecycle()
> StateFlow içindeki veri akışını Android uygulamasının yaşam döngüsüne (Lifecycle) duyarlı olarak dinleyen, uygulama arka plana gittiğinde kaynak tüketimini durduran Compose fonksiyonudur.

### 23. Ortak ViewModel Sahipliği (Shared ViewModel Ownership)
> Birden fazla ekranın (örneğin Liste ekranı ve Detay ekranı) aynı ViewModel örneğini paylaşarak ortak verileri ve favorileri senkronize şekilde kullanabilmesidir.

### 24. Türetilmiş Durum (Derived State)
> Mevcut ana durumdan (UiState) hesaplanarak elde edilen ikincil verilerdir (örneğin tüm şehirler arasından sadece favori işaretlenmiş olanların hesaplanması).

