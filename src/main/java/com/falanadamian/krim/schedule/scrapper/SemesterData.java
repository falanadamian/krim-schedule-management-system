package com.falanadamian.krim.schedule.scrapper;

import com.falanadamian.krim.schedule.domain.model.Module;
import java.util.ArrayList;
import java.util.List;

public class SemesterData {

    private List<Module> modules = new ArrayList<>();

    private List<SemesterDataRow> semesterDataRows= new ArrayList<>();

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<SemesterDataRow> getSemesterDataRows() {
        return semesterDataRows;
    }

    public void setSemesterDataRows(List<SemesterDataRow> semesterDataRows) {
        this.semesterDataRows = semesterDataRows;
    }
}
