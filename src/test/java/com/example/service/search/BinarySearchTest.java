package com.example.service.search;

import com.example.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    private Search search;
    private List<Person> people;
    private List<String> contacts;
    private List<Person> foundPeople;

    @BeforeEach
    void setUp() {
        search = new BinarySearch();

        people = new ArrayList<>();
        people.add(new Person("75554798", "Amalia Gaelan"));
        people.add(new Person("64260585", "Clea Griseldis"));
        people.add(new Person("59339418", "Gabrila Araminta"));
        people.add(new Person("21713301", "Peta Hwu"));
        people.add(new Person("57905921", "Valaria Lukas"));

        contacts = new ArrayList<>();
        contacts.add("Clea Griseldis");
        contacts.add("Peta Hwu");

        foundPeople = new ArrayList<>();
        foundPeople.add(new Person("64260585", "Clea Griseldis"));
        foundPeople.add(new Person("21713301", "Peta Hwu"));
    }

    @Test
    void findPeople() {
        assertEquals(foundPeople, search.findPeople(people, contacts));
    }

    @Test
    void findPeopleWhenNoPeopleListThanNull() {
        assertNull(search.findPeople(null, contacts));
    }

    @Test
    void findPeopleWhenNoContactsListThanNull() {
        assertNull(search.findPeople(people, null));
    }

    @Test
    void findPeopleWhenPeopleListEmptyThanNull() {
        assertNull(search.findPeople(new ArrayList<>(), contacts));
    }

    @Test
    void findPeopleWhenContactsListEmptyThanNull() {
        assertNull(search.findPeople(people, new ArrayList<>()));
    }
}