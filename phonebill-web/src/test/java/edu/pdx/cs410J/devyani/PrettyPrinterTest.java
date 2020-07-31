package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneCall;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class PrettyPrinterTest {
    /**
     * Test case for pretty printer
     */
    @Test
    public void TestPrettyPrinter() throws IOException {
         PhoneBill bill = new PhoneBill("devyani");
        PhoneCall call = new PhoneCall("503-449-7833", "503-449-7832","01/07/2019 07:00 AM","01/07/2019 09:00 AM" );
         bill.addPhoneCall(call);

        ArrayList<PhoneCall> callsList = bill.getPhoneCalls();
        PrettyPrinter dumper = new PrettyPrinter();
        try
        {
            for (PhoneCall call1 : callsList){
            dumper.dump(call1);
        }

        }
        catch (IOException i)
        {
            System.out.println(i);
        }

    }
}
