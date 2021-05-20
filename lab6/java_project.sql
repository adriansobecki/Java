-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Maj 2021, 06:04
-- Wersja serwera: 10.4.19-MariaDB
-- Wersja PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `java_project`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `weathers`
--

CREATE TABLE `weathers` (
  `id` int(10) UNSIGNED NOT NULL,
  `city_name` varchar(60) NOT NULL,
  `temp` decimal(6,1) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `weathers`
--

INSERT INTO `weathers` (`id`, `city_name`, `temp`, `create_time`) VALUES
(1, 'Wrocław', '12.0', '2021-05-19 19:41:15'),
(2, 'Wrocław', '13.0', '2021-05-20 05:52:11'),
(3, 'Wrocław', '13.0', '2021-05-20 05:52:15');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `weathers`
--
ALTER TABLE `weathers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `weathers`
--
ALTER TABLE `weathers`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
