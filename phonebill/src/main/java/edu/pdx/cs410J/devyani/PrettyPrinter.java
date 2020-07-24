package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PrettyPrinter implements PhoneBillDumper {

     FileWriter writer;


     /**
      * Default Constructor
      *
      * @param textFile Name of the text file to write output data to

*/
      PrettyPrinter(String textFile) {
      try {
      File file = new File(textFile);
      if (file.exists()) {
          writer = new FileWriter(file, true);
          System.out.println("Writing to a pretty print existing file  " + file.getAbsoluteFile() + "\n");
      }


        else {
     file.createNewFile();
     writer = new FileWriter(file);
      System.out.println("A new pretty print file created  " + file.getAbsoluteFile() + "\n");
      }
      } catch (IOException e) {
      System.err.println("Pretty print file error " + e.getMessage());
      System.exit(1);
      }
      }

     /**
     * @param bill
     * @throws IOException
*/
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {
        ArrayList<PhoneCall> callList = (ArrayList<PhoneCall>) (bill).getPhoneCalls();


        if (bill == null) {
            System.out.println("No Phonebill found..!!");
            System.exit(1);
        } else {
            String customerName = bill.getCustomer();
            if (customerName == null || customerName.equals("")) {
                System.out.println("Invalid Customer name!");
            }
            else {
                //writer.write("")
                writer.write("\n\nHey " +bill.getCustomer() + " below are your phonecall details...!!\n\n");
            }
                for (PhoneCall call : callList) {

                    writer.write(call.getCaller() + "\n");
                    writer.write(call.getCallee() + "\n");
                    String startTime = call.getStartTimeString();
                    writer.write(startTime + "\n");
                    String endTime = call.getEndTimeString();
                    writer.write(endTime + "\n");
                    long callDuration = call.getEndTime().getTime() - call.getStartTime().getTime();
                    writer.write("Call Duration: " + TimeUnit.MILLISECONDS.toMinutes(callDuration) + " minutes");
                }
                writer.close();
            }


        }





    }


