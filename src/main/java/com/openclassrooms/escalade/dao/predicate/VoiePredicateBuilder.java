package com.openclassrooms.escalade.dao.predicate;

import com.openclassrooms.escalade.entities.QVoie;
import com.openclassrooms.escalade.model.VoieSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Class to build predicates for Voie search
 */
public class VoiePredicateBuilder {

    public static final QVoie voie = QVoie.voie;

    public static Predicate buildSearch(VoieSearch voieSearch) {
        return new BooleanBuilder()
                .and(isName(voieSearch.getName()))
                .and(isCotationMin(voieSearch.getCotationMinId()))
                .and(isCotationMax(voieSearch.getCotationMaxId()))
                .and(isSecteurId(voieSearch.getSecteurId()));
    }

    private static BooleanExpression isName(String name) {
        return name != null ? voie.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isCotationMin(Long cotationMinId) {
        return cotationMinId != null ? voie.cotationMin.id.goe(cotationMinId) : null;
    }

    private static BooleanExpression isCotationMax(Long cotationMaxId) {
        return cotationMaxId != null ? voie.cotationMax.id.loe(cotationMaxId) : null;
    }

    private static BooleanExpression isSecteurId(Long secteurId) {
        return secteurId != null ? voie.secteur.id.eq(secteurId) : null;
    }
}
