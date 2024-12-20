# EterationChallenge

EterationChallenge, bir e-ticaret uygulaması için ürün listeleme, arama, sepet yönetimi gibi temel işlevleri örnekleyen bir Android projesidir. Bu proje, Clean Architecture prensiplerini, MVVM yapısını, Paging kütüphanesi, Room, Retrofit, Hilt, Coroutines ve Flow gibi modern Android geliştirme bileşenlerini kullanarak ölçeklenebilir ve test edilebilir bir mimari sunar.

## İçerik

- [Özellikler](#özellikler)
- [Mimari](#mimari)
- [Kullanılan Teknolojiler ve Kütüphaneler](#kullanılan-teknolojiler-ve-kütüphaneler)


## Özellikler

- **Ürün Listeleme:** Tüm ürünlerin sayfalama (Paging) desteği ile listelenmesi.
- **Ürün Detayı:** Belirli bir ürüne ait ayrıntılı bilgilerin gösterilmesi.
- **Arama Fonksiyonu:** Kullanıcılar, ürün isimlerine göre arama yaparak sonuçları filtreleyebilir.
- **Sepet Yönetimi:** Ürünleri sepete ekleme, çıkarma, miktarlarını güncelleme ve toplam fiyat hesabı.
- **Offline Desteği:** Room veritabanı ile yerel depolama.
- **Badge gösterme:** Sepetteki ürün sayısını gösterir.
- **Loading ve error handling:** Uygulamada Loading ve error handling yapıldı
- **Header ve Footer:** Uygulamada internet kesilmesi gibi durumlarda header ve footer gösteriliyor.

## Mimari

Proje Clean Architecture prensiplerini takip eder:

- **Data Katmanı:**  
  Repository implementasyonları, veri kaynakları (API, veritabanı) ve modeller.
- **Domain Katmanı:**  
  Use Case'ler (iş mantığı), domain modelleri ve repository arayüzleri.
- **UI (Presentation) Katmanı:**  
  ViewModel’ler, UI katmanı (Fragment, Activity), StateFlow kullanımı.

Bu yaklaşım, modüllerin birbirinden gevşek bağlı olmasını, test edilebilirliği ve bakımı kolaylaştırır.

## Kullanılan Teknolojiler ve Kütüphaneler

- **Kotlin & Coroutines:** Asenkron işlemleri basitleştirir.
- **Flow & StateFlow:** Reaktif veri yönetimi.
- **Hilt (Dependency Injection):** Kolay bağımlılık yönetimi.
- **Retrofit & OkHttp:** API istekleri.
- **Room:** Yerel veritabanı yönetimi.
- **Paging 3:** Büyük veri setlerini sayfalama ile yönetme.
- **Coil:** Görsel yükleme.

