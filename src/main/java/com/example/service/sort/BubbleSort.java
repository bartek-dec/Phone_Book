package com.example.service.sort;

import com.example.data.Person;

import java.util.List;

public class BubbleSort implements Sort {

    @Override
    public List<Person> sortByNameAlphabetically(List<Person> people, long time) {
        if (people == null) {
            return null;
        }

        long startSort = System.currentTimeMillis();

        int size = people.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                String nameLeft = people.get(j).getName();
                String nameRight = people.get(j + 1).getName();

                if (nameLeft.compareTo(nameRight) > 0) {
                    Person temp = people.get(j);
                    people.set(j, people.get(j + 1));
                    people.set(j + 1, temp);

                    if ((System.currentTimeMillis() - startSort) > 10 * time) {
                        return null;
                    }
                }
            }
        }
        return people;
    }
}
