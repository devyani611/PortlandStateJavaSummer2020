package edu.pdx.cs410J.devyani;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.family.TextDumper;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{

    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLER_NUMBER_PARAMETER = "callerNumber";
    static final String CALLEE_NUMBER_PARAMETER = "calleeNumber";
    static final String START_DATE_PARAMETER = "start";
    static final String END_DATE_PARAMETER = "end";


    private PhoneBill bill;

    //private final Map<String, String> dictionary = new HashMap<>();

   private final Map<String, PhoneBill> phoneBills = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        /**
         * get the parameters
         */

        String customer = getParameter(CUSTOMER_PARAMETER, request );
        String start = getParameter(START_DATE_PARAMETER, request);
        String end = getParameter(END_DATE_PARAMETER, request);


       if (customer == null) {
           missingRequiredParameter(response, CUSTOMER_PARAMETER);
           return;
       }

         if(customer != null && start== null && end == null) {
             PhoneBill newbill = new PhoneBill(customer);

             if(newbill.getPhoneCalls().size() == 0){
                 response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneBillForCustomer(customer));
             }
             else{
                 DisplayAllPhoneCalls(response);
             }

       }

       else if (customer != null && start != null & end != null) {
           DisplayPhoneCallsWithinSpecifiedRange(start, end, response);
       }

    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String customer = getParameter(CUSTOMER_PARAMETER, request );
        if (customer == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }

        String caller = getParameter(CALLER_NUMBER_PARAMETER, request );
        if (caller != null) {
            //System.err.println("error");
            try {
                ValidatePhoneNumber(caller);
            } catch (ParserException e) {
                writeIncorrectPhoneFormat(CALLER_NUMBER_PARAMETER, e.getMessage(), response);
                return;
            }
        }

        String callee = getParameter(CALLEE_NUMBER_PARAMETER, request );
        if ( callee != null) {
            try {
                ValidatePhoneNumber(callee);
            } catch (ParserException e) {
                writeIncorrectPhoneFormat(CALLEE_NUMBER_PARAMETER, e.getMessage(), response);
                return;
            }
        }

        String startDate = getParameter(START_DATE_PARAMETER, request );
        if ( startDate != null) {
            try {
                startDate = IsValidDateTime(startDate);
            }
            catch (ParseException e) {
                writeIncorrectDateFormat(START_DATE_PARAMETER, e.getMessage(), response);
                return;
            }
        }

        String endDate = getParameter(END_DATE_PARAMETER, request );
        if ( endDate != null) {
            try {
                endDate = IsValidDateTime(endDate);
            }
            catch (ParseException e) {
                writeIncorrectDateFormat(END_DATE_PARAMETER, e.getMessage(), response);
                return;
            }
        }

        if (bill == null) {
            bill = new PhoneBill(customer);
        }
        else if (!bill.getCustomer().equals(customer)) {
            CustomerNameNotMatch(customer, response);
            return;
        }

        //bill = new PhoneBill(customer);
        PhoneCall call = new PhoneCall(caller, callee, startDate, endDate);
        bill.addPhoneCall(call);

        this.phoneBills.put(customer, bill);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.addedCall(call.toString()));
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }


    public static String IsValidDateTime(String datetime) throws ParseException {
        datetime = datetime.replace("pm", "PM").replace("am", "AM");
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date depdate = sdf.parse(datetime);
        date = sdf.format(depdate);

        return date;
    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Method to compare the customer name with the already existing phonebill
     * @param customer customer name
     * @param response http response
     * @throws IOException
     */
    private void CustomerNameNotMatch(String customer, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Customer names do not match..!!");
        pw.flush();

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Giving only customer name pretty prints the phonecalls associated with the customer
     * @param response http response
     * @throws IOException
     */
    private void DisplayAllPhoneCalls(HttpServletResponse response) throws IOException {
        ArrayList<PhoneCall> value = bill.getPhoneCalls();

        //System.out.println("display phone calls value: "+value);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(value != null ? value.size() : 0));

        for (PhoneCall call : value) {
            System.out.println(call);
            pw.println(Messages.printPrettyPhoneCall(call));
        }

        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }


    private void DisplayPhoneCallsWithinSpecifiedRange(String startdate, String enddate, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        try {
            startdate = IsValidDateTime(startdate);
        } catch (ParseException e) {
            writeIncorrectDateFormat(START_DATE_PARAMETER, e.getMessage(), response);
            return;
        }
        try {
            enddate = IsValidDateTime(enddate);
        } catch (ParseException e) {
            writeIncorrectDateFormat(END_DATE_PARAMETER,e.getMessage(), response);
            return;
        }
        ArrayList<PhoneCall> rangeCalls = bill.getPhoneCallsWithinRange(startdate, enddate);

        if (rangeCalls.size() == 0) {
            pw.println("No phone calls found within the specified time period..!!");
           // response.sendError(HttpServletResponse.SC_NO_CONTENT, "No phone calls are found for the given customer" );
        }
        else {
            for (PhoneCall call : rangeCalls) {
                pw.println(Messages.printPrettyPhoneCall(call));
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }

        pw.flush();
       //response.setStatus(HttpServletResponse.SC_OK);
    }


    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    PhoneBill getPhoneBill(String customer) {
       return this.phoneBills.get(customer);
    }


    private void ValidatePhoneNumber(String number) throws ParserException {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(number); //Check if the phone number is in the format xxx-xxx-xxxx

        if (!matcher.matches()) {
            throw new ParserException("Please enter the phone number in valid format..!!");
        }
    }


    private void writeIncorrectPhoneFormat(String number, String message, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(number + ": " + message);
        pw.flush();

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void writeIncorrectDateFormat(String datetime, String message, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(datetime+ " : " + message);
        pw.flush();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

}
