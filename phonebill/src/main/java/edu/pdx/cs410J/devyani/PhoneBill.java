package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
     * Creates a new <code>PhoneBill</code> object using only the customers name.
     *
     * @param customer The name of the customer that the list of phone calls belongs to.
     */
    public PhoneBill(String customer) {
        this.customer = customer;
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
        if (phonecalls == null) {
            phonecalls = new ArrayList<>();
        }
        phonecalls.add((PhoneCall) abstractPhoneCall);

    }
    /**
     * Overrides <code>AbstractPhoneBill</code>'s abstract method <code>getPhoneCalls</code> to return the sorted list of
     * phone calls.
     *
     * @return The list of phone calls as a ArrayList.
     */
    @Override
    public ArrayList<PhoneCall> getPhoneCalls() {
        Collections.sort(phonecalls);
        return phonecalls;
    }

    /**
     *  This method returns a Collection of PhoneCalls contained in the PhoneBill object
     *  @return a Collection of PhoneCall objects

    @Override
    public Collection getPhoneCalls() {

        return phonecalls;
    }*/
}