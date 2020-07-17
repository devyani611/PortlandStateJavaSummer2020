package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * A unit test for code in the Project1 lass.
 */
public class Project2Test extends InvokeMainTestCase {
  /**
   * Invokes the main method of {@link Project2} with the given arguments.

 private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project2.class, args );
  }


  /**
   * Test the isinvalidPhoneNumber method
   */
  @Test
  public void testIsvalidPhoneNumberString(){
    String number = "333-333-4444";
    assertThat(Project2.IsvalidPhoneNumber(number),containsString("333-333-4444"));
}



}
