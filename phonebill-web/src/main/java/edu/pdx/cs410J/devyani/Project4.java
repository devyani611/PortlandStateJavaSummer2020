package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    /**
     * The main method creates an arrayList and adds arguments to it.
     * @param args arguments
     */
    public static void main(String[] args) {
        /**
         * Checks each given argument and calls Readme method, is any of the argument is "-README".
         */
        if (Arrays.stream(args).anyMatch(s -> s.equals("-README"))) {
            ReadMe();
            System.exit(0);
        }


        ArrayList<String> argumentsList = new ArrayList<String>(Arrays.asList(args));

       // ArrayList<String> argumentsList = new ArrayList<String>();
        //Collections.addAll(argumentsList, args);

        StringBuilder hostName = new StringBuilder();
        StringBuilder portNumber = new StringBuilder();
        //String hostName = null;
        //String portNumber = null;
        String customer = null;
        String callerNumber = null;
        String calleeNumber = null;
        String startDate = null;
        String startTime = null;
        String startMeridian = null;
        String endDate = null;
        String endTime = null;
        String endMeridian = null;

        String startDateTimeMeridian = null;
        String endDateTimeMeridian = null;

        boolean print = false;
        boolean search = false;

        if (argumentsList.size() == 0) {
            usage(MISSING_ARGS);
        }

        //Parse all the options
        //parseReadMe(args);
        print = CheckForPrint(argumentsList);

        search = CheckForSearch(argumentsList);

        CheckForHostandPort(hostName, portNumber, argumentsList);

        //Parses the port
        int port;
        try {
            assert portNumber != null;
            port = Integer.parseInt(portNumber.toString());
        }
        catch (NumberFormatException ex) {
            usage("Port \"" + portNumber + "\" must be an integer");
            return;
        }

        //Parses the arrayList and fills in the arguments
        for (String arg : argumentsList) {
            if (customer == null) {
                customer = arg;
            } else if (callerNumber == null) {
                callerNumber = arg;
            } else if (calleeNumber == null) {
                calleeNumber = arg;
            } else if (startDate == null) {
                startDate = arg;
            } else if (startTime == null) {
                startTime = arg;
            } else if (startMeridian == null) {
                startMeridian = arg;
            } else if (endDate == null) {
                endDate = arg;
            } else if (endTime == null) {
                endTime = arg;
            } else if (endMeridian == null) {
                endMeridian = arg;
            }
        }

        //Uses the client to interact with the servlet
        PhoneBillRestClient client = new PhoneBillRestClient(hostName.toString(), port);
        HttpRequestHelper.Response response = null;

        try {
            if (argumentsList.size() == 1) {
                response = client.getAllPhoneCalls();
            }

            else if (search) {
                if(argumentsList.size() > 7){
                    usage("Extraneous arguments..!! Please provide valid arguments..!!");
                }
                else if(argumentsList.size() < 7){
                    usage("Missing arguments..!! Please provide valid arguments..!!");
                }
                else{
                    startDateTimeMeridian = callerNumber + " " + calleeNumber + " " + startDate;
                    endDateTimeMeridian = startTime + " " + startMeridian + " " + endDate;
                }

                response = client.getRangePhoneCalls(customer, startDateTimeMeridian, endDateTimeMeridian);

                //System.out.println(response.getContent());
            }
            else {
                if(argumentsList.size() > 9){
                    usage("Extraneous arguments..!! Please provide valid arguments..!!");
                }
                else if(argumentsList.size() < 9){
                    usage("Missing arguments..!! Please provide valid arguments..!!");
                }
                else{
                    startDateTimeMeridian = startDate + " " + startTime + " " + startMeridian;
                    endDateTimeMeridian = endDate + " " + endTime + " " + endMeridian;
                }


                response = client.addPhoneCall(customer, callerNumber , calleeNumber, startDateTimeMeridian, endDateTimeMeridian);
            }

            checkResponseCode(HttpURLConnection.HTTP_OK, response);

        }
        catch (IOException e) {
            System.err.println("Error while contacting server: "+e);
            System.exit(1);
        }

        if (print && !search) {
            System.out.println(String.format("Phone call from %s to %s from %s to %s.)", callerNumber, calleeNumber, startDateTimeMeridian, endDateTimeMeridian));
        }

        System.out.println(response.getContent());

        System.exit(0);
    }

    /**
     * Check if the arguments list has "-print" option
     * @param argumentsList arraylist of arguments
     * @return
     */
    private static boolean CheckForPrint(ArrayList<String> argumentsList) {
        //If the -print tag exists, set verbose to true and remove it from the array so only the arguments are left
        if (argumentsList.contains("-print")) {
            argumentsList.remove("-print"); //Removes the tag from the list of arguments
            return true;
        }
      return false;
    }

    /**
     * Check if the arguments list has "-search" option
     * @param argumentsList arraylist of arguments
     * @return
     */
    private static boolean CheckForSearch(ArrayList<String> argumentsList) {
        if (argumentsList.contains("-search")) {
            argumentsList.remove("-search");
            return true;
        }
        return false;
    }

    /**
     * Check the arguments list for "-host" and "-port" options
     * @param host variable to hold the hostname
     * @param port variable to hold the port number
     * @param argumentsList The list of command line arguments
     */
    private static void CheckForHostandPort(StringBuilder host, StringBuilder port, ArrayList<String> argumentsList) {
        if (argumentsList.contains("-host") && argumentsList.contains("-port")) {
            int hostIndex = argumentsList.indexOf("-host") + 1;
            int portIndex = argumentsList.indexOf("-port") + 1;
            host.append(argumentsList.get(hostIndex));
            port.append(argumentsList.get(portIndex));
            argumentsList.remove(hostIndex);
            argumentsList.remove(hostIndex - 1);
            portIndex = argumentsList.indexOf("-port") + 1;
            argumentsList.remove(portIndex);
            argumentsList.remove(portIndex - 1);
        }
        else if (argumentsList.contains("-host") && !argumentsList.contains("-port")) {
            usage("Please provide the Port..!!");
        }
        else if (argumentsList.contains("-port") && !argumentsList.contains("-host")) {
            usage("Please provide the host..!!");
        }
    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }


    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    public static void usage(String message) {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java edu.pdx.cs410J.<login-id>.Project4 [options] <args>");
        err.println("args are (in this order)");
        err.println("  customer   Person whose phone bill we're modeling");
        err.println("  callerNumber   Phone number of caller");
        err.println("  calleeNumber     Phone number of person who was called");
        err.println("  startTime   Date and time call began");
        err.println("  endTime   Date and time call began");
        err.println();
        err.println("options are (options may appear in any order)");
        err.println("-host hostname   Host computer on which the server runs");
        err.println("-port port   Port on which the server is listening");
        err.println("-search   Phone calls should be searched for");
        err.println("-print Prints a description of the new phone call");
        err.println("-README prints a README for this project and exits");
        err.println();

        System.exit(1);
    }

    private static void ReadMe() {
        System.out.println("This is project1: Designing a phoneBill application \n" +
                "The project is designed by Devyani Shrivastava at Portland State University \n" +
                "The project is an extension of the Phonebill application that supports Phonebill server and provides REST services to " +
                "Phonebill client");
        System.exit(0);
    }
}