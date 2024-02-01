package com.challenges.addressbook;

public class Contact {
    private static int nextId = 1;
    private int id;
    private String name;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress)
    {
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
}
