package com.openclassrooms.escalade.dao.predicate;

import com.openclassrooms.escalade.entity.QTopo;
import com.openclassrooms.escalade.model.TopoSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * Class to build predicates for Topo search
 */
public class TopoPredicateBuilder {

    public static final QTopo topo = QTopo.topo;

    public static Predicate buildSearch(TopoSearch topoSearch) {
        return new BooleanBuilder()
                .and(isCountry(topoSearch.getCountry()))
                .and(isName(topoSearch.getName()))
                .and(isCotationMin(topoSearch.getCotationMinId()))
                .and(isCotationMax(topoSearch.getCotationMaxId()));
    }

    private static BooleanExpression isCountry(String country) {
        return country != null ? topo.country.eq(country) : null;
    }

    private static BooleanExpression isName(String name) {
        return name != null ? topo.name.containsIgnoreCase(name) : null;
    }

    private static BooleanExpression isCotationMin(Long cotationMin){
        return cotationMin != null ? topo.cotationMin.id.goe(cotationMin) : null;
    }

    private static BooleanExpression isCotationMax(Long cotationMax){
        return cotationMax != null ? topo.cotationMax.id.loe(cotationMax) : null;
    }
}
