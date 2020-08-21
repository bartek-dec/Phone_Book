package com.example;

import com.example.data.DataReader;
import com.example.data.DataReaderImpl;
import com.example.data.Person;
import com.example.service.search.LinearSearch;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File find = new File("./src/main/resources/find.txt");
        File directory = new File("./src/main/resources/directory.txt");

        DataReader reader = new DataReaderImpl();

        List<String> contacts = reader.readContacts(find);
        List<Person> people = reader.readPeople(directory);

        LinearSearch linearSearch = new LinearSearch();

        System.out.println("Start searching...");
        long start = System.currentTimeMillis();

        List<Person> foundPeople = linearSearch.findPeople(people, contacts);

        long finish = System.currentTimeMillis();

        System.out.println(getProcessTime(start, finish, foundPeople.size(), contacts.size()));
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
}
