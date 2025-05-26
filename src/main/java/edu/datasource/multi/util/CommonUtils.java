package edu.datasource.multi.util;

public class CommonUtils {

    public static boolean isNotValidString(final String testString) {
        return (null == testString) ||
                (testString.trim().isEmpty());
    }
}
