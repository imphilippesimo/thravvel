create database tp4; 

use tp4;

create table Employe(
    id integer not null auto_increment ,
	nom varchar(20),
	prenom varchar(20),
	adresse varchar(30),
	primary key (id) 
	
);

create table Vente(
    id integer not null auto_increment ,
	num_vente varchar(20),
	primary key (id) 
	
);

alter table Vente 
  add foreign key fk_vente_employe (id_employe)
      references Employe (id) ;

