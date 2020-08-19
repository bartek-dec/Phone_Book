package com.example.data;

import java.io.File;
import java.util.List;

public interface DataReader {

    List<String> readContacts(File file);

    List<Person> readPeople(File file);
}
