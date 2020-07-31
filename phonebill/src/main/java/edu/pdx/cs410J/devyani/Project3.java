package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;

import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project3 {

    //private static PhoneBill bill;
    public static void main(String args[]) throws ParseException {
        PhoneCall call = new PhoneCall();
        PhoneBill bill = new PhoneBill();

        String fileName = "";
        String prettyFile = "";

        /**
         * Booleans to print and to read/write text file
         */
        boolean print = false;
        boolean readTextFile = false;
        boolean writeTextFile = false;
        boolean prettyprint = false;

        /**
         * Checks each given argument and calls Readme method, is any of the argument is "-README".
         */
        if (Arrays.stream(args).anyMatch(s -> s.equals("-README"))) {
            ReadMe();
            System.exit(0);
        } else {
            switch (args.length) {
                case 0:
                    System.err.println("Missing command line arguments");
                    System.exit(1);

                case 1:
                    if(args[0].equals("-pretty") || args[0].equals("-textFile")){
                        System.err.println("Missing filename");
                        System.exit(1);
                    }
                    else {
                        System.err.println("Missing Caller Number");
                        System.exit(1);
                    }
                    break;

                case 2:
                    System.err.println("Missing Callee Number");
                    System.exit(1);

                    break;

                case 3:
                    System.err.println("Missing Call start date");
                    System.exit(1);
                    break;

                case 4:
                    System.err.println("Missing Call start time");
                    System.exit(1);
                    break;

                case 5:
                    System.err.println("Missing call start AM/PM");
                    System.exit(1);
                    break;

                case 6:
                    System.err.println("Missing call end date");
                    System.exit(1);
                    break;
                case 7:
                    System.err.println("Missing call end time");
                    System.exit(1);
                    break;

                case 8:
                    System.err.println("Missing call end AM/PM");
                    System.exit(1);
                    break;

                case 9:
                    if (args[0].equals("-print")) {
                            System.err.println("Missing call end AM/PM");
                            System.exit(1);
                    }
                    else if (args[0].equals("-textFile")) {
                        System.err.println("Missing call end time");
                        System.exit(1);
                    }
                    else if (args[0].equals("-pretty")) {
                        System.err.println("Missing call end time");
                        System.exit(1);
                    }

                    break;
                case 10:
                    if(args[0].equals("-pretty") || args[0].equals("-textFile")){
                        System.err.println("Missing call end AM/PM");
                        System.exit(1);
                    }
                    else if(args[0].equals("-print")){
                        print = true;
                    }
                    else{
                        System.err.println("please enter valid arguments");
                    }

                    break;

                case 11:
                    if ((args[0].equals("-print") && args[1].equals("-textFile")) || (args[0].equals("-print") && args[1].equals("-pretty")) ||
                            (args[0].equals("-pretty") && args[2].equals("-print"))) {
                        System.err.println("Missing call end AM/PM");
                        System.exit(1);
                    }
                    else if(args[0].equals("-pretty") && args[2].equals("-textFile")){
                        System.err.println("Missing call end time");
                        System.exit(1);
                    }
                    else if(args[0].equals("-textFile")) {
                        if(args[2].equals("-print")){
                            System.err.println("Missing call end AM/PM");
                            System.exit(1);
                        }
                        else if (args[2].equals("-pretty")) {
                            System.err.println("Missing call end time");
                            System.exit(1);
                        }
                        else {
                            readTextFile = true;
                            writeTextFile = true;
                        }
                    }

                    else if (args[0].equals("-pretty")) {
                        print = true;
                        prettyFile = args[1];
                        }

                    else {
                        System.err.println("please enter valid arguments");
                        System.exit(1);
                    }
                    break;

                case 12:
                    if((args[0].equals("-pretty") && args[2].equals("-textFile")) || (args[2].equals("-pretty") && args[0].equals("-textFile")) ) {
                        if(args[4].equals("-print")){
                            System.err.println("Missing call end time");
                            System.exit(1);
                        }
                        else {
                            System.err.println("Missing call end AM/PM");
                            System.exit(1);
                        }
                    }

                    else if(args[0].equals("-pretty")){
                        if (args[2].equals("-print") && !args[3].equals("-textFile")){
                            print = true;
                        }
                        else{
                            System.err.println("please enter valid arguments");
                            System.exit(1);
                        }
                    }
                    else if(args[0].equals("-textFile")){
                        if (args[2].equals("-print") && !args[3].equals("-pretty")) {
                            print = true;
                            readTextFile = true;
                            writeTextFile = true;

                        }

                        else{
                            System.err.println("please enter valid arguments");
                            System.exit(1);
                        }
                    }
                    else if(args[0].equals("-print") && args[1].equals("-textFile")){
                        if(!args[3].equals("-pretty")){
                            readTextFile = true;
                            writeTextFile = true;
                            print = true;

                        }
                        else {
                            System.err.println("Missing call end time");
                            System.exit(1);
                        }

                    }
                    else if(args[0].equals("-print") && args[1].equals("-pretty")){
                        if(!args[3].equals("-textFile")){
                            prettyFile = args[2];
                            print = true;

                        }
                        else {
                            System.err.println("Missing call end time");
                            System.exit(1);
                        }

                    }

                    else{
                        System.err.println("please enter valid arguments");
                        System.exit(1);
                    }
                    break;

                case 13:
                    if(args[0].equals("-pretty") && args[2].equals("-textFile")) {
                        readTextFile = true;
                        writeTextFile = true;
                        print = true;
                    }
                    else if(args[2].equals("-pretty") && args[0].equals("-textFile")){
                        readTextFile = true;
                        writeTextFile = true;
                        print = true;
                    }
                    else{
                        System.err.println("please enter valid arguments");
                        System.exit(1);
                    }
                    break;

                default:
                    if(args.length > 14){
                        System.err.println("Too many arguments, please enter valid arguments");
                        System.exit(1);
                    }
                    else if(args.length == 14){
                        if(!args[0].equals("-print") && !args[1].equals("-print") && !args[2].equals("-print")
                        && !args[3].equals("-print") && !args[4].equals("-print")){
                            System.err.println("Too many arguments, please enter valid arguments");
                            System.exit(1);
                        }
                        else if(args[0].equals("-print") || args[1].equals("-print") || args[2].equals("-print")
                                || args[3].equals("-print") || args[4].equals("-print")) {
                            if (!args[0].equals("-pretty") && !args[1].equals("-pretty") && !args[2].equals("-pretty")
                                    && !args[3].equals("-pretty")) {
                                System.err.println("Too many arguments, please enter valid arguments");
                                System.exit(1);
                            }
                            else if (!args[0].equals("-textFile") && !args[1].equals("-textFile") && !args[2].equals("-textFile")
                                    && !args[3].equals("-textFile")) {
                                System.err.println("Too many arguments, please enter valid arguments");
                                System.exit(1);
                            }
                            else{
                                print = true;
                                writeTextFile = true;
                                readTextFile = true;
                            }
                        }
                        else {
                            System.err.println("please enter valid arguments");
                            System.exit(1);
                        }


                    }


        }
        }
        /**
         * Checks for the position of -print and -textFile and set the value of variable i accordingly
         */

       int i = 0;
        if (args[0].equals("-print") && (!args[1].equals("-textFile") && !args[3].equals("-textFile")) &&
                (!args[1].equals("-pretty") && !args[3].equals("-pretty"))) {
            i = 1;
        }
        else if (args[0].equals("-print") && args[1].equals("-textFile")){
            fileName = args[2];
            if(args[3].equals("-pretty")){
                prettyFile = args[4];
                i = 5;
            }
            else{
               i =3;
            }
        }
        else if (args[0].equals("-print") && args[1].equals("-pretty")){
            prettyFile = args[2];
            if(args[3].equals("-textFile")){
                fileName = args[4];
                i =5;
            }
            else {
                i =3;
            }
        }
        else if(args[0].equals("-pretty")){
            prettyFile = args[1];
            if(args[2].equals("-textFile")){
                fileName = args[3];
                if(args[4].equals("-print")){
                    i =5;
                }
                else {
                    i =4;
                }
            }
            else if (args[2].equals("-print")){
                if(args[3].equals("-textFile")){
                    fileName = args[4];
                    i =5;
                }
                else {
                    i =3;
                }
            }
            else {
                i = 2;
            }
        }
        else if(args[0].equals("-textFile")){
            fileName = args[1];
            if(args[2].equals("-pretty")) {
                prettyFile = args[3];
                if (args[4].equals("-print")) {
                    i = 5;
                } else {
                    i = 4;
                }
            }
            else if (args[2].equals("-print")){
                if(args[3].equals("-pretty")){
                    prettyFile = args[4];
                    i =5;
                }
                else {
                    i =3;
                }
            }
            else {
                i = 2;
            }

        }

        /**
         * get all the values of the arguments
         */

        bill = new PhoneBill(args[i]);
        bill.customer = bill.getCustomer();
        call.callerNumber = IsvalidPhoneNumber(args[i + 1]);
        call.calleeNumber = IsvalidPhoneNumber(args[i + 2]);
        call.start = IsvalidTime(args[i + 3] + " " + args[i + 4]+ " "+ args[i+5]);
        call.end = IsvalidTime(args[i + 6] + " " + args[i + 7]+ " "+ args[i+8]);

        bill.addPhoneCall(call);

        if (writeTextFile == true || readTextFile == true) {
            //System.out.println(call);
            GotoTextFile(call, bill, fileName);
        }


        if (!prettyFile.equals(""))
        {
            if (prettyFile.equals("-"))
            {
                print = true;
            }
            else
            {
               PrettyPrinter printer = new PrettyPrinter(prettyFile);
               try
                {
                    printer.dump(bill);

                }
                catch (IOException e)
                {
                    System.err.println(e);
                }
            }
        }

        /**
         * if print is true, output the call details
         */
        if (print) {
            if(prettyFile.equals("-")){
                System.out.println("Hello!! Below are the phone call details for Customer: " + bill.getCustomer());
                System.out.println(call.toString());
                long callDuration = call.getEndTime().getTime() - call.getStartTime().getTime();
                System.out.println("Call Duration: " + TimeUnit.MILLISECONDS.toMinutes(callDuration) + " minutes\n");
            }
            else {
                System.out.println(call.toString());
            }
        }

    }

    /**
     * This method calls the textdumper and textparser class to write or read file respectively.
     *
     * @param call     call details
     * @param bill     bill containing several call details
     * @param fileName name of the file passed as argument
     */
      private static void GotoTextFile(PhoneCall call, PhoneBill bill, String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                {
                    try
                    {
                        String inputCustomerName = bill.getCustomer();
                        TextParser tp = new TextParser(fileName);
                        PhoneBill bill2 = tp.parse();
                        if (bill2.getCustomer() == null)
                        {
                            bill2.setCustomerName(inputCustomerName);
                        }
                        else
                        {
                            if (!bill2.getCustomer().equals(inputCustomerName))
                            {
                                System.err.println("Customer names do not match! Exiting Program");
                                System.exit(1);
                            }
                        }
                    }
                    catch (ParserException p)
                    {
                        System.out.println(p);
                    }
                }
                TextParser parser = new TextParser(fileName);
                //parser.parse();
                AbstractPhoneBill parseBill = parser.parse();
              // parseBill.addPhoneCall(call);
               //System.out.println(parseBill);
                TextDumper dumper = new TextDumper(fileName);
                dumper.dump(bill);
            }
            else {
                TextDumper dumper = new TextDumper(fileName);
                dumper.dump(bill);
                TextParser parser = new TextParser(fileName);
                parser.parse();
            }
        } catch (ParserException | IOException ex) {
            System.out.println("File not found " + fileName);
        }

    }

    /**
     * This method checks the date and time String entered by command line.
     *
     * @param datetime The String which needs to be validated.
     * @return The validated String.
     */
   public static String IsvalidTime(String datetime) throws ParseException {
        String date = null;
        /* Create Date object
         * parse the string into date

         */
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
            Date depdate = sdf.parse(datetime);
            date = sdf.format(depdate);

        } catch (ParseException e) {
            System.err.println("Please enter the date and time in the given format: mm/dd/yyyy HH:mm");
            System.exit(1);

        }

        return date;
    }

    /**
     * This method checks the String of Phone Number entered by command line.
     *
     * @return The validated String.
     **/
   public static String IsvalidPhoneNumber(String number) {
        if (!number.matches("\\d{3}-\\d{3}-\\d{4}")) {
            System.err.println("Please enter a valid phone number");
            System.exit(1);
        }
        return number;
    }


    private static void ReadMe() {
        System.out.println("This is project1: Designing a phoneBill application \n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n" +
                "The project basically designs a phonebill for an individual. It consists of three files: " +
                "PhoneBill.java, PhoneCall.java and Project1.java.\n" +
                "PhoneBill.java file maintains Customer name and PhoneCall.java file has CallerNumber, Callee Number," +
                "Call startdate and time and Call enddate and time. \n" +
                "Project1.java file passes the arguments through command line parsing.");
    }

}