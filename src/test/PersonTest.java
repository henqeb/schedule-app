package test;
import source.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonTest {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    Person person;

    @BeforeEach
    void init() {
        person = new Person("Bobby Brown", "bobbyb@bmail.bom");
    }

    @Test
    void whenGetPersonsSchedule_thenShouldNotBeEqual() {
        // add schedule to Person
        List<Meeting> listToAdd = new ArrayList<>();
        LocalDateTime m5startTime = LocalDateTime.parse("12.01.2024 15:45", formatter);
        LocalDateTime m5endTime = LocalDateTime.parse("12.01.2024 16:15", formatter);
        Meeting m = new Meeting(Arrays.asList(person), null, null);
    }
    
}