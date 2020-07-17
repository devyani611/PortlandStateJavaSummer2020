package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;

public class TextParser implements PhoneBillParser {
    /**
     * Variable to store filename
     */
    BufferedReader br;
    String filename;

    /**\
     * Constructor
     * @param filename
     */
    public TextParser(String filename) {
        this.filename = filename;
    }

    /**
     * Method to parse the call details inside the file
     * @return phonebill
     * @throws ParserException
     */
    @Override
    public PhoneBill parse() throws ParserException {

        PhoneBill bill = new PhoneBill();
        String CallerNumber=null;
        String CalleeNumber =null;
        String start=null;
        String end= null;
        try
        {
            File file = new File(filename);
            if (file.exists())
            {
                br = new BufferedReader(new FileReader(filename));
                String line;

                while ((line = br.readLine()) != null){
                    String [] PhoneBillInfo = line.split(";");
                    String CustomerName = PhoneBillInfo[0];
                    CallerNumber = PhoneBillInfo[1];
                    CalleeNumber = PhoneBillInfo[2];
                    String[] startDateTime = PhoneBillInfo[3].split(" ");
                    start = startDateTime[0]+ " "+ startDateTime[1];
                    String[] endDateTime = PhoneBillInfo[4].split(" ");
                    end = endDateTime[0]+ " "+endDateTime[1];

                    bill.setCustomerName(CustomerName);

                }

               PhoneCall call= new PhoneCall(CallerNumber,CalleeNumber,start,end);
                bill.addPhoneCall(call);

            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return bill;
    }
}
