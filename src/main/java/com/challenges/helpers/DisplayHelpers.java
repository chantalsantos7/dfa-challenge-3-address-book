package com.challenges.helpers;

import com.challenges.addressbook.Contact;

public class DisplayHelpers {

    /**
     * Creates a string representation of a contact's details, for display.
     *
     * @param contact the contact whose details will be displayed. Should be an instance of Contact.
     * @return String representation of all information within the contact
     * @throws IllegalArgumentException if contact is null
     * */
    public static String formatContactForDisplay(Contact contact) throws IllegalArgumentException
    {
        if (contact == null) throw new IllegalArgumentException("Contact is not allowed to be null");
        return String.format("%n%s%n" +
                "Phone: %s%n" +
                "Email: %s%n", contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress());
    }
}
