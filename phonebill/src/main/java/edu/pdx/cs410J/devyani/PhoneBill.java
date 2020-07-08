package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to generate phoneBill for a customer, inherited from Abstract class
 */
public class PhoneBill extends AbstractPhoneBill {

    /**
     * Variables for storing customer name and phone calls records.
     */
    String customer;
    ArrayList<PhoneCall> phonecalls;

    /**
     * Constructor to generate new arrayList to store phonecalls
     */

    public PhoneBill() {
        this.customer = null;
        this.phonecalls = new ArrayList<>();

    }

    /**
     *  Sets the Customer Name field of the PhoneBill object
     *  @param  customerName
     *          The name of the customer for the specified PhoneBill
     */
    public void setCustomerName(String customerName)
    {
        this.customer = customerName;
    }

    /**
     * Method to get customer name
     * @return customer
     */
    @Override
    public String getCustomer() {
        return customer;
    }

    /**
     * Method to add phonecalls to the arrayList, inherited from abstract class
     * @param abstractPhoneCall
     */
    @Override
    public void addPhoneCall(AbstractPhoneCall abstractPhoneCall) {
        phonecalls.add((PhoneCall) abstractPhoneCall);

    }


    /**
     *  This method returns a Collection of PhoneCalls contained in the PhoneBill object
     *  @return a Collection of PhoneCall objects
     */
    @Override
    public Collection getPhoneCalls() {

        return phonecalls;
    }
}
