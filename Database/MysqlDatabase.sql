-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 16 Nis 2023, 01:40:42
-- Sunucu sürümü: 10.4.27-MariaDB
-- PHP Sürümü: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `otomasyon`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `alerjiler`
--

CREATE TABLE `alerjiler` (
  `hasta_id` bigint(20) UNSIGNED NOT NULL,
  `alerji_adi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ameliyat`
--

CREATE TABLE `ameliyat` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `doktor_id` bigint(20) UNSIGNED NOT NULL,
  `hasta_id` bigint(20) UNSIGNED NOT NULL,
  `ameliyathane_id` bigint(10) UNSIGNED NOT NULL,
  `tarih` varchar(255) NOT NULL,
  `saat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ameliyathane`
--

CREATE TABLE `ameliyathane` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `ameliyathane`
--

INSERT INTO `ameliyathane` (`id`, `name`) VALUES
(1, 'ameliyathane1'),
(2, 'ameliyathane2'),
(3, 'Ameliyathane3'),
(4, 'Ameliyathane4'),
(5, 'Ameliyathane5');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `doktor_detail`
--

CREATE TABLE `doktor_detail` (
  `doktor_id` bigint(20) UNSIGNED NOT NULL,
  `alan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hasta_detail`
--

CREATE TABLE `hasta_detail` (
  `kişi_id` bigint(20) UNSIGNED NOT NULL,
  `hastalıgı` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `poliklinik`
--

CREATE TABLE `poliklinik` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `poliklinik_adi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `poliklinik`
--

INSERT INTO `poliklinik` (`id`, `poliklinik_adi`) VALUES
(1, 'Fizik Tedavi'),
(2, 'Acil'),
(3, 'Göz'),
(4, 'Diş'),
(5, 'Çocuk'),
(6, 'Kalp'),
(8, 'Genel Cerrhai'),
(9, 'Damar'),
(11, 'Fizyoterapi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `randevu`
--

CREATE TABLE `randevu` (
  `randevu_id` bigint(20) UNSIGNED NOT NULL,
  `hasta_id` bigint(20) UNSIGNED DEFAULT NULL,
  `doktor_id` bigint(20) UNSIGNED NOT NULL,
  `poliklinik_id` bigint(20) UNSIGNED NOT NULL,
  `tarih` varchar(255) NOT NULL,
  `saat` varchar(255) NOT NULL,
  `kontrol` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `tahliller`
--

CREATE TABLE `tahliller` (
  `hasta_id` bigint(20) UNSIGNED NOT NULL,
  `tahlil_adi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tc` varchar(255) NOT NULL,
  `type` enum('hasta','doktor','bashekim') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `alerjiler`
--
ALTER TABLE `alerjiler`
  ADD KEY `hasta_id` (`hasta_id`);

--
-- Tablo için indeksler `ameliyat`
--
ALTER TABLE `ameliyat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `doktor_id` (`doktor_id`),
  ADD KEY `hasta_id` (`hasta_id`),
  ADD KEY `ameliyathane_id` (`ameliyathane_id`);

--
-- Tablo için indeksler `ameliyathane`
--
ALTER TABLE `ameliyathane`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `doktor_detail`
--
ALTER TABLE `doktor_detail`
  ADD KEY `doktor_id` (`doktor_id`);

--
-- Tablo için indeksler `hasta_detail`
--
ALTER TABLE `hasta_detail`
  ADD KEY `kişi_id` (`kişi_id`);

--
-- Tablo için indeksler `poliklinik`
--
ALTER TABLE `poliklinik`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `randevu`
--
ALTER TABLE `randevu`
  ADD PRIMARY KEY (`randevu_id`),
  ADD KEY `hasta_id` (`hasta_id`),
  ADD KEY `doktor_id` (`doktor_id`),
  ADD KEY `poliklinik_id` (`poliklinik_id`);

--
-- Tablo için indeksler `tahliller`
--
ALTER TABLE `tahliller`
  ADD KEY `hasta_id` (`hasta_id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `ameliyat`
--
ALTER TABLE `ameliyat`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `ameliyathane`
--
ALTER TABLE `ameliyathane`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `poliklinik`
--
ALTER TABLE `poliklinik`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `randevu`
--
ALTER TABLE `randevu`
  MODIFY `randevu_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `alerjiler`
--
ALTER TABLE `alerjiler`
  ADD CONSTRAINT `alerjiler_ibfk_1` FOREIGN KEY (`hasta_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `ameliyat`
--
ALTER TABLE `ameliyat`
  ADD CONSTRAINT `ameliyat_ibfk_1` FOREIGN KEY (`doktor_id`) REFERENCES `doktor_detail` (`doktor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ameliyat_ibfk_2` FOREIGN KEY (`hasta_id`) REFERENCES `hasta_detail` (`kişi_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ameliyat_ibfk_3` FOREIGN KEY (`ameliyathane_id`) REFERENCES `ameliyathane` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `doktor_detail`
--
ALTER TABLE `doktor_detail`
  ADD CONSTRAINT `doktor_detail_ibfk_1` FOREIGN KEY (`doktor_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `hasta_detail`
--
ALTER TABLE `hasta_detail`
  ADD CONSTRAINT `hasta_detail_ibfk_1` FOREIGN KEY (`kişi_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `randevu`
--
ALTER TABLE `randevu`
  ADD CONSTRAINT `randevu_ibfk_1` FOREIGN KEY (`hasta_id`) REFERENCES `hasta_detail` (`kişi_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `randevu_ibfk_2` FOREIGN KEY (`doktor_id`) REFERENCES `doktor_detail` (`doktor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `randevu_ibfk_3` FOREIGN KEY (`poliklinik_id`) REFERENCES `poliklinik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `tahliller`
--
ALTER TABLE `tahliller`
  ADD CONSTRAINT `tahliller_ibfk_1` FOREIGN KEY (`hasta_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
