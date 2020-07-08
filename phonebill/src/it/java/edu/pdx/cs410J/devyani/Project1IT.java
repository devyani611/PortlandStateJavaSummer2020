package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

    /**
     * Test the method with just one argument
     */
    @Test
    public void TestWithOneArgument(){
        MainMethodResult result = invokeMain("Devyani");
        assertEquals(Integer.valueOf(1), result.getExitCode());
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Caller Number"));
    }

    /**
     * Test the method with two arguments
     */
    @Test
    public void testWithTwoArguments(){
        MainMethodResult result = invokeMain("devyani","888-989-3003");
        assertEquals(Integer.valueOf(1), result.getExitCode());
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Callee Number"));
    }

    /**
     * Test the method with three arguments
     */
    @Test
    public void testWiththreeArguments(){
        MainMethodResult result = invokeMain("Devyani","888-989-3003","877-989-3003");
        assertEquals(Integer.valueOf(1), result.getExitCode());
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Call start date"));
    }

    /**
     * Test the method with four arguments
     */
    @Test
    public void testWithFourArguments(){
        MainMethodResult result = invokeMain("Devyani","888-989-3003","877-989-3003","12/23/2001");
        assertEquals(Integer.valueOf(1), result.getExitCode());
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Call start time"));
    }

    /**
     * Test the method for invalid callee number
     */
    @Test
    public void testforInvalidCalleeNumber(){
        MainMethodResult result = invokeMain("-print","devyani","989-000-9999","123456","12/15/2001","12:39","12/12/2001","12:45");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter a valid phone number"));
    }

    /**
     * Test the method for invalid start time
     */
    @Test
    public void testforInvalidStartTime(){
        MainMethodResult result = invokeMain("-print","devyani","989-000-9999","989-109-6789","12/15/2001","jane","12/12/2001","12:45");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the following format: MM/DD/yyyy HH:mm"));
    }

    /**
     * Test the method for invalid end time.
     */
    @Test
    public void testforInvalidEndTime(){
        MainMethodResult result = invokeMain("-print","devyani","989-000-9999","989-109-6789","12/15/2001","12:39","12/12/2001","jane");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the following format: MM/DD/yyyy HH:mm"));
    }

    /**
     * Test the method for invalid start date.
     */
    @Test
    public void testforInvalidStartDate(){
        MainMethodResult result = invokeMain("-print","devyani","989-000-9999","989-109-6789","1234","12:39","12/12/2001","12:45");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the following format: MM/DD/yyyy HH:mm"));
    }

    /**
     * Test the method for invalid end date.
     */
    @Test
    public void testforInvalidEndDate(){
        MainMethodResult result = invokeMain("-print","devyani","989-000-9999","989-109-6789","12/15/20","12:39","2001/12/31","jane");
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter the date and time in the following format: MM/DD/yyyy HH:mm"));
    }

    /**
     * Test the method with "-README" argument
     */
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
        MainMethodResult result = invokeMain("-README","-print","devyani","989-000-9999","989-109-6789","12/15/20","12:39","2001/12/31","12:45");
        String expected = "This is project1: Designing a phoneBill application \n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n" +
                "The project basically designs a phonebill for an individual. It consists of three files: " +
                "PhoneBill.java, PhoneCall.java and Project1.java.\n" +
                "PhoneBill.java file maintains Customer name and PhoneCall.java file has CallerNumber, Callee Number," +
                "Call startdate and time and Call enddate and time. \n" +
                "Project1.java file passes the arguments through command line parsing.";
        assertThat(result.getTextWrittenToStandardOut(), containsString(expected));
    }

}