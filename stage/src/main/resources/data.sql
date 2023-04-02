CREATE TABLE IF NOT EXISTS OFFRESTAGE(
    idOffre INT, 
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

/*CREATE TABLE Stage(
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
    );*/


 /*ALTER TABLE OffreStage
    ADD FOREIGN KEY (nomStage) 
    REFERENCES Stage(nomStage);

 ALTER TABLE OffreStage
    ADD FOREIGN KEY (nomOrganisation) 
    REFERENCES Organisation(nomOrganisation);*/

INSERT INTO OFFRESTAGE
VALUES
(1, 'Stage développement web', 'Informatique', 'ABC Solutions', 'Nous recherchons un stagiaire pour travailler sur le développement de notre site web.', '2022-01-01', 4, true, '2022-02-01', 6, 1000.00, true, 'contact@abcsolutions.com', 0123456789, 'https://www.abcsolutions.com/stage-developpement-web'),
(2, 'Stage marketing digital', 'Marketing', 'XYZ Marketing', 'Nous recherchons un stagiaire pour travailler sur des campagnes publicitaires en ligne.', '2022-02-15', 3, false, '2022-03-15', 3, 800.00, false, 'contact@xyzmarketing.com', 0987654321, 'https://www.xyzmarketing.com/stage-marketing-digital'),
(3, 'Stage communication', 'Communication', 'DEF Communication', 'Nous recherchons un stagiaire pour travailler sur la rédaction de communiqués de presse et la gestion des réseaux sociaux.', '2022-03-01', 2, true, '2022-04-01', 4, 700.00, true, 'contact@defcommunication.com', 0123456789, 'https://www.defcommunication.com/stage-communication');