package edu.pdx.cs410J.devyani;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for phonebill
 *
 */
public class PhoneBillTest {

    PhoneBill bill;
    PhoneCall call;

    @Before
    public void runBefore () {
        bill = new PhoneBill("devyani");
        call = new PhoneCall("111-555-6666", "222-222-2222", "12/12/2001 12:12 am", "06/01/1990 12/12/2001 12:15 am");
        bill.addPhoneCall(call);
    }

    @Test
    public void testgetPhoneCalls () {
        ArrayList<PhoneCall> phoneList = new ArrayList<PhoneCall>();
        phoneList.add(call);
        assertThat(bill.getPhoneCalls(), equalTo(phoneList));
    }

    /**
     * Tests get customer
*/
    @Test
    public void getCustomerTest () {
        assertThat(bill.getCustomer(),equalTo("devyani"));
    }


}
