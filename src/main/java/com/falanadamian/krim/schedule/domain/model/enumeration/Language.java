package com.falanadamian.krim.schedule.domain.model.enumeration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Language {
    POLISH("Polski"),
    ENGLISH("Angielski");

    private String language;

    Language(String language){
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    private static final Map<String, Language> LANGUAGE_MAP = new HashMap<>();

    static {
        Arrays.stream(Language.values()).forEach(s -> LANGUAGE_MAP.put(s.getLanguage(), s));
    }

    public static Language of(String value) {
        return LANGUAGE_MAP.getOrDefault(value, POLISH);
    }
}
