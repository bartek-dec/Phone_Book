package com.example.service.search;

import com.example.data.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JumpSearch implements Search {

    @Override
    public List<Person> findPeople(List<Person> allPeople, List<String> names) {
        if (allPeople == null || allPeople.size() == 0) {
            return null;
        }

        if (names == null || names.size() == 0) {
            return null;
        }

        List<Person> foundPeople = new ArrayList<>();
        for (String name : names) {
            Person person = findPerson(allPeople, name);
            if (person != null) {
                foundPeople.add(person);
            }
        }
        return foundPeople;
    }

    private Person findPerson(List<Person> people, String name) {
        int right = 0;
        int left = 0;

        if (Objects.equals(people.get(right).getName(), name)) {
            return people.get(right);
        }

        int size = people.size();
        int block = (int) Math.sqrt(people.size());

        while (right < size - 1) {
            right = Math.min(size - 1, right + block);

            if (people.get(right).getName().compareTo(name) >= 0) {
                break;
            }

            left = right;
        }

        if ((right == size - 1) && (name.compareTo(people.get(right).getName()) > 0)) {
            return null;
        }

        return backwardSearch(people, name, left, right);
    }

    private Person backwardSearch(List<Person> people, String name, int left, int right) {
        for (int i = right; i > left; i--) {
            Person person = people.get(i);

            if (Objects.equals(name, person.getName())) {
                return person;
            }

            if (name.compareTo(person.getName()) > 0) {
                return null;
            }
        }
        return null;
    }
}
