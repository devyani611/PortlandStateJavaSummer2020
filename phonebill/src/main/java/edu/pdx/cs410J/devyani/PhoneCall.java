package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class inherits the existing AbstractPhoneCall class and its methods.
 */

public class PhoneCall extends AbstractPhoneCall {
  /**
   * Declare variables to store details of a phonecall
   */
  String callerNumber;
  String calleeNumber;
  String start;
  String  end;

  /**
   * Creating an empty phone call
   */
  public PhoneCall() {
    this.callerNumber = null;
    this.calleeNumber = null;
    this.start = null;
    this.end = null;
  }

  /**
   * Constructor to initialize various variables
   * @param caller Phone number of caller
   * @param callee Phone number of callee
   * @param start start date and time of the call
   * @param end end date and time of the call
   */
  public PhoneCall(String caller, String callee, String start, String end) {
    this.callerNumber = caller;
    this.calleeNumber = callee;
    this.start = start;
    this.end = end;
  }

  /**
   * This method validates the entered caller number
   * @return returns the validated number.
   */
  @Override
  public String getCaller() {
    return this.callerNumber;
  }

  /**
   * This method validates the entered callee number
   * @return returns the validated number
   */
  @Override
  public String getCallee() {

    return this.calleeNumber;
  }

  /**
   * This method returns the entered start datetime into date format.
   * @return Date startdatetime.
   */
  @Override
  public Date getStartTime() {
    DateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    /* Create Date object
     * parse the string into date */
    Date startDateTime = null;
    try
    {
      startDateTime = sdformat.parse(this.start);
    }
    catch (ParseException e)
    {
      System.err.println("Please enter the date and time in the following format: MM/DD/yyyy HH:mm");
      System.exit(1);
    }
    return startDateTime;
  }

  /**
   * This method returns the entered end datetime into date format.
   * @return Date enddatetime.
   */
  @Override
  public Date getEndTime(){
    DateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    /* Create Date object
     * parse the string into date
*/
    Date endDateTime = null;
    try
    {
      endDateTime = sdformat.parse(this.end);
    }
    catch (ParseException e)
    {
      System.err.println("Please enter the date and time in the following format: MM/DD/yyyy HH:mm");
      System.exit(1);
    }
    return endDateTime;
  }

  /**
   * This method returns the String date.
   * @return start date and time
   */
  @Override
  public String getStartTimeString() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    Date date = getStartTime();
    start = df.format(date);
    return start;
  }

  /**
   * This method returns the String date.
   * @return end date and time
   */
  @Override
  public String getEndTimeString() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    Date date = getEndTime();
    end = df.format(date);
    return end;
  }

}
