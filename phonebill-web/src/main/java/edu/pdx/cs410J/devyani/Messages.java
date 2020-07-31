package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{

    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    public static String noPhoneBillForCustomer(String customerName) {
        return String.format("No phonebill for customer %s",  customerName);
    }


    public static String addedCall(String call){
        return String.format("Added %s", call);
    }


    public static String printPrettyPhoneCall(AbstractPhoneCall call) throws IOException {
        PrettyPrinter pp = new PrettyPrinter();
        return pp.dump(call);

    }

    public static String getMappingCount(int count) {
        return String.format("Server contains %d phone calls.", count);
    }
}
