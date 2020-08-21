package com.example.service.search;

import com.example.data.Person;

import java.util.List;

public interface Search {

    List<Person> findPeople(List<Person> allPeople, List<String> names);
}
