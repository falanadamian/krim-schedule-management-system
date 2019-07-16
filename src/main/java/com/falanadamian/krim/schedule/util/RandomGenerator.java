package com.falanadamian.krim.schedule.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomGenerator {

    private static final Integer RANDOM_COUNT = 10;

    private RandomGenerator() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(RANDOM_COUNT);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(RANDOM_COUNT);
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(RANDOM_COUNT);
    }

}
