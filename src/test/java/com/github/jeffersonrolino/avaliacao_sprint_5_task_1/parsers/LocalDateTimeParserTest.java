package com.github.jeffersonrolino.avaliacao_sprint_5_task_1.parsers;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeParserTest {

    String dataCriacao = "26/01/2019 00:01:01";

    @Test
    void shouldParseAndFormat() {
        //GIVEN
        LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat(dataCriacao);

        //THEN
        assertEquals(creationDate.getClass().getSimpleName(), LocalDateTime.class.getSimpleName());
    }

    @Test
    void shouldNotParseAndFormat(){
        //GIVEN
        LocalDateTime creationDate = LocalDateTimeParser.parseAndFormat(null);

        //THEN
        assertNull(creationDate);

    }

    @Test
    void shouldConvertLocalDateTimeToString() {
        //GIVEN
        LocalDateTime localDateTime = LocalDateTime.now();

        //WHEN
        String date = LocalDateTimeParser.toString(localDateTime);

        //THEN
        assertEquals(date.getClass().getSimpleName(), String.class.getSimpleName());
    }

    @Test
    void shouldNotConvertLocalDateTimeToString() {
        //GIVEN
        LocalDateTime localDateTime = null;

        //WHEN
        String date = LocalDateTimeParser.toString(localDateTime);

        //THEN
        assertNull(date);
    }
}