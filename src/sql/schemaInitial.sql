-- drop schema chantier;
create schema chantier;
use chantier;
--
-- structure de la table `clients`
--

create table `clients` (
  `client_id` mediumint(8) unsigned not null auto_increment,
  `client_name` varchar(255) collate utf8_bin not null,
  `client_old` tinyint(4) not null default '0',
  primary key  (`client_id`)
) engine=innodb  default charset=utf8 collate=utf8_bin auto_increment=135 ;


--
-- structure de la table `coefficient`
--

create table `coefficient` (
  `st_coef` decimal(5,4) unsigned not null,
  `inter_coef` decimal(5,2) unsigned not null
) engine=innodb default charset=utf8 collate=utf8_bin;


--
-- structure de la table `commandes`
--

create table `commandes` (
  `command_id` mediumint(8) unsigned not null auto_increment,
  `command_devis` decimal(9,2) not null,
  `command_date` datetime not null,
  `command_libelle` varchar(255) collate utf8_bin not null,
  `client_id` mediumint(8) unsigned not null,
  `finalise` tinyint(4) not null default '0',
  primary key  (`command_id`),
  key `client_id` (`client_id`)
) engine=innodb  default charset=utf8 collate=utf8_bin auto_increment=482 ;

--
-- structure de la table `historique_heures`
--

create table `historique_heures` (
  `inter_id` mediumint(8) unsigned not null,
  `command_id` mediumint(8) unsigned not null,
  `historique_heures` decimal(5,2) unsigned not null,
  `historique_date` datetime not null,
  key `inter_id` (`inter_id`),
  key `command_id` (`command_id`)
) engine=innodb default charset=utf8 collate=utf8_bin;

-- --------------------------------------------------------

--
-- structure de la table `historique_somme`
--

create table `historique_somme` (
  `st_id` mediumint(8) unsigned not null,
  `command_id` mediumint(8) unsigned not null,
  `historique_somme` decimal(9,2) unsigned not null,
  `historique_date` datetime not null,
  key `command_id` (`command_id`),
  key `st_id` (`st_id`)
) engine=innodb default charset=utf8 collate=utf8_bin;

--
-- contenu de la table `historique_somme`
--

-- --------------------------------------------------------

--
-- structure de la table `intervenants`
--

create table `intervenants` (
  `inter_id` mediumint(8) unsigned not null auto_increment,
  `inter_name` varchar(255) collate utf8_bin not null,
  `inter_firstname` varchar(255) collate utf8_bin not null,
  `inter_old` tinyint(4) not null default '0',
  `inter_ordre` tinyint(4) not null default '99',
  primary key  (`inter_id`)
) engine=innodb  default charset=utf8 collate=utf8_bin auto_increment=11 ;

--
-- structure de la table `sous_traitants`
--

create table `sous_traitants` (
  `st_id` mediumint(8) unsigned not null auto_increment,
  `st_name` varchar(255) collate utf8_bin not null,
  `st_old` tinyint(4) not null default '0',
  primary key  (`st_id`)
) engine=innodb  default charset=utf8 collate=utf8_bin auto_increment=81 ;
