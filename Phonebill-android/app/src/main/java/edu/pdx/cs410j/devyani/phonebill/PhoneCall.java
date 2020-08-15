package edu.pdx.cs410j.devyani.phonebill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {
    private String caller;
    private String callee;
    private String start;
    private String end;


    public PhoneCall(String caller, String callee, String start, String end) {
        this.caller = caller;
        this.callee = callee;
        this.start = start;
        this.end = end;
    }

    @Override
    public String getCaller() {
        return caller;
    }

    @Override
    public String getCallee() {
        return callee;
    }

    /**
     * This method returns the entered start datetime into date format.
     * @return Date startdatetime.
     */
    @Override
    public Date getStartTime() {
        DateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
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

        }
        return startDateTime;
    }

    /**
     * This method returns the entered end datetime into date format.
     * @return Date enddatetime.
     */
    @Override
    public Date getEndTime(){
        DateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
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

        }
        return endDateTime;
    }

    /**
     * This method returns the String date.
     * @return start date and time
     */
    @Override
    public String getStartTimeString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
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
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date date = getEndTime();
        end = df.format(date);
        return end;
    }

    /**
     * method to get proper string representation to be written to the file for easy
     * delimition
     * @return String ';' separated representation of phonecall object
     */
    public String getCallDetails() {
        Date startdatetime = this.getStartTime();
        Date enddatetime = this.getEndTime();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        String[] startTimeString = sdf.format(startdatetime).split(" ");
        String[] endTimeString = sdf.format(enddatetime).split(" ");

        return this.getCaller() + ";" + this.getCallee() + ";" + startTimeString[0] + ";" + startTimeString[1] + ";"
                + startTimeString[2] + ";" + endTimeString[0] + ";" + endTimeString[1] + ";" + endTimeString[2];
    }


    /**
     * The method compares the two phonecalls on the basis of caller number and startdatetime
     * @param pc phonecall
     * @return compared value
*/
    @Override
    public int compareTo(PhoneCall pc) {
        int value1 = this.getCaller().compareTo(pc.getCaller());
        if (value1 == 0) {
            int value2 = pc.getStartTime().compareTo(this.getStartTime());
            return value2;
        }
        return value1;
    }
}
