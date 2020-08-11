package edu.pdx.cs410j.devyani.phonebill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class SearchPhoneCallsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_phonecalls);
    }

    /**
     * Method which gets user input and validates
     * @param view
     */
    public void searchPhoneCalls(View view){
        EditText editText = findViewById(R.id.customer_name);
        String customer = editText.getText().toString();
        editText = findViewById(R.id.start);
        String startDateTime = editText.getText().toString();
        editText = findViewById(R.id.end);
        String endDateTime = editText.getText().toString();

        /**
         * check if the customer name is valid
         */
        if(!AddPhoneCallActivity.isValidCustomerName(customer)){
            showPopUpMessage(view, "Customer value is either null or empty..!!");
            return;
        }

        /**
         * validate the start date and time
         */
        if(!AddPhoneCallActivity.isValidDateTime(startDateTime)){
            showPopUpMessage(view, "Please enter the start date and time in the given format: mm/dd/yyyy HH:mm a");
            return;
        }

        /**
         * validate the end date and time
         */
        if(!AddPhoneCallActivity.isValidDateTime(endDateTime)){
            showPopUpMessage(view, "Please enter the end date and time in the given format: mm/dd/yyyy HH:mm a");
            return;
        }

        // check if the file with the given customer name exists
        File file = new File(getFilesDir(), customer);
        if (file.exists() == false) {
            showPopUpMessage(view, "No phonebill for the given customer..!!");
            return;
        }

        // Find the range phone calls for the given start and end date and time
        PhoneBill pb = new PhoneBill(customer);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            //List<PhoneCall> result = new ArrayList<PhoneCall>();
            while ((line = br.readLine()) != null) {
                String[] args = line.split(";");
                if (args[0] == null || args[0].trim().equals(""))
                    continue;
                if (args.length != 9) {
                    br.close();
                    showPopUpMessage(view, "File is Malformatted. Incorrect number of arguments found!");
                    return;
                }

                //System.out.println(startDateTime);
                //System.out.println(endDateTime);

                //System.out.println("read from file" +args[3] + " "+ args[4]+ " "+ args[5]);
                String callstart = args[3] + " "+ args[4]+ " "+ args[5];
                if (args[0].equalsIgnoreCase(customer) && (callstart.compareTo(startDateTime) >= 0 && callstart.compareTo(endDateTime) <= 0)) {
                    // Create a phonebill object of the matched arguments
                    String caller = args[1];
                    String callee = args[2];
                    String start = args[3] + " " + args[4] + " " + args[5];
                    String end = args[6] + " " + args[7] + " " + args[8];

                    PhoneCall pc = new PhoneCall(caller, callee, start,end);
                    pb.addPhoneCall(pc);
                }
            }

            // If no calls found within the specified time
            if(pb.getPhoneCalls().size() == 0){
                showPopUpMessage(view, "No phonecalls found within the specified range.");
                return;
            }
            br.close();

            // Save the results to a temp file named tempfile.txt
            File tempfile = new File(getFilesDir(), "tempfile.txt");
            TextDumper dumper = new TextDumper(tempfile);
            dumper.dump(pb);

            // view to display the phone calls
            Intent intent = new Intent(this, CallsListActivity.class);
            startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showPopUpMessage(View view, String message) {
        // create a pop up message here
        Snackbar mySnackbar = Snackbar.make(view, message, 5000);
        mySnackbar.show();
    }
}
