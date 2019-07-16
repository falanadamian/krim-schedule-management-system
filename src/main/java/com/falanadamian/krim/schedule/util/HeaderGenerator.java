package com.falanadamian.krim.schedule.util;

import org.springframework.http.HttpHeaders;

public final class HeaderGenerator {

    public static final String KRIM_SCHEDULE_MANAGEMENT_SYSTEM = "KRIM";

    private HeaderGenerator() {
    }

    private static HttpHeaders generateHeader(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.add(message, param);
        return headers;
    }

    public static HttpHeaders generateCreationHeader(String entityName, String param) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM,  entityName + " created: " +  param);
    }

    public static HttpHeaders generateSignUpHeader(String username) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM, "USER signed up: " + username);
    }

    public static HttpHeaders generateUpdateHeader(String entityName, String param) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM, entityName + " updated: " + param);
    }

    public static HttpHeaders generateDeletionHeader(String entityName, String param) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM, entityName + " deleted: " + param);
    }

    public static HttpHeaders generateScrapperHeader(String scrapper, String param) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM, scrapper + " created: " +param);
    }

    public static HttpHeaders generateUserActivationSuccessfulHeader(String username) {
        return generateHeader(KRIM_SCHEDULE_MANAGEMENT_SYSTEM, "USER activated: " + username);
    }
}
