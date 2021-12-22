-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 22, 2021 lúc 05:52 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `covid-19`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id_account` int(11) NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id_account`, `password`, `username`) VALUES
(1, '123', '20186303'),
(2, '123', 'huys'),
(3, '123', '20186300'),
(4, '123', '2018630'),
(5, '123', '2000186397'),
(6, '123', '20186'),
(7, '123', '2018'),
(8, '123', '2000186356'),
(9, '123', '200018635');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address`
--

CREATE TABLE `address` (
  `addr_id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `color_area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number_f0` int(11) DEFAULT NULL,
  `number_f1` int(11) DEFAULT NULL,
  `number_user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `address`
--

INSERT INTO `address` (`addr_id`, `address`, `color_area`, `number_f0`, `number_f1`, `number_user`) VALUES
(1, 'Định Công Hoàng Mai Hà Nội', NULL, 1, 0, 6),
(5, 'Định CônHoàng Mai Hà Nội', NULL, 0, 0, 1),
(6, 'Mai Hà Nội', NULL, 0, 0, 1),
(4, 'Định CôngHà Nội', NULL, 1, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `datacontact`
--

CREATE TABLE `datacontact` (
  `id` int(11) NOT NULL,
  `user_one` int(11) DEFAULT NULL,
  `user_two` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `datacontact`
--

INSERT INTO `datacontact` (`id`, `user_one`, `user_two`) VALUES
(9, 3, 4),
(2, 1, 3),
(3, 2, 1),
(8, 2, 3),
(5, 6, 7),
(6, 7, 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dataf0`
--

CREATE TABLE `dataf0` (
  `f0_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dataf0`
--

INSERT INTO `dataf0` (`f0_id`, `user_id`) VALUES
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dataf1`
--

CREATE TABLE `dataf1` (
  `f1_id` int(11) NOT NULL,
  `f0_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `declaration`
--

CREATE TABLE `declaration` (
  `dec_id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phonenumber` int(11) DEFAULT NULL,
  `schedule` longblob DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `declaration`
--

INSERT INTO `declaration` (`dec_id`, `address`, `phonenumber`, `schedule`, `user_id`) VALUES
(27, NULL, NULL, 0x686f616e670a6875790a, 3),
(20, NULL, NULL, 0x686f616e670a6875790a686f616e670a6875790a6875790a686f616e670a, 1),
(26, NULL, NULL, 0x686f616e670a6875790a, 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `cmnd` int(11) NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `address`, `birthday`, `cmnd`, `email`, `fullname`, `username`) VALUES
(1, 'Định Công Hoàng Mai Hà Nội', '2000-10-10', 187870845, 'nguyendanghoang01112000@gmail.com', 'Hoàng Nguyễn Đăng', '20186303'),
(5, 'Mai Hà Nội', '2000-10-10', 187686669, 'ng011100@gmail.com', 'Hoàng Nguyễn Đăng', '2000186397'),
(2, 'Định CôngHà Nội', '2000-10-10', 468, 'dng01112000@gmail.com', 'Huy Nguyễn Đăng', 'huys'),
(3, 'Định CônHoàng Mai Hà Nội', '2000-10-10', 1875, 'ng000@gmail.com', 'Hoàng Nguyễn Đăng', '20186300'),
(4, 'Định Công Hoàng Mai Hà Nội', '2000-10-10', 45615, 'nang01112000@gmail.com', 'Hoàng Nguyễn Đăng', '2018630'),
(6, 'Định Công Hoàng Mai Hà Nội', '2000-10-10', 4561578, 'nang1112000@gmail.com', 'Hoàng Nguyễn Đăng', '20186'),
(7, 'Định Công Hoàng Mai Hà Nội', '2000-10-10', 45615785, 'nang111200@gmail.com', 'Hoàng Nguyễn Đăng', '2018'),
(8, 'Định Công Hoàng Mai Hà Nội', '2000-01-01', 1876866965, 'dangh2000@gmail.com', 'Huy', '2000186356'),
(9, 'Định Công Hoàng Mai Hà Nội', '2000-10-10', 1876866, 'ng0111000@gmail.com', 'Hoàng Nguyễn Đăng', '200018635');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id_account`);

--
-- Chỉ mục cho bảng `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`addr_id`),
  ADD UNIQUE KEY `UK_h8jov0p3cffixl6yhdy4hb2gi` (`address`) USING HASH;

--
-- Chỉ mục cho bảng `datacontact`
--
ALTER TABLE `datacontact`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dataf0`
--
ALTER TABLE `dataf0`
  ADD PRIMARY KEY (`f0_id`);

--
-- Chỉ mục cho bảng `dataf1`
--
ALTER TABLE `dataf1`
  ADD PRIMARY KEY (`f1_id`);

--
-- Chỉ mục cho bảng `declaration`
--
ALTER TABLE `declaration`
  ADD PRIMARY KEY (`dec_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_1reay28n15c236vdhltupv4rn` (`cmnd`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id_account` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `address`
--
ALTER TABLE `address`
  MODIFY `addr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `datacontact`
--
ALTER TABLE `datacontact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `dataf0`
--
ALTER TABLE `dataf0`
  MODIFY `f0_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT cho bảng `dataf1`
--
ALTER TABLE `dataf1`
  MODIFY `f1_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT cho bảng `declaration`
--
ALTER TABLE `declaration`
  MODIFY `dec_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
