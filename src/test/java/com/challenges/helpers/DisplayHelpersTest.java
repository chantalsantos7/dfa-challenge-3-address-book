package com.challenges.helpers;
import com.challenges.addressbook.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DisplayHelpersTest {

    @Nested
    @DisplayName("DisplayContact Tests")
    public class DisplayContactTests {

        Contact[] contacts;

        @BeforeEach
        public void testInitialisation()
        {
            contacts = new Contact[5];

            for (int i = 0; i < contacts.length; i++)
            {
                contacts[i] = mock(Contact.class);
                when(contacts[i].getName()).thenReturn("mockContact" + i);
                when(contacts[i].getEmailAddress()).thenReturn("mock" + i + "@email.com");
                when(contacts[i].getPhoneNumber()).thenReturn(i + "5555678910");
            }
        }

        @Test
        @DisplayName("DisplayContact should return a String with the correctly formatted details of the supplied contact")
        public void testDisplayContactReturnsCorrectlyFormattedString()
        {
            for (Contact contact : contacts)
            {
                String expectedString = String.format("%s%n" +
                        "Phone: %s%n" +
                        "Email: %s%n", contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress());
                assertEquals(expectedString, DisplayHelpers.formatContactForDisplay(contact));
            }
        }
    }
}
