-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 13 Des 2017 pada 11.42
-- Versi Server: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `didinnuryahya`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('ahmad', '12345'),
('didin', '456');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pengembalian` varchar(20) NOT NULL,
  `id_pinjam` int(11) NOT NULL,
  `nama_peminjam` varchar(30) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `biaya_pengembalian` int(10) NOT NULL,
  `administrasi` int(10) NOT NULL,
  `Total` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id_pengembalian`, `id_pinjam`, `nama_peminjam`, `tanggal`, `biaya_pengembalian`, `administrasi`, `Total`) VALUES
('P00001', 3, 'ahmad', '13 Desember 2017', 12000, 600, 12600),
('P00002', 1, 'didin', '13 Desember 2017', 1000, 50, 1050),
('P00003', 1, 'didin', '13 Desember 2017', 99000, 4950, 103950),
('P00004', 1, 'didin', '13 Desember 2017', 99000, 4950, 103950),
('P00005', 1, 'didin', '13 Desember 2017', 10000, 500, 10500),
('P00006', 3, 'ahmad', '13 Desember 2017', 20000, 1000, 21000),
('P00007', 3, 'ahmad', '13 Desember 2017', 2000, 100, 2100);

--
-- Trigger `pengembalian`
--
DELIMITER $$
CREATE TRIGGER `pengembalian` AFTER INSERT ON `pengembalian` FOR EACH ROW UPDATE pinjam SET besar=pinjam.besar-New.biaya_pengembalian WHERE id=New.id_pinjam
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjam`
--

CREATE TABLE `pinjam` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `besar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pinjam`
--

INSERT INTO `pinjam` (`id`, `nama`, `keterangan`, `tanggal`, `besar`) VALUES
(1, 'didin', 'pinjam', '12-11-2017', 0),
(3, 'ahmad', 'pinjam', '12-11-2000', 378000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `simpan`
--

CREATE TABLE `simpan` (
  `id_simpan` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `tgl` varchar(20) NOT NULL,
  `besar` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `simpan`
--

INSERT INTO `simpan` (`id_simpan`, `nama`, `keterangan`, `tgl`, `besar`) VALUES
(2, 'didin nur yahya', 'ngutang', '11-12-2017', 2000),
(3, 'ahmad syaifudin', 'ngutang', '12-11-2000', 2000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pengembalian`);

--
-- Indexes for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `simpan`
--
ALTER TABLE `simpan`
  ADD PRIMARY KEY (`id_simpan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `simpan`
--
ALTER TABLE `simpan`
  MODIFY `id_simpan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
