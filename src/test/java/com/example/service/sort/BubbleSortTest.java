package com.example.service.sort;

import com.example.data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    private Sort sort;
    private List<Person> people;
    private List<Person> sortedPeople;

    @BeforeEach
    void setUp() {
        sort = new BubbleSort();

        people = new ArrayList<>();
        people.add(new Person("59339418", "Gabrila Araminta"));
        people.add(new Person("75554798", "Amalia Gaelan"));
        people.add(new Person("21713301", "Peta Hwu"));
        people.add(new Person("57905921", "Valaria Lukas"));
        people.add(new Person("64260585", "Clea Griseldis"));

        sortedPeople = new ArrayList<>();
        sortedPeople.add(new Person("75554798", "Amalia Gaelan"));
        sortedPeople.add(new Person("64260585", "Clea Griseldis"));
        sortedPeople.add(new Person("59339418", "Gabrila Araminta"));
        sortedPeople.add(new Person("21713301", "Peta Hwu"));
        sortedPeople.add(new Person("57905921", "Valaria Lukas"));
    }

    @Test
    void sortByNameAlphabetically() {
        assertEquals(sortedPeople, sort.sortByNameAlphabetically(people, 1000));
    }

    @Test
    void whenNoListThanNull() {
        assertNull(sort.sortByNameAlphabetically(null, 1000));
    }

    @Test
    void whenEmptyListThanNull() {
        assertNull(sort.sortByNameAlphabetically(new ArrayList<>(), 1000));
    }
}