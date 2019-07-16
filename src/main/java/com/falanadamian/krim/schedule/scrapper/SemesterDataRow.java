package com.falanadamian.krim.schedule.scrapper;

import com.falanadamian.krim.schedule.domain.model.Classes;

public class SemesterDataRow {

    private String code;
    private String name;
    private String url;
    private Classes classes;
    private Integer ECTS;
    private Boolean exam;

    public SemesterDataRow code(final String code) {
        this.code = code;
        return this;
    }

    public SemesterDataRow name(final String name) {
        this.name = name;
        return this;
    }

    public SemesterDataRow url(final String url) {
        this.url = url;
        return this;
    }

    public SemesterDataRow semesterHours(final Classes classes) {
        this.classes = classes;
        return this;
    }

    public SemesterDataRow ECTS(final Integer ECTS) {
        this.ECTS = ECTS;
        return this;
    }

    public SemesterDataRow exam(final Boolean exam) {
        this.exam = exam;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Integer getECTS() {
        return ECTS;
    }

    public void setECTS(Integer ECTS) {
        this.ECTS = ECTS;
    }

    public Boolean getExam() {
        return exam;
    }

    public void setExam(Boolean exam) {
        this.exam = exam;
    }
}
