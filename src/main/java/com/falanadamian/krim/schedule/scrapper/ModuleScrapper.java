package com.falanadamian.krim.schedule.scrapper;

import com.falanadamian.krim.schedule.domain.model.Classes;
import com.falanadamian.krim.schedule.domain.model.Module;
import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.security.SecurityHandler;
import com.falanadamian.krim.schedule.service.UserService;
import com.falanadamian.krim.schedule.service.UserSignatureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ModuleScrapper {

    private List<String> semesterDataTablesIds;
    private ModuleGeneralInformationScrapper moduleGeneralInformationScrapper;
    @Autowired
    private UserService userService;

    @Autowired
    private UserSignatureService userSignatureService;

    public ModuleScrapper() {
        moduleGeneralInformationScrapper = new ModuleGeneralInformationScrapper();

        semesterDataTablesIds = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            semesterDataTablesIds.add("semester-data-" + i);
        }
    }

    public List<SemesterData> scrapStudiesData(String url, SimpMessagingTemplate simpMessagingTemplate) {
        Document studyPlan = this.getStudyPlanByUrl(url);

        Elements semesterWrappers = this.getSemesterDataWrappersByClassName(studyPlan, "semester-wrapper");
        List<Element> semesterDataTables = new ArrayList<>();

        this.semesterDataTablesIds.forEach(id -> {
            semesterDataTables.add(this.getSemesterDataTableByID(studyPlan, id));
        });

        Map<String, Elements> semesterData = new HashMap<>();

        semesterDataTables.forEach(semesterDataTable -> {
            semesterData.put(semesterWrappers.get(semesterData.size()).getElementsByClass("semester-number").text(), this.getElementsByAtribute(semesterDataTable, "data-id"));
        });

        for (Map.Entry<String, Elements> entry : semesterData.entrySet()) {
            System.out.println(entry.getKey() + " :");
            System.out.println(entry.getValue());
            System.out.println("=======================================");
        }

        List<SemesterData> semesterDataList = new ArrayList<>();

        User currentlyLoggedInUser = userService.findOneByUsername(SecurityHandler.getCurrentUserUsername().get()).get();

        for (Map.Entry<String, Elements> entry : semesterData.entrySet()) {
            Module module = new Module();
            SemesterData semesterDataa = new SemesterData();
            entry.getValue().forEach(dataRow -> {
                Elements dataFields = dataRow.select("td");
                simpMessagingTemplate.convertAndSend("/chat", "Pobieram dla: " + dataFields.get(1).select("a").text());
                Module semesterDataObject = new Module()
                        .code(dataFields.get(0).text())
                        .name(dataFields.get(1).select("a").text())
                        .url(dataFields.get(1).select("a").attr("abs:href"))
                        .classes(new Classes()
                                .lecture(parseString(dataFields.get(2).text()))
                                .blackboard(parseString(dataFields.get(3).text()))
                                .laboratory(parseString(dataFields.get(4).text()))
                                .project(parseString(dataFields.get(5).text()))
//                                .convensatory(parseString(dataFields.get(6).text()))
                                .seminar(parseString(dataFields.get(7).text()))
                                .practical(parseString(dataFields.get(8).text()))
                                .outdoor(parseString(dataFields.get(9).text()))
                                .workshop(parseString(dataFields.get(10).text()))
                                .other(parseString(dataFields.get(11).text()))
                                .elearning(parseString(dataFields.get(12).text()))
                                .sum(parseString(dataFields.get(13).text())))
                        .ECTS(parseString(dataFields.get(14).text()))
                        .exam(examParser(dataFields.get(15).text()))
                        .user(currentlyLoggedInUser);
                ModuleGeneralInformation moduleGeneralInformation = moduleGeneralInformationScrapper.scrapModuleGeneralInformationData(semesterDataObject.getUrl());
                semesterDataObject.moduleGeneralInformation(moduleGeneralInformation);
                semesterDataa.getModules().add(semesterDataObject);
            });
            semesterDataList.add(semesterDataa);
        }
        return semesterDataList;
    }

    private Integer parseString(String string) {
        return Integer.parseInt(string.replaceAll("\\s", ""));
    }

    private Boolean examParser(String string) {
        return string.equals("+");
    }

    private Document getStudyPlanByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Element getSemesterDataTableByID(Document document, String id) {
        Element semesterDataTable = document.getElementById(id);
        return semesterDataTable;
    }

    private Elements getSemesterDataWrappersByClassName(Document document, String className) {
        Elements semesterDataWrappers = document.getElementsByClass(className);
        return semesterDataWrappers;
    }

    private Elements getElementsByAtribute(Element element, String attribute) {
        Elements dataRows = element.getElementsByAttribute(attribute);
        return dataRows;
    }
}
