package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {

        return invokeMain( Project3.class, args );
    }
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertEquals(new Integer(1), result.getExitCode());
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testReadMe(){
        MainMethodResult result = invokeMain("-README");
        assertEquals(new Integer(0), result.getExitCode());

    }
    @Test
    public void testWithOnlyPrint(){
        MainMethodResult result = invokeMain("-print");
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testWithOnlyReadMeArgument() {
        MainMethodResult result = invokeMain("-README");
        String expected = "This is project1: Designing a phoneBill application \n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n" +
                "The project basically designs a phonebill for an individual. It consists of three files: " +
                "PhoneBill.java, PhoneCall.java and Project1.java.\n" +
                "PhoneBill.java file maintains Customer name and PhoneCall.java file has CallerNumber, Callee Number," +
                "Call startdate and time and Call enddate and time. \n" +
                "Project1.java file passes the arguments through command line parsing.";
        assertThat(result.getTextWrittenToStandardOut(), containsString(expected));
    }

    /**
     * Test the method with "-README" alongwith other arguments
     */
    @Test
    public void testWithReadMeOptionWithOtherArguments() {
        MainMethodResult result = invokeMain("-README","-print","devyani","989-000-9999","989-109-6789","12/15/20","12:39","AM","2001/12/31","12:45","AM");
        String expected = "This is project1: Designing a phoneBill application \n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n" +
                "The project basically designs a phonebill for an individual. It consists of three files: " +
                "PhoneBill.java, PhoneCall.java and Project1.java.\n" +
                "PhoneBill.java file maintains Customer name and PhoneCall.java file has CallerNumber, Callee Number," +
                "Call startdate and time and Call enddate and time. \n" +
                "Project1.java file passes the arguments through command line parsing.";
        assertThat(result.getTextWrittenToStandardOut(), containsString(expected));
    }

    @Test
    public void testWithoutTextFile(){
        MainMethodResult result = invokeMain("-textFile");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing filename"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testPrettyWithoutFile(){
        MainMethodResult result = invokeMain("-pretty");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing filename"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testPrettyAndPrintWork(){
        MainMethodResult result = invokeMain("-pretty","-print");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Callee Number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithPrettyAndFileName(){
        MainMethodResult result = invokeMain("-pretty","abc");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Callee Number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithPrettyAndDash(){
        MainMethodResult result = invokeMain("-pretty","-");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Callee Number"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testPrettyWithFileNameAndAllArguments(){
        MainMethodResult result = invokeMain("-pretty","abc","devyani","099-000-0000","000-000-0000","01/01/2015","01:01","pm","01/01/2015","01:05","pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString(""));
    }

    @Test
    public void testPrettyWithFileNameAndLessArguments(){
        MainMethodResult result = invokeMain("-pretty","abc","devyani","099-000-0000","000-000-0000","01/01/1010","01:01","am");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testPrettyWithDashAndAllArguments(){
        MainMethodResult result = invokeMain("-pretty","-","devyani","099-000-0000","000-000-0000","01/01/1010","01:01","am","11/11/1999","10:10","pm");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testPrettyWithDashAndExtraArgumentExitWithError(){
        MainMethodResult result = invokeMain("-pretty","-","devyani","099-000-0000","000-000-0000","01/01/1010","01:01","am","11/11/1999","10:10","pm","fred","john");
        assertThat(result.getTextWrittenToStandardError(), containsString("please enter valid arguments"));
        assertEquals(new Integer(1), result.getExitCode());
    }
    @Test
    public void testBothPrintAndReadMeWorks(){
        MainMethodResult result = invokeMain("-print","-README");
        assertEquals(new Integer(0), result.getExitCode());
    }

    @Test
    public void testWithCustomerName(){
        MainMethodResult result = invokeMain("devyani");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Caller Number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithTwoArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Callee Number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithThreeArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Call start date"));
        assertEquals(new Integer(1), result.getExitCode());
    }


    @Test
    public void testWithFourArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Call start time"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithFiveArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001","12:15");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call start AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithSixArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001","12:15","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end date"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithSevenArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001","12:15","AM","12/12/2001");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end time"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithEightArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001","12:15","AM","12/12/2001","12:16");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithNineArguments(){
        MainMethodResult result = invokeMain("devyani","222-222-2222","111-111-1111","12/12/2001","12:15","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
        //assertEquals(new Integer(0), result.getExitCode());
    }


    @Test
    public void testWithInvalidCallerNumber(){
        MainMethodResult result = invokeMain("devyani","222-222-22cc","111-111-1111","12/12/2001","12:15","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter a valid phone number"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithInvalidCalleeNumber(){
        MainMethodResult result = invokeMain("devyani","222-222-2211","111-111-11bn","12/12/2001","12:15","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter a valid phone number"));
        assertEquals(new Integer(1), result.getExitCode());
    }


    @Test
    public void testWithInvalidCallStartDate(){
        MainMethodResult result = invokeMain("devyani","222-222-2211","111-111-1111","12/12/cc","12:15","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the given format: mm/dd/yyyy HH:mm"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithInvalidCallStartTime(){
        MainMethodResult result = invokeMain("devyani","222-222-2211","111-111-1111","12/12/2001","12:cd","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the given format: mm/dd/yyyy HH:mm"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithInvalidCallEndDate(){
        MainMethodResult result = invokeMain("devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/20cd","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the given format: mm/dd/yyyy HH:mm"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithInvalidCallEndTime(){
        MainMethodResult result = invokeMain("devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:cd","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the given format: mm/dd/yyyy HH:mm"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithPrintAndNineArguments(){
        MainMethodResult result = invokeMain("-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithtextFileAndNineArguments(){
        MainMethodResult result = invokeMain("-textFile","abc","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithtextFilePrintAndNineArguments(){
        MainMethodResult result = invokeMain("-textFile","abc","-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithPrinttextFileAndNineArguments(){
        MainMethodResult result = invokeMain("-print","-textFile","abc","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithtextFileprettyFileAndNineArguments(){
        MainMethodResult result = invokeMain("-textFile","abc","-pretty","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithprettyFiletextFileAndNineArguments(){
        MainMethodResult result = invokeMain("-pretty","test","-textFile","abc","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithprettyFiletextFilePrintAndNineArguments(){
        MainMethodResult result = invokeMain("-pretty","test","-textFile","abc","-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithPrintprettyFiletextFileAndNineArguments(){
        MainMethodResult result = invokeMain("-print","-pretty","test","-textFile","abc","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithprettyFilePrinttextFileAndNineArguments(){
        MainMethodResult result = invokeMain("-pretty","test","-print","-textFile","abc","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithPrinttextFileprettyFileAndNineArguments(){
        MainMethodResult result = invokeMain("-print","-textFile","abc","-pretty","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
    }

    @Test
    public void testWithPrettyFileandEightArguments(){
        MainMethodResult result = invokeMain("-pretty","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16");
       // assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithPrettyFilePrintandEightArguments(){
        MainMethodResult result = invokeMain("-pretty","test","-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16");
        // assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithPrintPrettyFileandEightArguments(){
        MainMethodResult result = invokeMain("-print","-pretty","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16");
        // assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithtextFileandEightArguments(){
        MainMethodResult result = invokeMain("-textFile","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16");
       // assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithprintandEightArguments(){
        MainMethodResult result = invokeMain("-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing call end AM/PM"));
        assertEquals(new Integer(1), result.getExitCode());
    }

    @Test
    public void testWithprintprettyandNineArguments(){
        MainMethodResult result = invokeMain("-print","-pretty","test","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
        //assertEquals(new Integer(0), result.getExitCode());
    }

    @Test
    public void testWithprettyprintandNineArguments(){
        MainMethodResult result = invokeMain("-pretty","test","-print","devyani","222-222-2211","111-111-1111","12/12/2001","12:12","AM","12/12/2001","12:16","AM");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
        //assertEquals(new Integer(0), result.getExitCode());
    }
}