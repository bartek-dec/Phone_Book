package com.example.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataReaderImpl implements DataReader {

    @Override
    public List<String> readContacts(File file) {
        if (file == null) {
            return null;
        }

        List<String> contacts;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            contacts = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Reading from file " + file.getName() + " has failed");
            return null;
        }
        return contacts;
    }

    @Override
    public List<Person> readPeople(File file) {
        if (file == null) {
            return null;
        }

        List<Person> people = new ArrayList<>(1200000);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contact = line.split("\\s+");
                String phoneNumber = contact[0];

                for (int i = 1; i < contact.length; i++) {
                    builder.append(contact[i]);
                    builder.append(" ");
                }
                people.add(new Person(phoneNumber, builder.toString().trim()));
                builder.setLength(0);
            }
        } catch (IOException e) {
            System.out.println("Reading from file " + file.getName() + " has failed");
            return null;
        }
        return people;
    }
}
