##INSERTION TOPO##
INSERT INTO escalade.topo(id, available, cotation, country, description, duration, name, publication_date, region, topoCreator_id)
VALUES (
           1,
           true,
           '6a - 8a',
           'France',
           'L\'ouvrage présente les voies d\'escalade des Gorges de la Jonte, situées sur les grandes parois des communes du Rozier et de Saint-Pierre-des-Tripiers.',
           '4h',
           'Les gorges de la Jonte',
           CURRENT_DATE,
           'Lozère',
           1
       );
INSERT INTO escalade.topo(id, available, cotation, country, description, duration, name, publication_date, region, topoCreator_id)
VALUES (
           2,
           true,
           '4a - 7b+',
           'France',
           'La Grande Tête de l''Obiou, ou plus communément l''Obiou, est un sommet situé dans les Préalpes françaises, à peu près à égale distance de Grenoble (Isère) et de Gap (Hautes-Alpes). Avec ses 2 789 mètres d''altitude, c''est le point culminant du massif du Dévoluy',
           '4h',
           'Guide de l\'Obiou',
           CURRENT_DATE,
           'Isère',
           2
       );
INSERT INTO escalade.topo(id, available, cotation, country, description, duration, name, publication_date, region, topoCreator_id)
VALUES (
           3,
           true,
           '3c - 6a',
           'France',
           'Ce topo regroupe les 6 sites de grimpe de Mayenne : Changé, Entrammes, Moulay; St-Berthevin, Saulges et Toyères.',
           '4h à 6h',
           'Escalade en Mayenne',
           CURRENT_DATE,
           'Pays de la Loire',
           1
       );

##INSERTION SPOT##
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           1,
           'Jonte',
           'France',
           'Encore un des beaux sites de la Jonte, à l''équipement plutôt proche au regard des grandes classiques du site.',
           true,
           1,
           1,
           'La Jonte - Cirque des vases : Biotone',
           'TD- 6a+ I P1'
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           2,
           'Jonte',
           'France',
           'Site équipé par Médéric Tabart en 1987.',
           true,
           1,
           1,
           'La Jonte - Cirque des vases : Plume',
           'TD- 6a+>5c I P1'
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           3,
           'Jonte',
           'France',
           'Itinéraire très esthétique, qui se déroule sur le fil de l''arête. Rocher magnifique dans ce registre de difficulté, un vrai régal',
           true,
           1,
           1,
           'La Jonte - Cirque des vases : L\'arête',
           'D- 6a+>4c A0 I P1'
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           4,
           'Dévoluy',
           'France',
           'Le site se situe sur la facette E sommitale de l''Obiou. L''approche est conseillée par la voie Paul Arthaud et les variantes de l''arête bordant le couloir Paul Arthaud en rive droite. On peut également réaliser cette voie depuis l''accès par la voie normale. Enfin, C''est aussi une manière élégante de rejoindre le sommet après l''ascension du pilier NW.
            L''attaque de la voie Marie-Thérèse est située 20 m à droite d''une petite tour (10 m de haut) au pied la facette sommitale E de l''Obiou. Cette tour est aussi appelée le Campanile. La voie attaque dans un mur compact stratifié 50 m à gauche d''un grand dièdre caractéristique rayant la facette E de haut en bas.',
           false,
           2,
           2,
           'Obiou : Site Marie-Thérèse',
           'TD- 6a+ I P1'
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           5,
           'Dévoluy',
           'France',
           'Du chalet des Baumes (Parking sur la route du col des Faïsses, emprunter la voie normale de l''Obiou par un bon sentier, passer le pas du vallon, puis laisser le sentier à droite, et remonter le Pré du Chourum par des pentes d''herbe très raides (quelques petits rochers délités) orientées N, qui donnent accès à une arête et la face E du Petit Obiou. Dans un terrain de strates raides, longer la base des falaises vers le S jusqu''à trouver des marques bleues. Remonter alors le couloir cheminée, parfois par ses rives, parfois au fond. Au-dessus d''un passage de III on trouve un clou pour assurer un second si nécessaire. Sortir à une antécime à l''W du sommet.
            Cet itinéraire contourne le Petit Obiou. La traversée du Petit Obiou est plus compliquée et nécessite un rappel.',
           false,
           2,
           2,
           'Obiou : Par le Petit Obiou et les Feuillets',
           'PD 3c>3c '
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           6,
           'Moulay',
           'France',
           'Entre 25 et 50 voies réparties sur plusieurs falaises. Equipement sportif. Rocher : granite.',
           true,
           3,
           1,
           'Site de Moulay',
           '3c au 6a '
       );
INSERT INTO escalade.spot(id, city, country, description, isOfficial, topo_id, user_id, name, cotation)
VALUES (
           7,
           'Changé',
           'France',
           '10 à 15 voies. Equipement sportif. Rocher : calcaire',
           true,
           3,
           1,
           'Site de Moulay',
           '3b au 4a '
       );

##INSERTION SECTEUR##
INSERT INTO escalade.secteur(id, name, spot_id)
VALUES (
           1,
           'Site Moulay : secteur 1',
           6
       );
INSERT INTO escalade.secteur(id, name, spot_id)
VALUES (
           2,
           'Site Moulay : secteur 2',
           6
       );
INSERT INTO escalade.secteur(id, name, spot_id)
VALUES (
           3,
           'Secteur du site de Changé',
           6
       );

##INSERTION VOIES##
INSERT INTO escalade.voie(id, cotation, name, secteur_id)
VALUES (
           1,
           '3c',
           'Voie de la marmite',
           1
       );
INSERT INTO escalade.voie(id, cotation, name, secteur_id)
VALUES (
           2,
           '5a',
           'Voie de java',
           1
       );

##INSERTION LONGUEUR##
INSERT INTO escalade.longueur(id, cotation, name, voie_id)
VALUES (
           1,
           '3c',
           'Voie de la marmite : longueur 1',
           1
       );
