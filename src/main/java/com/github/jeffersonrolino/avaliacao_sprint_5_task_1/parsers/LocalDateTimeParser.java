package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {
    public static LocalDateTime parseAndFormat(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(data, formatter);
    }
}
