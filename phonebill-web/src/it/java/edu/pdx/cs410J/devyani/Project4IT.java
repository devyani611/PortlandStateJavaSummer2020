package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.InvokeMainTestCase;
//import edu.pdx.cs410J.UncaughtExceptionInMain;
//import edu.pdx.cs410J.devyani.PhoneBillRestClient.PhoneBillRestException;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

 /**   @Test
    public void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }
*/
    @Test
    public void test0NoCommandLineArguments()  {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }


   @Test
    public void test2EmptyServer() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        //String out = result.getTextWrittenToStandardOut();
        //assertThat(out, out, containsString(Messages.getMappingCount(0)));
    }

  /**  @Test(expected = PhoneBillRestClient.PhoneBillRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "devyani";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);

        } catch (UncaughtExceptionInMain ex) {
            throw ex.getCause();
        }
    }*/

    @Test
    public void test3AddPhoneBill() {
        MainMethodResult result = invokeMain( Project4.class, "-host", HOSTNAME,"-port", PORT,"devyani","111-111-1111","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM" );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));

       // result = invokeMain( Project4.class, HOSTNAME, PORT, word );
       // out = result.getTextWrittenToStandardOut();
       // assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));

        //result = invokeMain( Project4.class, HOSTNAME, PORT );
        //out = result.getTextWrittenToStandardOut();
        //assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
    }

    @Test
    public void test4SearchWithCustomerNameStartDateTimeMeridianEndDateTimeMeridian() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-search","devyani","12/12/2001","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test4AddPhoneBillWithouthostvariable() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  HOSTNAME,"-port", PORT,"devyani","111-111-1111","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test4AddPhoneBillWithoutportvariable() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-111-1111","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test5AddPhoneBillWithoutcustomerName() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"111-111-1111","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test6AddPhoneBillWithoutcallerNumber() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test7AddPhoneBillWithoutcalleeNumber() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","12/12/2001","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test8AddPhoneBillWithoutstartDate() throws UncaughtExceptionInMain{
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12:12","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test9AddPhoneBillWithoutstartTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12/12/2001","AM","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test10AddPhoneBillWithoutstartMeridian() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12/12/2001","12:12","12/12/2001","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test11AddPhoneBillWithoutEndDate() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12/12/2001","12:12","AM","12:15","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test12AddPhoneBillWithoutEndTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12/12/2001","12:12","AM","12/12/2001","AM"  );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test13AddPhoneBillWithoutEndMeridian() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2222","12/12/2001","12:12","AM","12/12/2001","12:12");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test14AddPhoneBillWithWrongFormatCallerNumber() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-22xx","222-222-2222","12/12/2001","12:12","AM","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test15AddPhoneBillWithWrongFormatCalleeNumber() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-22","12/12/2001","12:12","AM","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test16AddPhoneBillWithWrongFormatStartDate() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12mm","12:12","AM","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test17AddPhoneBillWithWrongFormatStartTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:nn","AM","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test18AddPhoneBillWithWrongFormatStartMeridian() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:12","abcd","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test19AddPhoneBillWithWrongFormatEndDate() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/20mm","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test20AddPhoneBillWithWrongFormatEndTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test21AddPhoneBillWithWrongFormatEndMeridian() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15","abcd");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test22SearchWithoutCustomerName() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test23SearchWithoutStartDateTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test24SearchWithoutEndDateTime() throws UncaughtExceptionInMain{
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test25SearchWithCustomerNameAndStartDate() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test26SearchWithCustomerNameAndStartDateTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001","12:12");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test27SearchWithCustomerNameAndStartDateTimeMeridian() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001","12:12","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test28SearchWithCustomerNameStartDateTimeMeridianEndDate() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001","12:12","AM","12/12/2001");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test29SearchWithCustomerNameStartDateTimeMeridianEndDateTime() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME, PORT,"-search","devyani","12/12/2001","12:12","AM","12/12/2001","12:15");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

 /**  @Test
    public void test30SearchWithCustomerNameStartDateTimeMeridianEndDateTimeMeridian() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-search","devyani","12/12/2001","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }
*/
    @Test
    public void test31OnlyReadMeWorks() {
        MainMethodResult result = invokeMain( Project4.class,  "-README");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test32ReadMewithhostandport() {
        MainMethodResult result = invokeMain( Project4.class,  "-host","-README");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test33ReadMewithallarguments() {
        MainMethodResult result = invokeMain( Project4.class,  "-README","-host",HOSTNAME, PORT,"devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test34WithPrintAndAllArguments() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test35WithPrintAndlessArguments() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test36WithPrintAndMoreArguments() throws UncaughtExceptionInMain {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15","AM","abcd");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test37WithPrintAndinvalidCallerNumber() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-2-2222","222-222-2211","12/12/2001","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test38WithPrintAndinvalidStartDate() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-222-2222","222-222-2211","12/12bbb","12:12","AM","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test39WithPrintAndinvalidEndDate() {
        MainMethodResult result = invokeMain( Project4.class,  "-host",HOSTNAME,"-port", PORT,"-print","devyani","111-222-2222","222-222-2211","12/12/2001","12:12","AM","12/12/nm","12:15","AM");
        assertThat(result.getTextWrittenToStandardOut(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(""));
    }


}
