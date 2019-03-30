-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 20 mars 2019 à 18:19
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pi`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone` int(11) NOT NULL,
  `typeCompte` varchar(255) NOT NULL,
  `sexe` varchar(255) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `naissance` date NOT NULL,
  `interets` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `piece_id` varchar(255) DEFAULT NULL,
  `domaine` varchar(255) DEFAULT NULL,
  `sous_domaine` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `cover` varchar(255) NOT NULL DEFAULT 'default_Pcover.png',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `password`, `telephone`, `typeCompte`, `sexe`, `avatar`, `naissance`, `interets`, `token`, `piece_id`, `domaine`, `sous_domaine`, `bio`, `cover`) VALUES
(5, 'hahg', 'gaga', 'ga', 'ga', 121, 'Amateur d\'art', 'homme', 'Avatar8.png', '2019-03-14', 'Cinema', 'active', NULL, NULL, NULL, NULL, 'wallpaper1.png'),
(6, 'gaag', 'gaga', 'gagag', 'gagagafa', 421, 'Artiste', 'femme', 'avatar', '2019-03-09', 'Cinema,Theatre,Peintre', '', '', 'Cinema', 'Producteur', 'gagagagag', 'default_Pcover.png');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
