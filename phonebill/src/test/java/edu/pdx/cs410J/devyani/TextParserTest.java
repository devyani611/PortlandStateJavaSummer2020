package edu.pdx.cs410J.devyani;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

public class TextParserTest {
    @Test
    public void TestToparseTextFile()
    {
        PhoneBill bill;
        TextParser parser = new TextParser("file");
        try
        {
            bill = parser.parse();
        }
        catch (ParserException e)
        {
            e.printStackTrace();
        }
    }
}
