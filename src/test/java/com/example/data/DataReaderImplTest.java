package com.example.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderImplTest {

    private final String peoplePath = "./src/test/resources/testPeople.txt";
    private final String contactsPath = "./src/test/resources/testContacts.txt";

    private DataReader reader;
    private File people;
    private File contacts;

    @BeforeEach
    void setUp() {
        reader = new DataReaderImpl();
        people = new File(peoplePath);
        contacts = new File(contactsPath);
    }

    @Test
    void readContacts() {
        List<String> expectedContacts = new ArrayList<>();
        expectedContacts.add("Lyndel Raymond");
        expectedContacts.add("Meta Joappa");
        expectedContacts.add("Kynthia Nadbus");
        expectedContacts.add("Ethelind Skeie");

        assertEquals(expectedContacts, reader.readContacts(contacts));
    }

    @Test
    void readContactsWhenNoFileThanNull() {
        assertNull(reader.readContacts(null));
    }

    @Test
    void readPeople() {
        List<Person> expectedPeople = new ArrayList<>();
        expectedPeople.add(new Person("59339418", "Gabrila Araminta"));
        expectedPeople.add(new Person("75554798", "Amalia Gaelan"));
        expectedPeople.add(new Person("21713301", "Peta Hwu"));
        expectedPeople.add(new Person("57905921", "Valaria Lukas"));
        expectedPeople.add(new Person("64260585", "Clea Griseldis"));

        assertEquals(expectedPeople, reader.readPeople(people));
    }

    @Test
    void readPeopleWhenNoFileThanNull() {
        assertNull(reader.readPeople(null));
    }
}