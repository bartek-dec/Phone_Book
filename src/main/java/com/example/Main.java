package com.example;

import com.example.data.DataReader;
import com.example.data.DataReaderImpl;
import com.example.data.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        File find = new File("./src/main/resources/find.txt");
        File directory = new File("./src/main/resources/directory.txt");

        DataReader reader = new DataReaderImpl();

        List<String> contacts = reader.readContacts(find);
        List<Person> people = reader.readPeople(directory);

        List<Person> foundPeople = new ArrayList<>();

        System.out.println("Start searching...");
        long start = System.currentTimeMillis();

        int size = people.size();
        for (String contact : contacts) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(contact, people.get(i).getName())) {
                    foundPeople.add(people.get(i));
                    break;
                }
            }
        }

        long finish = System.currentTimeMillis();
        long delta = finish - start;
        long minutes;
        long seconds;
        long miliSec;

        minutes = delta / 60000;
        seconds = (delta - minutes * 60000) / 1000;
        miliSec = delta - (minutes * 60000 + seconds * 1000);

        StringBuilder builder = new StringBuilder();
        builder.append("Found ");
        builder.append(foundPeople.size());
        builder.append(" / ");
        builder.append(contacts.size());
        builder.append(" entries. Time taken: ");
        builder.append(minutes).append(" min. ");
        builder.append(seconds).append(" sec. ");
        builder.append(miliSec).append(" ms.");

        System.out.println(builder.toString());
    }
}
