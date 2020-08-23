package com.example.data;

import java.util.Objects;

public class Person {

    private String phoneNumber;
    private String name;

    public Person(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return phoneNumber.equals(person.phoneNumber) &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, name);
    }
}
