createuser -P chantier;
createdb chantierdb -O chantier;


DROP SCHEMA chantier CASCADE;

CREATE SCHEMA chantier;

CREATE TABLE chantier.clients (
  Client_id  SERIAL,
  Client_Name varchar(255),
  Client_old boolean NOT NULL default 'false',
  PRIMARY KEY  (Client_id)
);

CREATE TABLE chantier.coefficient (
  St_coef decimal(5,4)  NOT NULL,
  Inter_coef decimal(5,2)  NOT NULL
);


CREATE TABLE chantier.intervenants (
  Inter_id SERIAL,
  Inter_Name varchar(255) ,
  Inter_FirstName varchar(255) ,
  Inter_old boolean NOT NULL default 'false',
  Inter_ordre smallint NOT NULL default '99',
  PRIMARY KEY  (Inter_id)
);


CREATE TABLE chantier.sous_traitants (
  St_id SERIAL,
  St_Name varchar(255) ,
  St_old boolean NOT NULL default 'false',
  PRIMARY KEY  (St_id)
);


CREATE TABLE chantier.commandes (
  Command_id SERIAL,
  Command_devis decimal(9,2) NOT NULL,
  Command_date timestamp NOT NULL,
  Command_libelle varchar(255) ,
  Client_id Integer,
  Finalise boolean NOT NULL default 'false',
  PRIMARY KEY  (Command_id),
  CONSTRAINT fk_commandes_client
    FOREIGN KEY (Client_id )
    REFERENCES chantier.clients(Client_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


CREATE TABLE chantier.historique_heures (
  historique_heures_id SERIAL,
  Inter_id Integer,
  Command_id Integer,
  Historique_Heures decimal(5,2) NOT NULL,
  Historique_date timestamp NOT NULL,
  PRIMARY KEY (historique_heures_id),
  CONSTRAINT fk_intervenant_heures
    FOREIGN KEY (Inter_id )
    REFERENCES chantier.intervenants(Inter_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_commandes_heures
    FOREIGN KEY (Command_id)
    REFERENCES chantier.commandes(Command_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE chantier.historique_somme (
  historique_somme_id SERIAL,
  St_id Integer,
  Command_id Integer,
  Historique_Somme decimal(9,2) NOT NULL,
  Historique_date timestamp NOT NULL,
  PRIMARY KEY (historique_somme_id),
  CONSTRAINT fk_sous_traitants_somme
    FOREIGN KEY (St_id)
    REFERENCES chantier.sous_traitants(St_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_commandes_somme
    FOREIGN KEY (Command_id)
    REFERENCES chantier.commandes(Command_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);



