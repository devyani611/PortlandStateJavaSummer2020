package edu.pdx.cs410J.devyani;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneCallTest {

    @Test
    public void testConstructor(){
        PhoneCall call = new PhoneCall("999-909-9999", "231-223-1234","12/11/2001 11:23","12/11/2001 11:25");
        assertThat(call.getCaller(), containsString("999-909-9999"));
        assertThat(call.getCallee(), containsString("231-223-1234"));
    }

    @Test
    public void getCallerImplemented()
    {
        PhoneCall call = new PhoneCall("callerNumber","calleeNumber","start","end");
        assertThat(call.getCaller(), containsString("callerNumber"));
    }

    @Test
    public void getCalleeImplemented()
    {
        PhoneCall call = new PhoneCall("callerNumber","calleeNumber","start","end");
        assertThat(call.getCallee(), containsString("calleeNumber"));
    }


    @Test
    public void getStartTimeStringImplemented()
    {
        PhoneCall call = new PhoneCall("callerNumber","calleeNumber", "09/12/2001 12:23", "end");
        assertThat(call.getStartTimeString(), containsString("09/12/2001 12:23"));
    }


    @Test
    public void getEndTimeStringImplemented()
    {
        PhoneCall call = new PhoneCall("callerNumber","calleeNumber", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.getEndTimeString(), containsString("09/12/2001 12:25"));
    }


    @Test
    public void ChecktoStringMethodWithValidArguments(){
        PhoneCall call = new PhoneCall("989-000-9999","989-109-6789", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.toString(), equalTo("Phone call from 989-000-9999 to 989-109-6789 from 09/12/2001 12:23 PM to 09/12/2001 12:25 PM"));

    }


    @Test
    public void toStringContainsCallerNumber(){
        PhoneCall call = new PhoneCall("989-000-9999","989-109-6789", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.toString(), containsString("Phone call from 989-000-9999"));

    }

    @Test
    public void toStringContainsCalleeNumber(){
        PhoneCall call = new PhoneCall("989-000-9999","989-109-6789", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.toString(), containsString("to 989-109-6789"));

    }

    @Test
    public void toStringContainsStartDateAndTime(){
        PhoneCall call = new PhoneCall("989-000-9999","989-109-6789", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.toString(), containsString("from 09/12/2001 12:23 PM"));

    }


    @Test
    public void toStringContainsEndDateAndTime(){
        PhoneCall call = new PhoneCall("989-000-9999","989-109-6789", "09/12/2001 12:23", "09/12/2001 12:25");
        assertThat(call.toString(), containsString("to 09/12/2001 12:25 PM"));

    }
}
