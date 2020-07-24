package edu.pdx.cs410J.devyani;

import org.junit.Test;

import java.io.IOException;

public class PrettyPrinterTest {

    /**
     * Test case for pretty printer
     */
    @Test
    public void TestPrettyPrinter() throws IOException {
        PhoneBill bill = new PhoneBill("devyani");
        PhoneCall call = new PhoneCall("503-449-7833", "503-449-7832","01/07/2019 07:00 AM","01/07/2019 09:00 AM" );
        bill.addPhoneCall(call);
        PrettyPrinter dumper = new PrettyPrinter("newprettyFile");
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
