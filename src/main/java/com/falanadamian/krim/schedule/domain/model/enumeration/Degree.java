package com.falanadamian.krim.schedule.domain.model.enumeration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Degree {
    PROFESOR("dr hab, prof."),
    DOKTOR_HABILITOWANY("dr hab. inż."),
    DOKTOR("dr"),
    MAGISTER("mgr"),
    DOKTORANT("DOKTORANT"),
    DOKTORANT_ZE_STYPENDIUM("DOKTORANT_ZE_STYPENDIUM"),
    DOKTORANT_WDROZENIOWY("DOKTORANT_WDROZENIOWY"),
    EKSPERT_DOKTOR("EKSPERT_DOKTOR"),
    EKSPERT_DOKTOR_HABILITOWANY("EKSPERT_DOKTOR_HABILITOWANY"),
    EKSPERT_PROFESOR("EKSPERT_PROFESOR"),
    MAGISTER_INZYNIER("mgr inż."),
    DOKTOR_INZYNIER("dr inż."),
    DOKTOR_HABILITOWANY_INZYNIER("dr hab. inż."),
    PROFESOR_DOKTOR_HABILITOWANY_INZYNIER("prof. dr hab. inż."),
    PROFESOR_NADZWYCZAJNY_DOKTOR_HABILITOWANY_INZYNIER("prof. nadzw. dr hab. inż."),
    BRAK("");


    private String degree;

    Degree(String degree){
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    private static final Map<String, Degree> DEGREE_MAP = new HashMap<>();

    static {
        Arrays.stream(Degree.values()).forEach(s -> DEGREE_MAP.put(s.getDegree(), s));
    }

    public static Degree of(String value) {
        return DEGREE_MAP.getOrDefault(value, BRAK);
    }
}
