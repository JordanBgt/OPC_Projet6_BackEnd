package com.openclassrooms.escalade.dao.predicate;

import com.openclassrooms.escalade.entity.QSpot;
import com.openclassrooms.escalade.model.SpotSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Class to build predicates for Spot search
 */
public class SpotPredicateBuilder {

    public static final QSpot spot = QSpot.spot;

    public static Predicate buildSearch(SpotSearch spotSearch) {
        return new BooleanBuilder()
                .and(isName(spotSearch.getName()))
                .and(isCountry(spotSearch.getCountry()))
                .and(isCity(spotSearch.getCity()))
                .and(isOfficial(spotSearch.isOfficial()))
                .and(isCotationMin(spotSearch.getCotationMinId()))
                .and(isCotationMax(spotSearch.getCotationMaxId()));
    }

    private static BooleanExpression isName(String name) {
        return name != null ? spot.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isCountry(String country) {
        return country != null ? spot.country.eq(country) : null;
    }

    private static BooleanExpression isCity(String city) {
        return city != null ? spot.city.eq(city) : null;
    }

    private static BooleanExpression isOfficial(boolean isOfficial) {
        return isOfficial ? spot.isOfficial.eq(true) : null;
    }

    private static BooleanExpression isCotationMin(Long cotationMinId) {
        return cotationMinId != null ? spot.cotationMin.id.goe(cotationMinId) : null;
    }

    private static BooleanExpression isCotationMax(Long cotationMaxId) {
        return cotationMaxId != null ? spot.cotationMax.id.loe(cotationMaxId) : null;
    }
}
