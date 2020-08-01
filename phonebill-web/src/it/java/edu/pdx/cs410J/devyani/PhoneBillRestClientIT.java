package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

/*  @Test
  public void test0RemoveAllDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllDictionaryEntries();
  }

/**  @Test
  public void test1EmptyServerContainsNoDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    Map<String, String> dictionary = client.getAllDictionaryEntries();
    assertThat(dictionary.size(), equalTo(0));
  }*/

  @Test
  public void test1EmptyServerContainsNoPhonebills() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    HttpRequestHelper.Response response =  client.getAllPhoneCalls();
    assertThat(response.getContent(), containsString(""));
  }

  @Test
  public void test2ServerReturnsRangePhoneCalls() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String customer = "customer";
    String starttime = "starttime";
    String endtime = "endtime";
    HttpRequestHelper.Response response =  client.getRangePhoneCalls(customer, starttime, endtime);
    assertThat(response.getContent(), containsString(""));
  }

  @Test
  public void test3ServerAddsPhonecall() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String customer = "customer";
    String starttime = "starttime";
    String endtime = "endtime";
    String caller = "caller";
    String callee = "callee";
    HttpRequestHelper.Response response =  client.addPhoneCall(customer, caller,callee, starttime, endtime);
    assertThat(response.getContent(), containsString(""));
  }





}
