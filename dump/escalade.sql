-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jul 30, 2020 at 03:32 PM
-- Server version: 5.7.26
-- PHP Version: 7.1.32

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `escalade`
--
CREATE DATABASE IF NOT EXISTS `escalade` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `escalade`;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `content` longtext,
  `date` datetime DEFAULT NULL,
  `spot_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `content`, `date`, `spot_id`, `user_id`) VALUES
(4, 'commentaire', '2020-06-18 09:14:45', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cotation`
--

DROP TABLE IF EXISTS `cotation`;
CREATE TABLE `cotation` (
  `id` bigint(20) NOT NULL,
  `label` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cotation`
--

INSERT INTO `cotation` (`id`, `label`) VALUES
(1, '3'),
(2, '3+'),
(3, '4a'),
(4, '4b'),
(5, '4c'),
(6, '5a'),
(7, '5b'),
(8, '5c'),
(9, '6a'),
(10, '6a+'),
(11, '6b'),
(12, '6b+'),
(13, '6c'),
(14, '6c+'),
(15, '7a'),
(16, '7a+'),
(17, '7b'),
(18, '7b+'),
(19, '7c'),
(20, '7c+'),
(21, '8a'),
(22, '8a+'),
(23, '8b'),
(24, '8b+'),
(25, '8c'),
(26, '8c+'),
(27, '9a'),
(28, '9a+');

-- --------------------------------------------------------

--
-- Table structure for table `longueur`
--

DROP TABLE IF EXISTS `longueur`;
CREATE TABLE `longueur` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `cotation_max_id` bigint(20) DEFAULT NULL,
  `cotation_min_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `voie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `longueur`
--

INSERT INTO `longueur` (`id`, `description`, `name`, `cotation_max_id`, `cotation_min_id`, `user_id`, `voie_id`) VALUES
(1, 'Première longueur de la voie de la marmite. \nVoie facile à appréhender, conseillée pour les débutants', 'Voie de la marmite : longueur 1', 3, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `photo`
--

INSERT INTO `photo` (`id`, `extension`, `name`) VALUES
(35, 'jpeg', 'photo658c0391-d85b-4d4a-a542-5dcada20f145.jpeg'),
(36, 'jpeg', 'photo37adf9a0-28d4-4dbe-b238-3bc102158f24.jpeg'),
(37, 'jpeg', 'photoc74140d2-4a88-4426-a1d5-6a16be4a0fe9.jpeg'),
(38, 'jpeg', 'photof620928e-67d0-4d06-b026-14d0a8034b0b.jpeg'),
(39, 'jpeg', 'photob579193f-9037-49df-af98-356d1f29ef61.jpeg'),
(40, 'jpeg', 'photodfa5bb03-9d3b-49a8-bfee-f479a6b23ad8.jpeg'),
(41, 'jpeg', 'photo1fa73360-bbba-444a-a88f-10e4f4c2bd16.jpeg'),
(42, 'jpeg', 'photo497d5082-f51c-46b5-82dc-7fe7d58fd1c3.jpeg'),
(43, 'jpeg', 'photofd1dccd1-ae34-4c15-8edf-71012ff57d2a.jpeg'),
(44, 'jpeg', 'photo06674a8e-9594-4ad0-b039-9e1ea1f468a9.jpeg'),
(45, 'jpeg', 'photo0828debc-5ecd-4fbf-acba-5aab330ad6b4.jpeg'),
(46, 'jpeg', 'photoe3dd50bb-47bb-4b99-bf4f-118a749e7028.jpeg'),
(47, 'jpeg', 'photodc994f15-8955-4a4f-8955-b12cc08cdc7b.jpeg'),
(48, 'jpeg', 'photod544158e-8bd0-4e0a-aeaa-bedbd74a038c.jpeg'),
(49, 'jpeg', 'photo80df8116-edf2-4b41-a974-13aaff587478.jpeg'),
(50, 'jpeg', 'photo7fab38d4-c1c7-4581-b05f-0c872105a5f6.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `secteur`
--

DROP TABLE IF EXISTS `secteur`;
CREATE TABLE `secteur` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `spot_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `secteur`
--

INSERT INTO `secteur` (`id`, `description`, `name`, `spot_id`, `user_id`) VALUES
(1, 'Premier secteur du site du Moulay, composé de deux voies. \nTrès représentatif du site Moulay', 'Site Moulay : secteur 1', 6, 1),
(2, 'Second secteur du site Moulay, composé de voies plus difficiles que celles du premier secteur. Attention aux débutants', 'Site Moulay : secteur 2', 6, 1),
(3, 'Seul secteur su site de Changé. Il comprend plusieurs voies (en cours d\'ajout)', 'Secteur du site de Changé', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
CREATE TABLE `spot` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` longtext,
  `isOfficial` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cotation_max_id` bigint(20) DEFAULT NULL,
  `cotation_min_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spot`
--

INSERT INTO `spot` (`id`, `city`, `country`, `description`, `isOfficial`, `name`, `cotation_max_id`, `cotation_min_id`, `user_id`) VALUES
(1, 'Jonte', 'France', 'Encore un des beaux sites de la Jonte, à l\'équipement plutôt proche au regard des grandes classiques du site.', b'1', 'La Jonte - Cirque des vases : Biotone', 20, 10, 1),
(2, 'Jonte', 'France', 'Site équipé par Médéric Tabart en 1987.', b'1', 'La Jonte - Cirque des vases : Plume', 10, 8, 1),
(3, 'Jonte', 'France', 'Itinéraire très esthétique, qui se déroule sur le fil de l\'arête. Rocher magnifique dans ce registre de difficulté, un vrai régal', b'1', 'La Jonte - Cirque des vases : L\'arête', 10, 5, 1),
(4, 'Dévoluy', 'France', 'Le site se situe sur la facette E sommitale de l\'Obiou. L\'approche est conseillée par la voie Paul Arthaud et les variantes de l\'arête bordant le couloir Paul Arthaud en rive droite. On peut également réaliser cette voie depuis l\'accès par la voie normale. Enfin, C\'est aussi une manière élégante de rejoindre le sommet après l\'ascension du pilier NW.\r\n            L\'attaque de la voie Marie-Thérèse est située 20 m à droite d\'une petite tour (10 m de haut) au pied la facette sommitale E de l\'Obiou. Cette tour est aussi appelée le Campanile. La voie attaque dans un mur compact stratifié 50 m à gauche d\'un grand dièdre caractéristique rayant la facette E de haut en bas.', b'0', 'Obiou : Site Marie-Thérèse', 10, 5, 2),
(5, 'Dévoluy', 'France', 'Du chalet des Baumes (Parking sur la route du col des Faïsses, emprunter la voie normale de l\'Obiou par un bon sentier, passer le pas du vallon, puis laisser le sentier à droite, et remonter le Pré du Chourum par des pentes d\'herbe très raides (quelques petits rochers délités) orientées N, qui donnent accès à une arête et la face E du Petit Obiou. Dans un terrain de strates raides, longer la base des falaises vers le S jusqu\'à trouver des marques bleues. Remonter alors le couloir cheminée, parfois par ses rives, parfois au fond. Au-dessus d\'un passage de III on trouve un clou pour assurer un second si nécessaire. Sortir à une antécime à l\'W du sommet.\r\n            Cet itinéraire contourne le Petit Obiou. La traversée du Petit Obiou est plus compliquée et nécessite un rappel.', b'0', 'Obiou : Par le Petit Obiou et les Feuillets', 2, 2, 2),
(6, 'Moulay', 'France', 'Entre 25 et 50 voies réparties sur plusieurs falaises. Equipement sportif. Rocher : granite.', b'1', 'Site de Moulay', 10, 2, 1),
(7, 'Changé', 'France', '10 à 15 voies. Equipement sportif. Rocher : calcaire', b'1', 'Site de Moulay 2', 3, 2, 1),
(8, 'Rochefort-Montagne', 'France', 'Entre 10 et 15 voies. Nécessite un équipement sportif.\nHauteur max : environ 100m.\nType de prises : à plats.\nPour accéder au site : \nSe garer au parking du col de Guéry. Descendre 200m sur la route D80a en direction de Rochefort-Montagne. Quitter la route dans le virage en épingle à cheveu pour prendre la piste forestière sur la gauche. Au premier embranchement, continuer à descendre à droite. A environ 800m, on arrive devant une pancarte F.F.M.E. ; la falaise est en face, légèrement sur la droite en remontant.', b'0', 'La Roche Tuilière', 6, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `spot_photos`
--

DROP TABLE IF EXISTS `spot_photos`;
CREATE TABLE `spot_photos` (
  `Spot_id` bigint(20) NOT NULL,
  `photos_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spot_photos`
--

INSERT INTO `spot_photos` (`Spot_id`, `photos_id`) VALUES
(1, 38),
(1, 39),
(1, 40),
(2, 43),
(3, 41),
(3, 42),
(4, 45),
(5, 44),
(6, 49),
(7, 50),
(8, 46),
(8, 47),
(8, 48);

-- --------------------------------------------------------

--
-- Table structure for table `topo`
--

DROP TABLE IF EXISTS `topo`;
CREATE TABLE `topo` (
  `id` bigint(20) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `publication_date` datetime DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `cotation_max_id` bigint(20) DEFAULT NULL,
  `cotation_min_id` bigint(20) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topo`
--

INSERT INTO `topo` (`id`, `country`, `description`, `name`, `publication_date`, `region`, `cotation_max_id`, `cotation_min_id`, `photo_id`, `creator_id`) VALUES
(1, 'France', 'L\'ouvrage présente les voies d\'escalade des Gorges de la Jonte, situées sur les grandes parois des communes du Rozier et de Saint-Pierre-des-Tripiers.', 'Les gorges de la Jonte', '2020-05-01 00:00:00', 'Lozère', 21, 9, 37, 1),
(2, 'France', 'La Grande Tête de l\'Obiou, ou plus communément l\'Obiou, est un sommet situé dans les Préalpes françaises, à peu près à égale distance de Grenoble (Isère) et de Gap (Hautes-Alpes). Avec ses 2 789 mètres d\'altitude, c\'est le point culminant du massif du Dévoluy', 'Guide de l\'Obiou', '2020-05-01 00:00:00', 'Isère', 18, 3, 36, 2),
(3, 'France', 'Ce topo regroupe les 6 sites de grimpe de Mayenne : Changé, Entrammes, Moulay; St-Berthevin, Saulges et Toyères.', 'Escalade en Mayenne', '2020-05-01 00:00:00', 'Pays de la Loire', 9, 2, 35, 1);

-- --------------------------------------------------------

--
-- Table structure for table `topo_spots`
--

DROP TABLE IF EXISTS `topo_spots`;
CREATE TABLE `topo_spots` (
  `topos_id` bigint(20) NOT NULL,
  `spots_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topo_spots`
--

INSERT INTO `topo_spots` (`topos_id`, `spots_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `topo_user`
--

DROP TABLE IF EXISTS `topo_user`;
CREATE TABLE `topo_user` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) NOT NULL,
  `booking_date` datetime DEFAULT NULL,
  `booking_state` varchar(255) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `topo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topo_user`
--

INSERT INTO `topo_user` (`id`, `available`, `booking_date`, `booking_state`, `owner_id`, `tenant_id`, `topo_id`) VALUES
(6, b'1', NULL, NULL, 1, NULL, 1),
(7, b'1', NULL, NULL, 2, NULL, 2),
(8, b'1', NULL, NULL, 1, NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `username`) VALUES
(1, 'admin@email.com', '$2a$10$Roncy1LUr8JCoRygEkuO6OHSddNY88u0RHQm4CsyRlBAC9SuBfB5K', 'admin'),
(2, 'user1@email.com', '$2a$10$jj5p64AYQeJF4y4EFwlaEuPFYURYwc.w.FQEZx4289coW4f6zP5Gi', 'user1');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `voie`
--

DROP TABLE IF EXISTS `voie`;
CREATE TABLE `voie` (
  `id` bigint(20) NOT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `cotation_max_id` bigint(20) DEFAULT NULL,
  `cotation_min_id` bigint(20) DEFAULT NULL,
  `secteur_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `voie`
--

INSERT INTO `voie` (`id`, `description`, `name`, `cotation_max_id`, `cotation_min_id`, `secteur_id`, `user_id`) VALUES
(1, 'Voie très facile, idéale pour qui souhaite s\'initier à l\'ecalade', 'Voie de la marmite', 2, 2, 1, 1),
(2, 'Voie composée de plusieurs longueurs (en cous d\'ajout). Niveau facile. Attention toutefois, certaines compétences sont nécessaires afin d\'assurer la progression en toute sécurité sur des voies de plusieurs longueurs', 'Voie de java', 6, 6, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKitlok6t6a7917bc3tweondq5b` (`spot_id`),
  ADD KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`);

--
-- Indexes for table `cotation`
--
ALTER TABLE `cotation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `longueur`
--
ALTER TABLE `longueur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcjeoxm79wvnm8estclhipbsjw` (`cotation_max_id`),
  ADD KEY `FK77tw6nat79sgeiekcti4r7chb` (`cotation_min_id`),
  ADD KEY `FKlq7jctub72ex8wco2nq2xse0w` (`user_id`),
  ADD KEY `FKro1y7gu1g630s7j7vaiksn6s5` (`voie_id`);

--
-- Indexes for table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `secteur`
--
ALTER TABLE `secteur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfl8dasd6q488w7uvvq0vn26tb` (`spot_id`),
  ADD KEY `FKpjfiad695limf6qch2vfdp4h5` (`user_id`);

--
-- Indexes for table `spot`
--
ALTER TABLE `spot`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6g2lud0tmk8rg7ja5khu3dvc6` (`cotation_max_id`),
  ADD KEY `FKlv1s949apaw5uie6s9f80gfo6` (`cotation_min_id`),
  ADD KEY `FKmq47ra92ib4lihvk99xmct1eb` (`user_id`);

--
-- Indexes for table `spot_photos`
--
ALTER TABLE `spot_photos`
  ADD UNIQUE KEY `UK_pxlwnue43otb3mlbpyja22q5l` (`photos_id`),
  ADD KEY `FKh1ykaomfp85csv7xe9v9dx92r` (`Spot_id`);

--
-- Indexes for table `topo`
--
ALTER TABLE `topo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcm7reglmm91ojv4e774oyv7gi` (`cotation_max_id`),
  ADD KEY `FK6dqit1ih37xcgi7ndi6dtgst8` (`cotation_min_id`),
  ADD KEY `FKki7p7wwl6yv7boebad42x924q` (`photo_id`),
  ADD KEY `FK2ttc8ak5arbnjirjrto21en71` (`creator_id`);

--
-- Indexes for table `topo_spots`
--
ALTER TABLE `topo_spots`
  ADD KEY `FK353ekac7fa51onv5yr3sfvt19` (`spots_id`),
  ADD KEY `FK7l9fek4330tfu31nfbe6jppbo` (`topos_id`);

--
-- Indexes for table `topo_user`
--
ALTER TABLE `topo_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5votdbcj4862s36d2dwcs7xgk` (`owner_id`),
  ADD KEY `FKt370woq36hdiuhh70nsmeg52h` (`tenant_id`),
  ADD KEY `FKis4c98wg09xtlr6ptmvvt6e6o` (`topo_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- Indexes for table `voie`
--
ALTER TABLE `voie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe6tpgltdj5jicpccj2ye2u4ah` (`cotation_max_id`),
  ADD KEY `FK841h40xgpkfwgcedjm6xdohij` (`cotation_min_id`),
  ADD KEY `FKqlo061c6fgkuosv9chywpgn8t` (`secteur_id`),
  ADD KEY `FKkln3kltxwiapc4av3l9mrdwib` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `cotation`
--
ALTER TABLE `cotation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `longueur`
--
ALTER TABLE `longueur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `secteur`
--
ALTER TABLE `secteur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `spot`
--
ALTER TABLE `spot`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `topo`
--
ALTER TABLE `topo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `topo_user`
--
ALTER TABLE `topo_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `voie`
--
ALTER TABLE `voie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKitlok6t6a7917bc3tweondq5b` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`);

--
-- Constraints for table `longueur`
--
ALTER TABLE `longueur`
  ADD CONSTRAINT `FK77tw6nat79sgeiekcti4r7chb` FOREIGN KEY (`cotation_min_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKcjeoxm79wvnm8estclhipbsjw` FOREIGN KEY (`cotation_max_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKlq7jctub72ex8wco2nq2xse0w` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKro1y7gu1g630s7j7vaiksn6s5` FOREIGN KEY (`voie_id`) REFERENCES `voie` (`id`);

--
-- Constraints for table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `FKfl8dasd6q488w7uvvq0vn26tb` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`),
  ADD CONSTRAINT `FKpjfiad695limf6qch2vfdp4h5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `spot`
--
ALTER TABLE `spot`
  ADD CONSTRAINT `FK6g2lud0tmk8rg7ja5khu3dvc6` FOREIGN KEY (`cotation_max_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKlv1s949apaw5uie6s9f80gfo6` FOREIGN KEY (`cotation_min_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKmq47ra92ib4lihvk99xmct1eb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `spot_photos`
--
ALTER TABLE `spot_photos`
  ADD CONSTRAINT `FK86lwvy68b1ouh8yidjxftak54` FOREIGN KEY (`photos_id`) REFERENCES `photo` (`id`),
  ADD CONSTRAINT `FKh1ykaomfp85csv7xe9v9dx92r` FOREIGN KEY (`Spot_id`) REFERENCES `spot` (`id`);

--
-- Constraints for table `topo`
--
ALTER TABLE `topo`
  ADD CONSTRAINT `FK2ttc8ak5arbnjirjrto21en71` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK6dqit1ih37xcgi7ndi6dtgst8` FOREIGN KEY (`cotation_min_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKcm7reglmm91ojv4e774oyv7gi` FOREIGN KEY (`cotation_max_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKki7p7wwl6yv7boebad42x924q` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`id`);

--
-- Constraints for table `topo_spots`
--
ALTER TABLE `topo_spots`
  ADD CONSTRAINT `FK353ekac7fa51onv5yr3sfvt19` FOREIGN KEY (`spots_id`) REFERENCES `spot` (`id`),
  ADD CONSTRAINT `FK7l9fek4330tfu31nfbe6jppbo` FOREIGN KEY (`topos_id`) REFERENCES `topo` (`id`);

--
-- Constraints for table `topo_user`
--
ALTER TABLE `topo_user`
  ADD CONSTRAINT `FK5votdbcj4862s36d2dwcs7xgk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKis4c98wg09xtlr6ptmvvt6e6o` FOREIGN KEY (`topo_id`) REFERENCES `topo` (`id`),
  ADD CONSTRAINT `FKt370woq36hdiuhh70nsmeg52h` FOREIGN KEY (`tenant_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `voie`
--
ALTER TABLE `voie`
  ADD CONSTRAINT `FK841h40xgpkfwgcedjm6xdohij` FOREIGN KEY (`cotation_min_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKe6tpgltdj5jicpccj2ye2u4ah` FOREIGN KEY (`cotation_max_id`) REFERENCES `cotation` (`id`),
  ADD CONSTRAINT `FKkln3kltxwiapc4av3l9mrdwib` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqlo061c6fgkuosv9chywpgn8t` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
