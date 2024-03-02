package test;
import source.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

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
        List<Meeting> listToCompare = new ArrayList<>();
        LocalDateTime mStartTime = LocalDateTime.parse("12.01.2024 15:45", formatter);
        LocalDateTime mEndTime = LocalDateTime.parse("12.01.2024 16:15", formatter);
        Meeting m = new Meeting(Arrays.asList(person), mStartTime, mEndTime);
        listToCompare.add(m);

        person.addMeetingToSchedule(m);

        assertFalse(listToCompare == person.getSchedule()); // references should be unique
        assertEquals(listToCompare, person.getSchedule()); // content of list should be the same
    }
    
}