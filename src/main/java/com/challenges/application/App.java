package com.challenges.application;

import com.challenges.addressbook.AddressBook;
import com.challenges.addressbook.Contact;
import com.challenges.addressbook.ContactDetailType;
import com.challenges.helpers.DisplayHelpers;
import com.challenges.helpers.ValidatorHelpers;

import java.util.Iterator;
import java.util.Scanner;

public class App {
    private static AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {

        int option;
        System.out.println("Welcome to your DFCorp Address Book \n");
        do {
            System.out.println("Would you like to: ");
            System.out.println("1. Add a new contact,");
            System.out.println("2. Find a contact,");
            System.out.println("3. Edit a contact,");
            System.out.println("4. View all current contacts,");
            System.out.println("5. Delete a contact,");
            System.out.println("6. Delete all contacts");
            System.out.println("7. Exit program.\n");

            System.out.println("Enter an option from 1-7: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();
            in.nextLine();
            System.out.println("You selected " + option + "\n");
            menuSelector(option);
        } while (option != 7);


    }

    private static void menuSelector(int chosenOption)
    {
        switch (chosenOption)
        {
            case 1:
                addingContactWithInputValidation();
                break;
            case 2:
                findContact();
                break;
            case 3:
                editContact();
                break;
            case 4:
                System.out.println(addressBook.viewAllContacts());
                break;
            case 5:
                deleteContact();
                break;
            case 6:
                deleteAllContacts();
                break;
            default:
        }
    }


    private static void addingContactWithInputValidation()
    {
        System.out.println("Please enter the details of the new contact.\nYou can leave either the phone number or email address empty, but not both.");
        Contact newContact;

        Scanner in = new Scanner(System.in);
        System.out.print("Name: ");

        String name = in.nextLine().trim();
        name = validateNameInput(name);

        System.out.println("\nOnly UK phone numbers are currently valid.");
        System.out.print("Phone Number: ");
        String phoneNumber = in.nextLine().trim();
        phoneNumber = validatePhoneInput(phoneNumber);

        System.out.println("\nCorrect format for email - [user]@[domainName]");
        System.out.print("Email Address: ");
        String email = in.nextLine().trim();
        email = validateEmailInput(email);

        try {
            newContact = new Contact(name, phoneNumber, email);
            addressBook.addContact(newContact);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }


    }

    private static Contact findContact()
    {
        Contact foundContact;
        System.out.println("Do you want to find the contact by,\n 1. Name\n 2. Phone Number\n 3. Email Address");
        System.out.print("Enter your selection: ");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        in.nextLine();

        switch (option) {
            case 1:
                System.out.println("Enter name: ");
                String name = in.nextLine().trim();
                name = validateNameInput(name);
                foundContact = addressBook.findContact(name);
                System.out.println(DisplayHelpers.formatContactForDisplay(foundContact));
                break;
            case 2:
                System.out.println("Enter phone number: ");
                String phoneNumber = in.nextLine().trim();
                phoneNumber = validatePhoneInput(phoneNumber);
                foundContact = addressBook.findContact(phoneNumber, ContactDetailType.PHONE_NUMBER);
                System.out.println(DisplayHelpers.formatContactForDisplay(foundContact));
                break;
            case 3:
                System.out.println("Enter email: ");
                String email = in.nextLine().trim();
                email = validateEmailInput(email);
                foundContact = addressBook.findContact(email, ContactDetailType.EMAIL_ADDRESS);
                System.out.println(DisplayHelpers.formatContactForDisplay(foundContact));
                break;
            default:
                return null;
        }
        return foundContact;
    }

    private static void editContact()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Which contact do you want to edit?");
        try {
            Contact contactToEdit = findContact();
            System.out.println("\nFor each entry, type the new value. To leave it unchanged, leave it blank.");

            System.out.printf("Name: %s, new value: ", contactToEdit.getName());
            String newName = in.nextLine().trim();

            if (!newName.isEmpty())
            {
                newName = validateNameInput(newName);
                contactToEdit.setName(newName);
            }

            System.out.printf("Phone: %s, new value: ", contactToEdit.getPhoneNumber());
            String newPhone = in.nextLine().trim();
            if (!newPhone.isEmpty())
            {
                newPhone = validatePhoneInput(newPhone);
                contactToEdit.setPhoneNumber(newPhone);
            }

            System.out.printf("Email: %s, new value: ", contactToEdit.getEmailAddress());
            String newEmail = in.nextLine().trim();
            if (!newEmail.isEmpty())
            {
                newEmail = validateEmailInput(newEmail);
                contactToEdit.setEmailAddress(newEmail);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("That contact could not be found, returning to the menu.");
        }
    }

    private static void deleteContact()
    {
        System.out.println("Which contact do you want to delete?");
        Contact deleteContact;

        try {
            deleteContact = findContact();
            addressBook.removeContact(deleteContact);
            System.out.println("Contact deleted.");
        } catch (NullPointerException e)
        {
            System.out.println("Could not find that contact. Returning to menu.");
        }
    }

    private static void deleteAllContacts()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Are you sure you want to delete all contacts in your address book? This cannot be undone.");
        System.out.println("Type yes to confirm deletion of all contacts:");

        if (in.nextLine().trim().equals("yes"))
        {
            Iterator<Contact> contactsIterator = addressBook.getContactsList().iterator();
            while (contactsIterator.hasNext())
            {
                contactsIterator.next();
                contactsIterator.remove();
            }

            System.out.println("All contacts have been deleted.");
        }
    }

    private static String validateNameInput(String name)
    {
        while (name.isEmpty())
        {
            Scanner in = new Scanner(System.in);
            System.out.print("The name is not allowed to be blank.\nPlease enter name again: ");
            name = in.nextLine().trim();
        }
        return name;
    }

    private static String validatePhoneInput(String phoneNumber)
    {
        boolean contactValid = ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER);
        while(!contactValid)
        {
            Scanner in = new Scanner(System.in);
            System.out.print("That phone number input was invalid.\nPlease enter the phone number again: ");
            phoneNumber = in.nextLine().trim();
            contactValid = ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER);
        }
        return phoneNumber;
    }

    private static String validateEmailInput(String email)
    {
        boolean contactValid = ValidatorHelpers.validateContactInput(email, ContactDetailType.EMAIL_ADDRESS);
        while(!contactValid)
        {
            Scanner in = new Scanner(System.in);
            System.out.print("That email input was invalid.\nPlease enter the email address again: ");
            email = in.nextLine().trim();
            contactValid = ValidatorHelpers.validateContactInput(email, ContactDetailType.EMAIL_ADDRESS);
        }
        return email;
    }


}
