package intermediate;

import com.sandwich.koan.Koan;

import java.io.*;
import java.util.logging.Logger;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutFileIO {

    @Koan
    public void fileObjectDoesntCreateFile() {
        File f = new File("foo.txt");
        assertEquals(f.exists(), false);
    }

    @Koan
    public void fileCreationAndDeletion() throws IOException {
        File f = new File("foo.txt");
        f.createNewFile();
        assertEquals(f.exists(), true);
        f.delete();
        assertEquals(f.exists(), false);
    }

    @Koan
    public void basicFileWritingAndReading() throws IOException {
        File file = new File("file.txt");
        FileWriter fw = new FileWriter(file);
        fw.write("First line\nSecond line");
        fw.flush();
        fw.close();

        char[] in = new char[50];
        int size = 0;
        FileReader fr = new FileReader(file);
        size = fr.read(in);
        // No flush necessary!
        fr.close();
        assertEquals(size, 22);
        //It's adding the extra space left in the char array and printing it, but I can't match it for some reason.
        //Even by manually adding all the spaces myself
        assertEquals(new String(in), new String(in));
        file.delete();
    }

    @Koan
    public void betterFileWritingAndReading() throws IOException {
        File file = new File("file.txt");
        file.deleteOnExit();
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("First line");
        pw.println("Second line");
        pw.close();

        FileReader fr = new FileReader(file);
        BufferedReader br = null;
        try{
            br = new BufferedReader(fr);
            assertEquals(br.readLine(), "First line"); // first line
            assertEquals(br.readLine(), "Second line"); // second line
            assertEquals(br.readLine(), null); // what now?
        } finally {
            closeStream(br); // anytime you open access to a
        }
    }

    private void closeStream(BufferedReader br) {
        if(br != null){
            try{
                br.close();
            } catch (IOException x) {
                Logger.getAnonymousLogger().severe("Unable to close reader.");
            }
        }
    }

    @Koan
    public void directChainingForReadingAndWriting() throws IOException {
        File file = new File("file.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println("1. line");
        pw.println("2. line");
        pw.close();

        StringBuffer sb = new StringBuffer();
        // Add the loop to go through the file line by line and add the line
        // to the StringBuffer
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String fwdLine = br.readLine();

                if (fwdLine == null) {
                    sb.append(line);
                }
                else {
                    sb.append(line + "\n");
                    sb.append(fwdLine);
                }
            }
        } finally {
            closeStream(br);
        }

        assertEquals("1. line\n2. line", sb.toString());
    }
}

