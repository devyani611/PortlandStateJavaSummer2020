package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class PhoneBill extends AbstractPhoneBill {
    String customer;

    public ArrayList<PhoneCall> phonecalls;


    public PhoneBill(String customer) {
        this.customer = customer;
        this.phonecalls = new ArrayList<>();
    }

    @Override
    public String getCustomer() {
        return this.customer;
    }

    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        if (phonecalls == null) {
            phonecalls = new ArrayList<>();
        }
        phonecalls.add((PhoneCall) call);
    }


    @Override
    public ArrayList<PhoneCall> getPhoneCalls() {
        Collections.sort(phonecalls);
        return phonecalls;
    }


    public ArrayList<PhoneCall> getPhoneCallsWithinRange(String startdate, String enddate) {
        ArrayList<PhoneCall> rangeCalls = new ArrayList<>();

        for (PhoneCall call : phonecalls) {
            String callStart = call.getStartTimeString();
            if (callStart.compareTo(startdate) >= 0 && callStart.compareTo(enddate) <= 0) {
                rangeCalls.add(call);
            }
        }
        return rangeCalls;
    }


    // @Override
    //public static Collection<PhoneBill> getPhoneCalls() {
      //  return null;
    //}

}
