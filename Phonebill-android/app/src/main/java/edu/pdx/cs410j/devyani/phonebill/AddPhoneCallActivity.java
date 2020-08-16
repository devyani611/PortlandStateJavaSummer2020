package edu.pdx.cs410j.devyani.phonebill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPhoneCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phonecall);
    }


    /**
     * Get user input for adding a phone call to phone bill
     * @param view
     */
    public void GetCallData(View view) throws ParseException {
        EditText editText = findViewById(R.id.customer_name);
        String customer = editText.getText().toString();
        editText = findViewById(R.id.caller_number);
        String callerNumber = editText.getText().toString();
        editText = findViewById(R.id.callee_number);
        String calleeNumber = editText.getText().toString();
        editText = findViewById(R.id.start);
        String startDateTime = editText.getText().toString();
        editText = findViewById(R.id.end);
        String endDateTime = editText.getText().toString();

        // Validate the customer name
        if(!isValidCustomerName(customer)){
            showPopUpMessage(view, "Customer name is either null or empty..!!");
            return;
        }

        //Validate the caller number
        if(!isValidPhoneNumber(callerNumber)){
            showPopUpMessage(view, "please provide caller's number in the format: xxx-xxx-xxxx");
            return;
        }

        // Validate the callee number
        if(!isValidPhoneNumber(calleeNumber)){
            showPopUpMessage(view, "please provide callee's number in the format: xxx-xxx-xxxx");
            return;
        }

        // validate the start date and time
        if(!isValidDateTime(startDateTime)){
            showPopUpMessage(view, "Please enter the start date and time in the given format: mm/dd/yyyy HH:mm a");
            return;
        }

        // validate the end date and time
        if(!isValidDateTime(endDateTime)){
            showPopUpMessage(view, "Please enter the end date and time in the given format: mm/dd/yyyy HH:mm a");
            return;
        }

        // create a phone bill and add the phone call to it.
        PhoneBill bill = new PhoneBill(customer);
        PhoneCall call = null;
        List<PhoneCall> phoneCallList = new ArrayList<PhoneCall>();
        call = new PhoneCall(callerNumber, calleeNumber, startDateTime, endDateTime);
        //System.out.println("first call object "+ call);

        //Snackbar mySnackbar = Snackbar.make(view, "Added "+ call.toString() ,5000);
       // mySnackbar.show();

        // add the call to the arrayList
        phoneCallList.add(call);
        bill.setPhonecalls((ArrayList<PhoneCall>) phoneCallList);

        //check if the file with this customer name exists, if yes then add file data to above phonebill object
        File file = new File(getFilesDir(), customer);
        String line;
        BufferedReader br;


        try {
            if (file.exists() == false) {
                file.createNewFile();
                Log.e("AddPhonecall", "New file created.");
                showPopUpMessage(view, "New file created successfully..!!");
                TextDumper dumper = new TextDumper(file);
                dumper.dump(bill);
            }
            else {

                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    //System.out.println("reading by bufferreader " +line);
                    String[] args = line.split(";");

                    if (args[0] == null || args[0].trim().equals("")) {
                       Log.e("AddPhonecall", "New file created.");
                        //showPopUpMessage(view, "New file created successfully..!!");
                        TextDumper dumper = new TextDumper(file);
                        dumper.dump(bill);
                        return;
                    }

                    //check the total number of arguments
                    if(args.length != 9) {
                        br.close();
                        showPopUpMessage(view, "File is Malformatted. Incorrect number of arguments found!");
                        return;
                    }

                    // check if the customer name matches
                    if(!bill.getCustomer().equalsIgnoreCase(args[0])) {
                        br.close();
                       // Log.e("AddPhonecall", "Customer name does not match with the file.");
                        showPopUpMessage(view, "Customer name does not match with the file.");
                        return;
                    }

                    //Create the phone call object
                    String caller = args[1];
                    String callee = args[2];
                    String start = args[3]+" "+args[4]+" "+args[5];
                    String end = args[6]+" "+args[7]+" "+args[8];

                    PhoneCall pc = new PhoneCall(caller, callee, start, end);

                    //add the phonecall to the bill
                    bill.addPhoneCall(pc);
                }

                br.close();
                TextDumper dumper = new TextDumper(file);
                dumper.dump(bill);

                }

            // create a pop up message here
            Snackbar mySnackbar = Snackbar.make(view, "Added "+ call.toString() ,5000);
            mySnackbar.show();

            }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates the customer name
     * @param customer name of the customer
     * @return true/false based on the provided input
     */
   public static boolean isValidCustomerName(String customer) {
        if(customer == null || customer.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * validates the given date and time
     * @param DateTime start or end datetime
     * @return true/false based on the provided input
     */
    public static boolean isValidDateTime(String DateTime) throws ParseException {
        // null and empty check
        if (DateTime == null || DateTime.isEmpty())
            return false;

        String[] depart = DateTime.split(" ");

        // length check
        if (depart.length != 3)
            return false;

        String Date = depart[0];
        String Time = depart[1];
        String Meridian = depart[2];

        // check if date is valid
        if (!Date.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            return false;
        }

        // check if time is valid
        if (!Time.matches("\\d{1,2}:\\d{1,2}")) {
            return false;
        }

        // check if am or pm is mentioned correctly
        if (!(Meridian.equalsIgnoreCase("am") || Meridian.equalsIgnoreCase("pm"))) {
            return false;
        }

        return true;
    }


    /**
     * validates given caller/callee phone number
     * @param number caller/callee number
     * @return true/false based on the provided input
     */
    private boolean isValidPhoneNumber(String number) {
        if (!number.matches("\\d{3}-\\d{3}-\\d{4}")) {
            return false;
        }
        return true;
    }

    private void showPopUpMessage(View view, String message) {
        // create a pop up message here
        Snackbar mySnackbar = Snackbar.make(view, message, 5000);
        mySnackbar.show();
    }
}
