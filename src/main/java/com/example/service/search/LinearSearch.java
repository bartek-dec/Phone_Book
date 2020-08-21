package com.example.service.search;

import com.example.data.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LinearSearch implements Search {

    @Override
    public List<Person> findPeople(List<Person> allPeople, List<String> names) {
        if (allPeople == null) {
            return null;
        }

        if (names == null) {
            return null;
        }

        List<Person> foundPeople = new ArrayList<>();

        int size = allPeople.size();
        for (String name : names) {
            for (int i = 0; i < size; i++) {
                Person person = allPeople.get(i);

                if (Objects.equals(name, person.getName())) {
                    foundPeople.add(person);
                    break;
                }
            }
        }
        return foundPeople;
    }
}
