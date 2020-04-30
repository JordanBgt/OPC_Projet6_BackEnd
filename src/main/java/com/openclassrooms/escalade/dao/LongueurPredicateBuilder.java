package com.openclassrooms.escalade.dao;

import com.openclassrooms.escalade.entities.QLongueur;
import com.openclassrooms.escalade.model.LongueurSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class LongueurPredicateBuilder {

    public static final QLongueur longueur = QLongueur.longueur;

    public static Predicate buildSearch(LongueurSearch longueurSearch) {
        return new BooleanBuilder()
                .and(isName(longueurSearch.getName()))
                .and(isCotationMin(longueurSearch.getCotationMinId()))
                .and(isCotationMax(longueurSearch.getCotationMaxId()))
                .and(isVoieId(longueurSearch.getVoieId()));
    }

    private static BooleanExpression isName(String name) {
        return name != null ? longueur.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isCotationMin(Long cotationMinId) {
        return cotationMinId != null ? longueur.cotationMin.id.goe(cotationMinId) : null;
    }

    private static BooleanExpression isCotationMax(Long cotationMaxId) {
        return cotationMaxId != null ? longueur.cotationMax.id.loe(cotationMaxId) : null;
    }

    private static BooleanExpression isVoieId(Long voieId) {
        return voieId != null ? longueur.voie.id.eq(voieId) : null;
    }
}
