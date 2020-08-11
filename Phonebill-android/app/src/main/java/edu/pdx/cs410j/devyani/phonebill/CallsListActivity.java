package edu.pdx.cs410j.devyani.phonebill;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

public class CallsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_list);

        ListView listview = findViewById(R.id.list_view);
        ArrayAdapter<PhoneCall> adapter = new PhoneCallAdapter(this);
        listview.setAdapter(adapter);

        // read the calls from the temp file
        try {
            File file = new File(getFilesDir(), "tempfile.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            //List<PhoneCall> result = new ArrayList<PhoneCall>();
            while ((line = br.readLine()) != null) {
                String[] args = line.split(";");
                String caller = args[1];
                String callee = args[2];
                String start = args[3]+" "+args[4]+" "+args[5];
                String end = args[6]+" "+args[7]+" "+args[8];

                PhoneCall pc = new PhoneCall(caller, callee, start, end);
                adapter.add(pc);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listview.setAdapter(adapter);
    }

    class PhoneCallAdapter extends ArrayAdapter<PhoneCall> {

        public PhoneCallAdapter(@NonNull Context context) {
            super(context, R.layout.phonecall_view);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View phonecall_View, @NonNull ViewGroup parent) {
            if (phonecall_View == null) {
                phonecall_View = getLayoutInflater().inflate(R.layout.phonecall_view, parent, false);
            }

            PhoneCall call = getItem(position);
            TextView textView = phonecall_View.findViewById(R.id.call_details);
            textView.setText(prettyPrint(call));

            return phonecall_View;
        }
    }

    // pretty print the matched phone call details
    private static String prettyPrint(PhoneCall call) {
        long duration = call.getEndTime().getTime() - call.getStartTime().getTime();
        duration = TimeUnit.MILLISECONDS.toMinutes(duration);
        String startDate = DateFormat.getDateInstance(DateFormat.LONG).format(call.getStartTime());
        String endDate = DateFormat.getDateInstance(DateFormat.LONG).format(call.getEndTime());
        return String.format("You had a call from %s to your phone %s on %s to %s that lasted %s minutes.\n", call.getCallee(),
                call.getCaller(), startDate, endDate, duration);
    }
}
