package edu.pdx.cs410j.devyani.phonebill;

import java.util.ArrayList;
import java.util.Collections;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneBill extends AbstractPhoneBill{

    private String customer;
    public ArrayList<PhoneCall> phonecalls;

    public PhoneBill(String customer) {
        this.customer = customer;
        this.phonecalls = new ArrayList<>();
    }

    @Override
    public String getCustomer() {
        return customer;
    }

   /** public void setCustomer(String customer) {
        this.customer = customer;
    }*/

    @Override
    public void addPhoneCall(AbstractPhoneCall call) {
        phonecalls.add((PhoneCall) call);

    }

    public void setPhonecalls(ArrayList<PhoneCall> phonecalls) {
        this.phonecalls = phonecalls;
    }

    @Override
    public ArrayList<PhoneCall> getPhoneCalls() {
        Collections.sort(phonecalls);
        return phonecalls;
    }



}
