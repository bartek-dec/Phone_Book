package com.example.service.sort;

import com.example.data.Person;

import java.util.List;

public interface Sort {

    List<Person> sortByNameAlphabetically(List<Person> people);
}
