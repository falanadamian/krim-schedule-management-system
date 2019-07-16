package com.falanadamian.krim.schedule.scrapper;


import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.UserSignature;
import com.falanadamian.krim.schedule.domain.model.enumeration.Degree;
import com.falanadamian.krim.schedule.domain.model.enumeration.Language;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyLevel;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleGeneralInformationScrapper {

    ModuleGeneralInformationScrapper() {
    }

    public ModuleGeneralInformation scrapModuleGeneralInformationData(String url) {
        Document moduleGeneralInformation = this.getModuleGeneralInformationByUrl(url);

        Elements generalInformationForm = this.getGeneralInformationFormByClassName(moduleGeneralInformation, "education-effect-form");
        List<Element> infoGroupRows = new ArrayList<>();

        Elements infoGroupRowElements = generalInformationForm.select("div.info-group-row");

        for (int i = 0; i < infoGroupRowElements.size(); i++) {
            infoGroupRows.add(infoGroupRowElements.get(i));
        }

        Elements contentElements0 = infoGroupRows.get(0).select("div.content");
        Elements contentElements1 = infoGroupRows.get(1).select("div.content");
        Elements contentElements2 = infoGroupRows.get(2).select("div.content");
        Elements contentElements3 = infoGroupRows.get(3).select("div.content");
        Elements contentElements4 = infoGroupRows.get(4).select("div.content");
        Elements contentElements5 = infoGroupRows.get(5).select("div.content");
        Elements contentElements6 = infoGroupRows.get(6).select("div.content");

        ModuleGeneralInformation moduleGeneralInformation1 = new ModuleGeneralInformation();
        moduleGeneralInformation1.name(contentElements0.get(0).text()).studyCourse(contentElements0.get(1).text()).code(contentElements0.get(2).text());
        moduleGeneralInformation1.faculty(contentElements1.get(0).text()).studyLevel(StudyLevel.of(contentElements1.get(1).text()));
        moduleGeneralInformation1.specialty(contentElements2.get(0).text()).studyField(contentElements2.get(1).text());
        moduleGeneralInformation1.semester(parseString(contentElements3.get(0).text())).educationProfile(contentElements3.get(1).text()).lectureLanguage(Language.of(contentElements3.get(2).text()));
        moduleGeneralInformation1.studyType(StudyType.of(contentElements4.get(0).text()));
        if(!this.extractUserSignatures(contentElements5.get(0)).isEmpty())
            moduleGeneralInformation1.responsibleUserSignature(this.extractUserSignatures(contentElements5.get(0)).get(0));
        if(!this.extractUserSignatures(contentElements6.get(0)).isEmpty())
            moduleGeneralInformation1.academicUserSignatures(this.extractUserSignatures(contentElements6.get(0)));

        Elements responsibleTeacherEmailLink = contentElements5.get(0).select("a");
        String responsibleTeacherEmail = responsibleTeacherEmailLink.attr("abs:href").replaceAll("mailto:", "");

        Elements academicTeachersEmailLinks = contentElements6.get(0).select("a");
        List<String> academicTeachersEmails = new ArrayList<>();

        academicTeachersEmailLinks.forEach(academicTeachersEmailLink -> {
            academicTeachersEmails.add(academicTeachersEmailLink.attr("abs:href").replaceAll("mailto:", ""));
        });

        System.out.println("x");
        return moduleGeneralInformation1;
    }

    private Integer parseString(String string) {
        return Integer.parseInt(string.replaceAll("\\s", ""));
    }

    private Document getModuleGeneralInformationByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Element getGeneralInformationFormByClass(Document document, String formClass) {
        Element semesterDataTable = document.getElementById(formClass);
        return semesterDataTable;
    }

    private Elements getGeneralInformationFormByClassName(Document document, String className) {
        Elements generalInformationForm = document.getElementsByClass(className);
        return generalInformationForm;
    }

    private Elements getElementsByAtribute(Element element, String attribute) {
        Elements dataRows = element.getElementsByAttribute(attribute);
        return dataRows;
    }

    private List<UserSignature> extractUserSignatures(Element element) {
        List<Node> nodes = element.childNodes().stream().filter( node -> {
            if (node instanceof Element) {
                Element localElement = (Element) node;
                if(localElement.tagName().equals("br"))
                    return false;
                return true;
            }
            if (node instanceof TextNode) {
                TextNode textNode = (TextNode) node;
                String userDetails = textNode.text();
                if(StringUtils.isNotBlank(userDetails) && !userDetails.equals("br")) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        List<UserSignature> userSignatures = new ArrayList<>();
        for(int i = 0; i < nodes.size(); i+=2){
            UserSignature userSignature = new UserSignature();
            String[] userDetails = ((TextNode) nodes.get(i)).text().split("\\s(?=[A-Z])");
            if(userDetails.length == 2) {
                userSignature.setDegree(Degree.BRAK);
                userSignature.setLastName(userDetails[0].trim());
                userSignature.setFirstName(userDetails[1].trim());
                userSignature.setEmail(((Element) nodes.get(i + 1)).select("a").text());
                userSignatures.add(userSignature);
            }
            if(userDetails.length == 3) {
                userSignature.setDegree(Degree.of(userDetails[0].trim()));
                userSignature.setLastName(userDetails[1].trim());
                userSignature.setFirstName(userDetails[2].trim());
                userSignature.setEmail(((Element) nodes.get(i + 1)).select("a").text());
                userSignatures.add(userSignature);
            }
        }
        return userSignatures;
    }

    private String removeWhitespaces(String string) {
        return string.replaceAll("\\s","");
    }

    private String[] removeWhitespaces(String[] strings) {
        return Arrays.stream(strings).map(this::removeWhitespaces).toArray(String[]::new);
    }
}
