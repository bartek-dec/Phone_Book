package com.example.service.sort;

import com.example.data.Person;

import java.util.List;

public class QuickSort implements Sort {

    @Override
    public List<Person> sortByNameAlphabetically(List<Person> people, long time) {
        if (people == null) {
            return null;
        }

        if (people.size() == 0) {
            return null;
        }

        int left = 0;
        int right = people.size() - 1;

        quickSort(people, left, right);

        return people;
    }

    private void quickSort(List<Person> people, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(people, left, right);
            quickSort(people, left, pivotIndex - 1);
            quickSort(people, pivotIndex + 1, right);
        }
    }

    private int partition(List<Person> people, int left, int right) {
        String pivot = people.get(right).getName();
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            String currentName = people.get(i).getName();
            if (currentName.compareTo(pivot) < 0) {
                swap(people, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(people, partitionIndex, right);
        return partitionIndex;
    }

    private void swap(List<Person> people, int i, int j) {
        Person temp = people.get(i);
        people.set(i, people.get(j));
        people.set(j, temp);
    }
}
