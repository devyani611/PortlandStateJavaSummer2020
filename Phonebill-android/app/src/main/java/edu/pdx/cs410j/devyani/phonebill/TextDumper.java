package edu.pdx.cs410j.devyani.phonebill;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextDumper {
    private File file;


    public TextDumper(File file) {
        this.file = file;
    }


    /**
     * Method to dump the object into a file
     * @param pb Phonebill object
     * @throws IOException
     */
    public void dump(PhoneBill pb) throws IOException {
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            // first empty the file
            bw.write("");
            ArrayList<PhoneCall> calls = pb.getPhoneCalls();
            Collections.sort(calls);
            //System.out.println("in text dumper "+ calls);
            for(int i = 0; i < calls.size(); i++) {
                //System.out.println(pb.getCustomer()+";"+(pb).getPhoneCalls().get(i).getCallDetails());
                bw.append(pb.getCustomer()+";"+(pb).getPhoneCalls().get(i).getCallDetails());
                if(i < pb.getPhoneCalls().size()-1)
                    bw.newLine();
            }
            System.out.println("File written successfully!");
            bw.close();
        } catch (IOException e) {
            Log.e("AddPhonecall", e.getMessage());
        }
    }
}