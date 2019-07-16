package com.falanadamian.krim.schedule.domain.model.enumeration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum StudyType {
    FULL_TIME("Stacjonarne"),
    PART_TIME("Niestacjonarne");

    private String studyType;

    StudyType(String studyType){
        this.studyType = studyType;
    }

    public String getStudyType() {
        return studyType;
    }

    private static final Map<String, StudyType> STUDY_TYPE_MAP = new HashMap<>();

    static {
        Arrays.stream(StudyType.values()).forEach(s -> STUDY_TYPE_MAP.put(s.getStudyType(), s));
    }

    public static StudyType of(String value) {
        return STUDY_TYPE_MAP.getOrDefault(value, FULL_TIME);
    }
}
