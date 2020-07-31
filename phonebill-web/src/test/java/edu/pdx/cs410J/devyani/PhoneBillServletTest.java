package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneBill;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;


import static edu.pdx.cs410J.devyani.PhoneBillServlet.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class PhoneBillServletTest {

  //private String CUSTOMER_PARAMETER;

  @Test
  public void requestWithNoCustomerReturnMissingParameter() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter("customer"));
  }

  @Test
  public void requestCustomerWithNoPhoneBillReturnsNotFound() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    String customerName = "Dave";
    when(request.getParameter("customer")).thenReturn(customerName);
    HttpServletResponse response = mock(HttpServletResponse.class);
    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneBillForCustomer(customerName));

  }

 @Test
  public void addPhoneCallToPhoneBill() throws ServletException, IOException{
   PhoneBillServlet servlet = new PhoneBillServlet();
    String customer = "Customer";
    String callerPhoneNumber = "503-123-4567";
    String calleePhoneNumber = "999-123-2399";
    String start = "12/12/2001 12:12 am";
    String end = "12/12/2001 12:15 am";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
    when(request.getParameter(CALLER_NUMBER_PARAMETER)).thenReturn(callerPhoneNumber);
    when(request.getParameter(CALLEE_NUMBER_PARAMETER)).thenReturn(calleePhoneNumber);
    when(request.getParameter(START_DATE_PARAMETER)).thenReturn(start);
    when(request.getParameter(END_DATE_PARAMETER)).thenReturn(end);

    HttpServletResponse response = mock(HttpServletResponse.class);
    //PrintWriter pw = mock(PrintWriter.class);

   // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    //verify(pw, times(0)).println(Mockito.any(String.class));
    verify(response).setStatus(HttpServletResponse.SC_OK);

    PhoneBill phoneBill = servlet.getPhoneBill(customer);
    assertThat(phoneBill, notNullValue());
    assertThat(phoneBill.getCustomer(), equalTo(customer));
    PhoneCall phoneCall = phoneBill.getPhoneCalls().iterator().next();
    assertThat(phoneCall.getCaller(), equalTo(callerPhoneNumber));
    assertThat(phoneCall.getCallee(), equalTo(calleePhoneNumber));
    assertThat(stringWriter.toString(), containsString(""));
  }

    @Test
    public void addPhoneCallToPhoneBillWithInvalidCallerNumber() throws ServletException, IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();
        String customer = "Customer";
        String callerPhoneNumber = "abcd";
        String calleePhoneNumber = "999-123-2399";
        String start = "12/12/2001 12:12 am";
        String end = "12/12/2001 12:15 am";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
        when(request.getParameter(CALLER_NUMBER_PARAMETER)).thenReturn(callerPhoneNumber);
        when(request.getParameter(CALLEE_NUMBER_PARAMETER)).thenReturn(calleePhoneNumber);
        when(request.getParameter(START_DATE_PARAMETER)).thenReturn(start);
        when(request.getParameter(END_DATE_PARAMETER)).thenReturn(end);

        HttpServletResponse response = mock(HttpServletResponse.class);
        //PrintWriter pw = mock(PrintWriter.class);

        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);

        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void addPhoneCallToPhoneBillWithInvalidCalleeNumber() throws ServletException, IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();
        String customer = "Customer";
        String calleePhoneNumber = "abcd";
        String callerPhoneNumber = "999-123-2399";
        String start = "12/12/2001 12:12 am";
        String end = "12/12/2001 12:15 am";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
        when(request.getParameter(CALLER_NUMBER_PARAMETER)).thenReturn(callerPhoneNumber);
        when(request.getParameter(CALLEE_NUMBER_PARAMETER)).thenReturn(calleePhoneNumber);
        when(request.getParameter(START_DATE_PARAMETER)).thenReturn(start);
        when(request.getParameter(END_DATE_PARAMETER)).thenReturn(end);

        HttpServletResponse response = mock(HttpServletResponse.class);
        //PrintWriter pw = mock(PrintWriter.class);

        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);

        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void addPhoneCallToPhoneBillWithInvalidstartDateTime() throws ServletException, IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();
        String customer = "Customer";
        String calleePhoneNumber = "111-111-2222";
        String callerPhoneNumber = "999-123-2399";
        String start = "abcd";
        String end = "12/12/2001 12:15 am";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
        when(request.getParameter(CALLER_NUMBER_PARAMETER)).thenReturn(callerPhoneNumber);
        when(request.getParameter(CALLEE_NUMBER_PARAMETER)).thenReturn(calleePhoneNumber);
        when(request.getParameter(START_DATE_PARAMETER)).thenReturn(start);
        when(request.getParameter(END_DATE_PARAMETER)).thenReturn(end);

        HttpServletResponse response = mock(HttpServletResponse.class);

        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);

        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void addPhoneCallToPhoneBillWithInvalidendDateTime() throws ServletException, IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();
        String customer = "Customer";
        String calleePhoneNumber = "111-111-2222";
        String callerPhoneNumber = "999-123-2399";
        String end = "abcd";
        String start = "12/12/2001 12:15 am";

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(CUSTOMER_PARAMETER)).thenReturn(customer);
        when(request.getParameter(CALLER_NUMBER_PARAMETER)).thenReturn(callerPhoneNumber);
        when(request.getParameter(CALLEE_NUMBER_PARAMETER)).thenReturn(calleePhoneNumber);
        when(request.getParameter(START_DATE_PARAMETER)).thenReturn(start);
        when(request.getParameter(END_DATE_PARAMETER)).thenReturn(end);

        HttpServletResponse response = mock(HttpServletResponse.class);

        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);

        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }




/**  @Test
  public void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    int expectedWords = 0;
    verify(pw).println(Messages.formatWordCount(expectedWords));
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void addOneWordToDictionary() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String word = "TEST WORD";
    String definition = "TEST DEFINITION";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn(word);
    when(request.getParameter("definition")).thenReturn(definition);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    assertThat(servlet.getDefinition(word), equalTo(definition));
  }*/

}
