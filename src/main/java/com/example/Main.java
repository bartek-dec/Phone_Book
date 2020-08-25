package com.example;

import com.example.data.DataReader;
import com.example.data.DataReaderImpl;
import com.example.data.Person;
import com.example.service.search.BinarySearch;
import com.example.service.search.JumpSearch;
import com.example.service.search.LinearSearch;
import com.example.service.search.Search;
import com.example.service.sort.BubbleSort;
import com.example.service.sort.QuickSort;
import com.example.service.sort.Sort;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File find = new File("./src/main/resources/find.txt");
        File directory = new File("./src/main/resources/directory.txt");

        DataReader reader = new DataReaderImpl();

        List<String> contacts = reader.readContacts(find);
        List<Person> people = reader.readPeople(directory);
        List<Person> peopleToBeBubbleSort = new ArrayList<>(people);
        List<Person> peopleToBeQuickSort = new ArrayList<>(people);


        System.out.println("Start searching (linear search)...");
        Search linearSearch = new LinearSearch();

        long start = System.currentTimeMillis();

        List<Person> foundPeopleLinear = linearSearch.findPeople(people, contacts);

        long finish = System.currentTimeMillis();
        long linearSearchTime = finish - start;

        System.out.print("Found " + foundPeopleLinear.size() + " / " + contacts.size() + " entries. ");
        System.out.print("Time taken: ");
        System.out.println(calculateTime(start, finish));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nStart searching (bubble sort + jump search)...");
        Sort bubbleSort = new BubbleSort();
        Search jumpSearch = new JumpSearch();

        start = System.currentTimeMillis();

        long startSort = System.currentTimeMillis();
        List<Person> bubbleSortPeople = bubbleSort.sortByNameAlphabetically(peopleToBeBubbleSort);
        long finishSort = System.currentTimeMillis();

        long startSearch = System.currentTimeMillis();
        List<Person> foundPeopleJump;
        if (bubbleSortPeople == null) {
            foundPeopleJump = linearSearch.findPeople(people, contacts);
        } else {
            foundPeopleJump = jumpSearch.findPeople(bubbleSortPeople, contacts);
        }
        long finishSearch = System.currentTimeMillis();

        finish = System.currentTimeMillis();

        System.out.print("Found " + foundPeopleJump.size() + " / " + contacts.size() + " entries. ");
        System.out.print("Time taken: ");
        System.out.println(calculateTime(start, finish));

        System.out.print("Sorting time: ");
        System.out.print(calculateTime(startSort, finishSort));
        if (bubbleSortPeople == null) {
            System.out.print(" - STOPPED, moved to linear search\n");
        } else {
            System.out.print("\n");
        }
        System.out.print("Searching time: ");
        System.out.println(calculateTime(startSearch, finishSearch));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nStart searching (quick sort + binary search)...");
        Sort quickSort = new QuickSort();
        Search binarySearch = new BinarySearch();

        start = System.currentTimeMillis();

        startSort = System.currentTimeMillis();
        List<Person> quickSortPeople = quickSort.sortByNameAlphabetically(peopleToBeQuickSort);
        finishSort = System.currentTimeMillis();

        startSearch = System.currentTimeMillis();
        List<Person> foundPeopleBinary;
        if (quickSortPeople == null) {
            foundPeopleBinary = linearSearch.findPeople(people, contacts);
        } else {
            foundPeopleBinary = binarySearch.findPeople(quickSortPeople, contacts);
        }
        finishSearch = System.currentTimeMillis();

        finish = System.currentTimeMillis();

        System.out.print("Found " + foundPeopleBinary.size() + " / " + contacts.size() + " entries. ");
        System.out.print("Time taken: ");
        System.out.println(calculateTime(start, finish));

        System.out.print("Sorting time: ");
        System.out.print(calculateTime(startSort, finishSort));
        if (quickSortPeople == null) {
            System.out.print(" - STOPPED, moved to linear search\n");
        } else {
            System.out.print("\n");
        }
        System.out.print("Searching time: ");
        System.out.println(calculateTime(startSearch, finishSearch));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\nStart searching (hash table)...");
        Hashtable<String, Person> table = new Hashtable<>();

        start = System.currentTimeMillis();

        startSort = System.currentTimeMillis();
        people.stream().forEach(p -> table.put(p.getName(), p));
        finishSort = System.currentTimeMillis();

        startSearch = System.currentTimeMillis();
        List<Person> foundPeopleHashTable = new ArrayList<>();
        for (String name : contacts) {
            Person person = table.get(name);
            foundPeopleHashTable.add(person);
        }

        finishSearch = System.currentTimeMillis();

        finish = System.currentTimeMillis();

        System.out.print("Found " + foundPeopleHashTable.size() + " / " + contacts.size() + " entries. ");
        System.out.print("Time taken: ");
        System.out.println(calculateTime(start, finish));

        System.out.print("Creating time: ");
        System.out.print(calculateTime(startSort, finishSort) + "\n");
        System.out.print("Searching time: ");
        System.out.println(calculateTime(startSearch, finishSearch));
    }

    private static String calculateTime(long start, long finish) {
        long delta = finish - start;
        long minutes;
        long seconds;
        long milliSec;

        minutes = delta / 60000;
        seconds = (delta - minutes * 60000) / 1000;
        milliSec = delta - (minutes * 60000 + seconds * 1000);

        StringBuilder builder = new StringBuilder();
        builder.append(minutes).append(" min. ");
        builder.append(seconds).append(" sec. ");
        builder.append(milliSec).append(" ms.");

        return builder.toString();
    }
}
