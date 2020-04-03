package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.QSecteur;
import com.openclassrooms.escalade.model.SecteurSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;


public class SecteurPredicateBuilder {

    public static final QSecteur secteur = QSecteur.secteur;

    public static Predicate buildSearch(SecteurSearch secteurSearch) {
        return new BooleanBuilder()
                .and(isName(secteurSearch.getName()));
    }

    private static BooleanExpression isName(String name) {
        return name != null ? secteur.name.eq(name) : null;
    }

}
