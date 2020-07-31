package edu.pdx.cs410J.devyani;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MessagesTest {

    @Test
    public void testMissingRequiredParameter() {
        assertThat(Messages.missingRequiredParameter("devyani"), equalTo("The required parameter \"devyani\" is missing"));
    }

    @Test
    public void testNoPhoneBillForCustomer(){
        assertThat(Messages.noPhoneBillForCustomer("devyani"), equalTo("No phonebill for customer devyani"));
    }

   @Test
    public void testMappingCount(){
        assertThat(Messages.getMappingCount(1), equalTo("Server contains 1 phone calls."));

    }

    @Test
    public void testAddedCall(){
        assertThat(Messages.addedCall(""), equalTo("Added "));

    }
}
