package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return this.caller;
    }

    @Override
    public String getCallee() {
        return this.callee;
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

    /**
     *
     * @param pc
     * @return
     */
    @Override
    public int compareTo(PhoneCall pc) {
        int value1 = this.getCaller().compareTo(pc.getCaller());
        if (value1 == 0) {
            int value2 = pc.getStartTimeString().compareTo(this.getStartTimeString());
            return value2;
        }
        return value1;
    }
}
