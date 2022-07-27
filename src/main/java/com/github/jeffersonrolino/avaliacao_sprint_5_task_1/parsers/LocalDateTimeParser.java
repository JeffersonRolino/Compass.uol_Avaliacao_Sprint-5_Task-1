package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static LocalDateTime parseAndFormat(String data){
        return LocalDateTime.parse(data, formatter);
    }

    public static String toString(LocalDateTime localDateTime){
        return localDateTime.format(formatter);
    }
}
