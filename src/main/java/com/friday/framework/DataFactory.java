package com.friday.framework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Test data
 */

public class DataFactory {

    public static String getCurrentDate(String format) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }


}
