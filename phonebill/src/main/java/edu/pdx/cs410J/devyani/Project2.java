package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project2 {

    public static void main(String args[]) throws ParseException {
        PhoneCall call = new PhoneCall();
        PhoneBill bill = new PhoneBill();

        String fileName = null;

        /**
         * Booleans to print and to read/write text file
         */
        boolean print = false;
        boolean readTextFile = false;
        boolean writeTextFile = false;

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
                    System.err.println("Missing Caller Number");
                    System.exit(1);
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
                    System.err.println("Missing call end date");
                    System.exit(1);
                    break;

                case 6:
                    System.err.println("Missing call end time");
                    System.exit(1);
                    break;
                case 7:
                    if (args[0].equals("-print")) {
                        System.err.println("Missing call end time");
                        System.exit(1);
                    } else if (args[0].equals("-textFile")) {
                        System.err.println("Missing call end date");
                        System.exit(1);
                    }
                    break;
                case 8:
                    if (args[0].equals("-textFile")) {
                        if (args[2].equals("-print")) {
                            System.err.println("Missing call end date");
                            System.exit(1);
                        } else {
                            System.err.println("Missing call end time");
                            System.exit(1);
                        }

                    } else if (args[0].equals("-print")) {
                        if (args[1].equals("-textFile")) {
                            System.err.println("Missing call end date");
                            System.exit(1);
                        } else {
                            print = true;
                            //i = 1;
                        }
                    } else {
                        System.err.println("please enter valid arguments");
                        System.exit(1);
                    }
                    break;
                case 9:
                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        System.err.println("Missing call end time");
                        System.exit(1);
                    } else if (args[0].equals("-textFile") && args[2].equals("-print")) {
                        System.err.println("Missing call end time");
                        System.exit(1);
                    } else if (args[0].equals("-textFile")) {
                        readTextFile = true;
                        writeTextFile = true;
                        fileName = args[1];
                    } else {
                        System.err.println("please enter valid arguments");
                        System.exit(1);
                    }
                    break;
                default:
                    if (args[0].equals("-print") || args[2].equals("-print")) {
                        if (args[0].equals("-textFile") || args[1].equals("-textFile")) {
                            if (args.length == 10) {
                                print = true;
                                readTextFile = true;
                                writeTextFile = true;

                            } else if (args.length > 10) {
                                System.err.println("Too many arguments, please enter valid arguments");
                                System.exit(1);
                            }
                        }
                    } else if (args[0].equals("-textFile")) {
                        if (!args[2].equals("-print")) {
                            if (args.length > 9) {
                                System.err.println("Too many arguments, please enter valid arguments");
                                System.exit(1);
                            }
                        }
                    } else if (args.length > 7) {
                        System.err.println("Too many arguments, please enter valid arguments");
                        System.exit(1);
                    }
            }

        }
        /**
         * Checks for the position of -print and -textFile and set the value of variable i accordingly
         */

        int i = 0;
        if (args[0].equals("-print") && !args[1].equals("-textFile")) {
            i = 1;
        } else if (args[0].equals("-print") && args[1].equals("-textFile")) {
            fileName = args[2];
            i = 3;
        } else if (args[0].equals("-textFile") && args[2].equals("-print")) {
            fileName = args[1];
            i = 3;
        } else if (args[0].equals("-textFile")) {
            fileName = args[1];
            i = 2;
        }
        /**
         * get all the values of the arguments
         */
        bill.setCustomerName(args[i]);
        bill.customer = bill.getCustomer();
        call.callerNumber = IsvalidPhoneNumber(args[i + 1]);
        call.calleeNumber = IsvalidPhoneNumber(args[i + 2]);
        call.start = IsvalidTime(args[i + 3] + " " + args[i + 4]);
        call.end = IsvalidTime(args[i + 5] + " " + args[i + 6]);

        /**
         * if print is true, output the call details
         */
        if (print) {
            bill.addPhoneCall(call);
            System.out.println(call.toString());
        }

        /**
         * call the method to read/write textfile
         */
        if (writeTextFile == true || readTextFile == true) {
            bill.addPhoneCall(call);
            GotoTextFile(call, bill, fileName);
            System.exit(0);
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
                TextParser parser = new TextParser(fileName);
                bill = (PhoneBill) parser.parse();
                bill.addPhoneCall(call);
                TextDumper dumper = new TextDumper(fileName);
                dumper.dump(bill);
            } else {
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
         * parse the string into date*/
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