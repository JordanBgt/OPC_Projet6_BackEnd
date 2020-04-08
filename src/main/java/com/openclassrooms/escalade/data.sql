##INSERTION PHOTO##
INSERT INTO escalade.photo(id, name)
VALUES
(1, "gorges-jonte.jpg"),
(2, "guide-obiou.jpg"),
(3, "escalade-mayenne.jpg");

##INSERTION COTATION##
INSERT INTO escalade.cotation(id, label)
VALUES
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

##INSERTION TOPO##
INSERT INTO escalade.topo(id, available, cotation_min_id, cotation_max_id, country, description, name, publication_date, region, topoCreator_id, photo_id)
VALUES (
           1,
           true,
           9,
           21,
           'France',
           'L\'ouvrage présente les voies d\'escalade des Gorges de la Jonte, situées sur les grandes parois des communes du Rozier et de Saint-Pierre-des-Tripiers.',
           'Les gorges de la Jonte',
           CURRENT_DATE,
           'Lozère',
           1,
           1
       );
INSERT INTO escalade.topo(id, available, cotation_min_id, cotation_max_id, country, description, name, publication_date, region, topoCreator_id, photo_id)
VALUES (
           2,
           true,
           3,
           18,
           'France',
           'La Grande Tête de l''Obiou, ou plus communément l''Obiou, est un sommet situé dans les Préalpes françaises, à peu près à égale distance de Grenoble (Isère) et de Gap (Hautes-Alpes). Avec ses 2 789 mètres d''altitude, c''est le point culminant du massif du Dévoluy',
           'Guide de l\'Obiou',
           CURRENT_DATE,
           'Isère',
           2,
           2
       );
INSERT INTO escalade.topo(id, available, cotation_min_id, cotation_max_id, country, description, name, publication_date, region, topoCreator_id, photo_id)
VALUES (
           3,
           true,
           2,
           9,
           'France',
           'Ce topo regroupe les 6 sites de grimpe de Mayenne : Changé, Entrammes, Moulay; St-Berthevin, Saulges et Toyères.',
           'Escalade en Mayenne',
           CURRENT_DATE,
           'Pays de la Loire',
           1,
           3
       );

##INSERTION SPOT##
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           1,
           'Jonte',
           'France',
           'Encore un des beaux sites de la Jonte, à l''équipement plutôt proche au regard des grandes classiques du site.',
           true,
           1,
           'La Jonte - Cirque des vases : Biotone',
           10,
           20
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           2,
           'Jonte',
           'France',
           'Site équipé par Médéric Tabart en 1987.',
           true,
           1,
           'La Jonte - Cirque des vases : Plume',
           8,
           10
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           3,
           'Jonte',
           'France',
           'Itinéraire très esthétique, qui se déroule sur le fil de l''arête. Rocher magnifique dans ce registre de difficulté, un vrai régal',
           true,
           1,
           'La Jonte - Cirque des vases : L\'arête',
           5,
           10
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           4,
           'Dévoluy',
           'France',
           'Le site se situe sur la facette E sommitale de l''Obiou. L''approche est conseillée par la voie Paul Arthaud et les variantes de l''arête bordant le couloir Paul Arthaud en rive droite. On peut également réaliser cette voie depuis l''accès par la voie normale. Enfin, C''est aussi une manière élégante de rejoindre le sommet après l''ascension du pilier NW.
            L''attaque de la voie Marie-Thérèse est située 20 m à droite d''une petite tour (10 m de haut) au pied la facette sommitale E de l''Obiou. Cette tour est aussi appelée le Campanile. La voie attaque dans un mur compact stratifié 50 m à gauche d''un grand dièdre caractéristique rayant la facette E de haut en bas.',
           false,
           2,
           'Obiou : Site Marie-Thérèse',
           5,
           10
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           5,
           'Dévoluy',
           'France',
           'Du chalet des Baumes (Parking sur la route du col des Faïsses, emprunter la voie normale de l''Obiou par un bon sentier, passer le pas du vallon, puis laisser le sentier à droite, et remonter le Pré du Chourum par des pentes d''herbe très raides (quelques petits rochers délités) orientées N, qui donnent accès à une arête et la face E du Petit Obiou. Dans un terrain de strates raides, longer la base des falaises vers le S jusqu''à trouver des marques bleues. Remonter alors le couloir cheminée, parfois par ses rives, parfois au fond. Au-dessus d''un passage de III on trouve un clou pour assurer un second si nécessaire. Sortir à une antécime à l''W du sommet.
            Cet itinéraire contourne le Petit Obiou. La traversée du Petit Obiou est plus compliquée et nécessite un rappel.',
           false,
           2,
           'Obiou : Par le Petit Obiou et les Feuillets',
           2,
           2
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           6,
           'Moulay',
           'France',
           'Entre 25 et 50 voies réparties sur plusieurs falaises. Equipement sportif. Rocher : granite.',
           true,
           1,
           'Site de Moulay',
           2,
           10
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, user_id, name, cotation_min_id, cotation_max_id)
VALUES (
           7,
           'Changé',
           'France',
           '10 à 15 voies. Equipement sportif. Rocher : calcaire',
           true,
           1,
           'Site de Moulay',
           2,
           3
       );

##INSERTION TOPO_SPOTS##
INSERT INTO escalade.topo_spots(topos_id, spots_Id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7);

##INSERTION SECTEUR##
INSERT INTO escalade.secteur(id, name)
VALUES (
           1,
           'Site Moulay : secteur 1'
       );
INSERT INTO escalade.secteur(id, name)
VALUES (
           2,
           'Site Moulay : secteur 2'
       );
INSERT INTO escalade.secteur(id, name)
VALUES (
           3,
           'Secteur du site de Changé'
       );

##INSERTION VOIES##
INSERT INTO escalade.voie(id, cotation_min_id, cotation_max_id, name, secteur_id)
VALUES (
           1,
           2,
           2,
           'Voie de la marmite',
           1
       );
INSERT INTO escalade.voie(id, cotation_min_id, cotation_max_id, name, secteur_id)
VALUES (
           2,
           6,
           6,
           'Voie de java',
           1
       );

##INSERTION LONGUEUR##
INSERT INTO escalade.longueur(id, cotation_min_id, cotation_max_id, name, voie_id)
VALUES (
           1,
           2,
           3,
           'Voie de la marmite : longueur 1',
           1
       );

##INSERTION SPOT_SECTEURS##
INSERT INTO escalade.spot_secteurs(Spot_id, secteurs_id)
VALUES
(6, 1),
(6, 2),
(6, 3);
