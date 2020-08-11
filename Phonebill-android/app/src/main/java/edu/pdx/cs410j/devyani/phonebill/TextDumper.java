package edu.pdx.cs410j.devyani.phonebill;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            List<PhoneCall> calls = pb.getPhoneCalls();
            Collections.sort(calls);
            for(int i = 0; i < calls.size(); i++) {
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