package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PrettyPrinter {
    /**
     * Holds the list of phone calls from the phone bill.
     */
    private ArrayList<PhoneCall> callList;
    /**
     * Holds the length of the phone calls in minutes.
     */
    private long duration;
    /**
     * Holds the start date of the phone call.
     */
    private String startDate;
    /**
     * Holds the end date of the phone call.
     */
    private String endDate;

    /**
     * Dump the phone call information
     * @param call
     * @return
     * @throws IOException
     */
    public String dump(AbstractPhoneCall call) throws IOException {
        duration = call.getEndTime().getTime() - call.getStartTime().getTime();
        duration = TimeUnit.MILLISECONDS.toMinutes(duration);
        startDate = DateFormat.getDateInstance(DateFormat.LONG).format(call.getStartTime());
        endDate = DateFormat.getDateInstance(DateFormat.LONG).format(call.getEndTime());
        return String.format("You had a call from %s to your phone %s on %s to %s that lasted %s minutes.\n", call.getCallee(),
                call.getCaller(), startDate, endDate, duration);
    }
}
