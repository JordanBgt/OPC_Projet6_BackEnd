package com.openclassrooms.escalade.dao.predicate;

import com.openclassrooms.escalade.entities.QSecteur;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Class to build predicates for Secteur search
 */
public class SecteurPredicateBuilder {

    public static final QSecteur secteur = QSecteur.secteur;

    public static Predicate buildSearch(SecteurSearch secteurSearch) {
        return new BooleanBuilder()
                .and(isName(secteurSearch.getName()))
                .and(isSpotId(secteurSearch.getSpotID()));
    }

    private static BooleanExpression isName(String name) {
        return name != null ? secteur.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isSpotId(Long spotId) {
        return spotId != null ? secteur.spot.id.eq(spotId) : null;
    }

}
