package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextDumper implements PhoneBillDumper {

    /**
     * Variable to store filename
     */
    String filename;


    /**
     * Constructor to initialize filename
     * @param filename name of the file
     */
    public TextDumper(String filename){
        this.filename = filename;
    }

    /**
     *Method to write call details to the file
     * @throws IOException
     */
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {

        ArrayList<PhoneCall> callList = (ArrayList<PhoneCall>) (bill).getPhoneCalls();
        FileWriter writer;

        try {
            writer = new FileWriter(filename);

        } catch (IOException e) {
            throw e;
        }

        for (PhoneCall call : callList )
        {
            writer.write(bill.getCustomer() + ";");
            writer.write(call.getCaller() + ";");
            writer.write(call.getCallee() + ";");
            String start = call.getStartTimeString();
            writer.write(start + ";");
            String end = call.getEndTimeString();
            writer.write(end + "\n");
        }
        writer.close();
    }
}
