CREATE DATABASE  IF NOT EXISTS `neon_assignment`;
USE `neon_assignment`;

--
-- Table structure for table `releasetracker`
--

DROP TABLE IF EXISTS `release_tracker`;

CREATE TABLE `release_tracker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `release_date` timestamp DEFAULT NULL,
  `created_at` timestamp DEFAULT NULL,
  `last_update_at` timestamp DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `valid_statuses`;
CREATE TABLE `valid_statuses` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
ALTER TABLE `release_tracker` 
ADD FOREIGN KEY (`status` ) REFERENCES `valid_statuses` (`id`);