package com.example.strayapp;

import com.example.strayapp.UI.MainActivity;

import java.util.Date;

public class Animal {
    String City;
    String Description;
    String Name;
    String Street;
    String Date;
    String Contact;
    String Encoded;

    public Animal(){}

    public Animal(String city, String description, String name, String street, Date date, String contact, String encoded) {
        City = city;
        Description = description;
        Name = name;
        Street = street;
        Date = date.toString();
        Contact = contact;
        Encoded = encoded;
    }


    public String getEncoded() {
        return Encoded;
    }

    public void setEncoded(String encoded) {
        Encoded = encoded;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }


    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
