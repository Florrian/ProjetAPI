CREATE TABLE OffreStage(
    idOffre INT PRIMARY KEY, 
    nomStage VARCHAR(255), 
    domaine VARCHAR(255), 
    nomOrganisation VARCHAR(255), 
    descriptionStage VARCHAR(255), 
    datePubOffre DATE, 
    niveauEtudeStage INT, 
    expRequiseStage BOOLEAN, 
    dateDebStage DATE, 
    dureeStage INT, 
    salaireStage FLOAT, 
    indemnisation BOOLEAN, 
    email VARCHAR(255), 
    telephone INT, 
    url VARCHAR(255)
    );

CREATE TABLE Stage(
    nomStage VARCHAR(255) PRIMARY KEY,
    adressePaysS VARCHAR(255), 
    adresseVilleS VARCHAR(255), 
    codePostalS INT, 
    adresseRueS VARCHAR(255), 
    latitudeS INT, 
    longitudeS INT
    );

CREATE TABLE Organisation(
    nomOrganisation VARCHAR(255) PRIMARY KEY,
    adressePaysO VARCHAR(255), 
    adresseVilleO VARCHAR(255), 
    codePostalO INT, 
    adresseRueO VARCHAR(255)
    );

CREATE TABLE Utilisateur(
    idUser INT PRIMARY KEY, 
    nomUser VARCHAR(255), 
    adresseU VARCHAR(255), 
    NiveauEtude INT, 
    adresseMail VARCHAR(255), 
    tel INT, 
    domaine VARCHAR(255)
    );


 ALTER TABLE OffreStage
    ADD FOREIGN KEY (nomStage) 
    REFERENCES Stage(nomStage);

 ALTER TABLE OffreStage
    ADD FOREIGN KEY (nomOrganisation) 
    REFERENCES Organisation(nomOrganisation);
