package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.*;

public class PersonTest {
    
    Person person;

    @BeforeEach
    void init() {
        person = new Person("Bobby Brown", "bobbyb@bmail.bom");
    }

    @Test
    void whenGetPersonsSchedule_thenShouldNotBeEqual() {
        ;
    }
    
}