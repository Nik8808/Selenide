package ru.netology.service;

import java.time.format.DateTimeFormatter;

public class LocalDate {


    public String generateDate(long addDays, String pattern) {
        return java.time.LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    String planningDate = generateDate(4, "dd.mm.yyyy");
}
