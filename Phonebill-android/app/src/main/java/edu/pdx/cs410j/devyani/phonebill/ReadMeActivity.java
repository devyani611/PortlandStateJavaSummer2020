package edu.pdx.cs410j.devyani.phonebill;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReadMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_me);

        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText("This is project5: Designing an Android phoneBill application \n\n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n\n" +
                "You can do different activities: Creating a phonebill, add a phonecall to the phonebill, pretty print the phone calls details. \n\n"+
                "You can also search for phonecalls by providing the customer name, start and end time. The matching phonecall details will be printed for you.\n\n"+
                "The caller and callee number should be provided in the format: xxx-xxx-xxxx. \n\n"+
                "The start and end time should be provided in the format: mm/dd/yyyy hh:mm a");
    }
}
