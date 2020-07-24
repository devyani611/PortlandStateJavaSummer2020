package edu.pdx.cs410J.devyani;

import org.junit.Test;

import java.io.IOException;

public class TextDumperTest {

    @Test
    public void TesttodumpFile()
    {
        PhoneBill bill = new PhoneBill();
        bill.setCustomerName("devyani");
        PhoneCall call = new PhoneCall("222-555-5555", "888-666-6666", "12/12/2001 12:00 AM", "12/12/2001 12:11 PM");
        bill.addPhoneCall(call);
        TextDumper dumper = new TextDumper("newTextfile");
        try
        {
            dumper.dump(bill);
        }
        catch (IOException i)
        {
            System.out.println(i);
        }


    }

}
