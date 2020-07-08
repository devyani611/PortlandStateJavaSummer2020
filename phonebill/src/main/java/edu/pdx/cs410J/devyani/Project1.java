package edu.pdx.cs410J.devyani;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

    //Main method
  public static void main(String[] args) {
      PhoneBill bill = new PhoneBill();
      PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

      /**
       * Checks each given argument and calls Readme method, is any of the argument is "-README".
       */
      if (Arrays.stream(args).anyMatch(s -> s.equals("-README"))) {
          ReadMe();
          System.exit(0);
      }

      /**
       * If there is no readme, execute the switch statements.
       * The program won't check for any errors in the given argument unless all the arguments are given.
       */
      else {
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

                  //Assuming first argument will be -print in order to print the string
              case 7:
                  if (args[0].equals("-print")) {
                      System.err.println("Missing call end time");
                      System.exit(1);
                  } else {
                      System.exit(0);
                  }

              default:
                  if (args.length > 9) {
                      System.err.println("Too many arguments, please enter valid arguments");
                  } else {
                      int i = 0;
                      if (args[0].equals("-print")) {
                          i = 1;
                      }
                      bill.setCustomerName(args[i]);
                      bill.customer = bill.getCustomer();
                      call.callerNumber = IsvalidPhoneNumber(args[i + 1]);
                      call.calleeNumber = IsvalidPhoneNumber(args[i + 2]);
                      call.start = IsvalidTime(args[i + 3] + " " + args[i + 4]);
                      call.end = IsvalidTime(args[i + 5] + " " + args[i + 6]);

                  }

          }
          bill.addPhoneCall(call);
          System.out.println(call.toString());
      }

  }

    /**
   * This method checks the date and time String entered by command line.
   * @param datetime The String which needs to be validated.
   * @return The validated String.
   */
  public static String IsvalidTime(String datetime) {
    int separation = datetime.indexOf(" ");
    String date = datetime.substring(0, separation);
    String time = datetime.substring(separation + 1);
    /* Create Date object
     * parse the string into date
**/
    try {

        DateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date depdate = sdf.parse(date);
        String newdate = DateFormat.getDateInstance(DateFormat.SHORT).format(depdate);
        DateFormat sdf1 = new SimpleDateFormat("hh:mm");
        Date deptime = sdf1.parse(time);
        String newtime = DateFormat.getTimeInstance(DateFormat.SHORT).format(deptime);
        datetime = newdate + " " + newtime;
    }
    catch (ParseException e)
    {
      System.err.println("Please enter the date and time in the following format: MM/DD/yyyy HH:mm");
      System.exit(1);
    }
    return datetime;
  }


/**
   * This method checks the String of Phone Number entered by command line.
   * @return The validated String.

**/
  public static String IsvalidPhoneNumber(String number) {
    if(!number.matches("\\d{3}-\\d{3}-\\d{4}")) {
      System.err.println("Please enter a valid phone number");
      System.exit(1);
    }
   return number;
  }

  /**
   * The method prints the description of the project to the console window.
   */
 private static void ReadMe(){
    System.out.println("This is project1: Designing a phoneBill application \n" +
    "The project is designed by Devyani Shrivastava at Portland State University \n" +
    "The project basically designs a phonebill for an individual. It consists of three files: " +
            "PhoneBill.java, PhoneCall.java and Project1.java.\n" +
   "PhoneBill.java file maintains Customer name and PhoneCall.java file has CallerNumber, Callee Number," +
            "Call startdate and time and Call enddate and time. \n" +
    "Project1.java file passes the arguments through command line parsing.");
  }

}