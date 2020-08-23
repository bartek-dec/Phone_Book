package com.example.service.search;

import com.example.data.Person;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch implements Search {

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
            Person person = binarySearch(allPeople, name);

            if (person != null) {
                foundPeople.add(person);
            }
        }
        return foundPeople;
    }

    private Person binarySearch(List<Person> people, String name) {
        int left = 0;
        int right = people.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            Person current = people.get(mid);

            if (name.compareTo(current.getName()) == 0) {
                return current;
            } else if (name.compareTo(current.getName()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }
}
