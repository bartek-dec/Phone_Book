package com.example;

import com.example.data.DataReader;
import com.example.data.DataReaderImpl;
import com.example.data.Person;
import com.example.service.search.JumpSearch;
import com.example.service.search.LinearSearch;
import com.example.service.search.Search;
import com.example.service.sort.BubbleSort;
import com.example.service.sort.Sort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File find = new File("./src/main/resources/find.txt");
        File directory = new File("./src/main/resources/directory.txt");

        DataReader reader = new DataReaderImpl();

        List<String> contacts = reader.readContacts(find);
        List<Person> people = reader.readPeople(directory);
        List<Person> peopleToBeSorted = new ArrayList<>(people);

        Search linearSearch = new LinearSearch();

        System.out.println("Start searching (linear search)...");
        long start = System.currentTimeMillis();

        List<Person> foundPeople = linearSearch.findPeople(people, contacts);

        long finish = System.currentTimeMillis();
        long linearSearchTime = finish - start;

        System.out.println(getProcessTime(start, finish, foundPeople.size(), contacts.size()));

        System.out.println("\nStart searching (bubble sort + jump search)...");
        Sort bubbleSort = new BubbleSort();
        Search jumpSearch = new JumpSearch();

        start = System.currentTimeMillis();

        long startSort = System.currentTimeMillis();
        List<Person> sortedPeople = bubbleSort.sortByNameAlphabetically(peopleToBeSorted, linearSearchTime);
        long finishSort = System.currentTimeMillis();

        long startSearch = System.currentTimeMillis();

        List<Person> foundPeople2;
        if (sortedPeople == null) {
            foundPeople2 = linearSearch.findPeople(people, contacts);
        } else {
            foundPeople2 = jumpSearch.findPeople(sortedPeople, contacts);
        }
        long finishSearch = System.currentTimeMillis();

        finish = System.currentTimeMillis();
        System.out.println(getProcessTime(start, finish, foundPeople2.size(), contacts.size()));
        System.out.print(getProcessTime(startSort, finishSort, "Sorting time"));
        if (sortedPeople == null) {
            System.out.println(" - STOPPED, moved to linear search");
        }
        System.out.println(getProcessTime(startSearch, finishSearch, "Searching time"));
    }

    private static String getProcessTime(long start, long finish, int foundPeople, int requiredPeople) {
        long delta = finish - start;
        long minutes;
        long seconds;
        long milliSec;

        minutes = delta / 60000;
        seconds = (delta - minutes * 60000) / 1000;
        milliSec = delta - (minutes * 60000 + seconds * 1000);

        StringBuilder builder = new StringBuilder();
        builder.append("Found ");
        builder.append(foundPeople);
        builder.append(" / ");
        builder.append(requiredPeople);
        builder.append(" entries. Time taken: ");
        builder.append(minutes).append(" min. ");
        builder.append(seconds).append(" sec. ");
        builder.append(milliSec).append(" ms.");

        return builder.toString();
    }

    private static String getProcessTime(long start, long finish, String message) {
        long delta = finish - start;
        long minutes;
        long seconds;
        long milliSec;

        minutes = delta / 60000;
        seconds = (delta - minutes * 60000) / 1000;
        milliSec = delta - (minutes * 60000 + seconds * 1000);

        StringBuilder builder = new StringBuilder();
        builder.append(message).append(": ");
        builder.append(minutes).append(" min. ");
        builder.append(seconds).append(" sec. ");
        builder.append(milliSec).append(" ms.");

        return builder.toString();
    }
}
