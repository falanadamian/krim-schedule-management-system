package com.falanadamian.krim.schedule.domain.model.enumeration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum StudyLevel {
    FIRST_CYCLE("Studia I stopnia"),
    SECOND_CYCLE("Studia II stopnia"),
    THIRD_CYCLE("Studia III stopnia");

    private String studyLevel;

    StudyLevel(String studyLevel){
        this.studyLevel = studyLevel;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    private static final Map<String, StudyLevel> STUDY_LEVEL_MAP = new HashMap<>();

    static {
        Arrays.stream(StudyLevel.values()).forEach(s -> STUDY_LEVEL_MAP.put(s.getStudyLevel(), s));
    }

    public static StudyLevel of(String value) {
        return STUDY_LEVEL_MAP.getOrDefault(value, FIRST_CYCLE);
    }
}
