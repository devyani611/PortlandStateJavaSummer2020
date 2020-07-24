package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextDumper implements PhoneBillDumper {

    /**
     * Variable to store filename
     */
    String filename;
    FileWriter writer;


    /**
     * Constructor to initialize filename
     *
     * @param filename name of the file
     */
    public TextDumper(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
               writer = new FileWriter(file,true);
                System.out.println("Writing to an existing text file : " + file.getAbsoluteFile() + "\n");
            } else {
                file.createNewFile();
                writer = new FileWriter(file);
                System.out.println("A new text file created:  " + file.getAbsoluteFile() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error while dumping to the file " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Method to write call details to the file
     *
     * @throws IOException
     */
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {
        ArrayList<PhoneCall> callList = (ArrayList<PhoneCall>) (bill).getPhoneCalls();

        for (PhoneCall call : callList )
        {
            writer.write(bill.getCustomer() + ";");
            writer.write(call.getCaller() + ";");
            writer.write(call.getCallee() + ";");
            String start = call.getStartTimeString();
            //System.out.println("in text dumper" +start);
            writer.write(start + ";");
            String end = call.getEndTimeString();
           // System.out.println("in text dumper" +end);
            writer.write(end + "\n");
            //writer.write("\n");
        }
        writer.close();
    }
}

