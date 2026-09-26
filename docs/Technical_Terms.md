# CP1 Teknik Terimler Sözlüğü

Bu doküman, `Developing_Steps.md` rehberinde (Check Point 1) geçen teknik terimlerin en basit ve anlaşılır açıklamalarını içerir.

---

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
> Ana projenin koduna zarar vermeden yeni bir özellik geliştirmek veya denemeler yapmak üzere açılan bağımsız çalışma alanıdır (örneğin: `cp1_başlangıç`).

### 12. Commit
> Yapılan değişikliklerin o anki halini bir açıklama mesajı ile birlikte Git kaydı olarak dondurma/kaydetme işlemidir (kayıt noktası).
