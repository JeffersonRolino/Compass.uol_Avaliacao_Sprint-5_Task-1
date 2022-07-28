package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static LocalDateTime parseAndFormat(String data) {
        if(data != null){
            return LocalDateTime.parse(data, formatter);
        }
        return null;
    }

    public static String toString(LocalDateTime localDateTime){
        if(localDateTime != null){
            return localDateTime.format(formatter);
        }
        return null;
    }
}
