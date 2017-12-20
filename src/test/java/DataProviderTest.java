import static org.junit.Assert.*;

import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.verifications.Verify;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import java.net.URI;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //https://admhelp.microfocus.com/leanft/en/latest/HelpCenter/Content/HowTo/CustomFrameworks.htm
        try{
            ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
            config.setServerAddress(new URI("ws://localhost:5095"));
            SDK.init(config);
            Reporter.init();
        }
        catch(Exception e){
        }
    }

    @AfterClass
    public static void teardownAfterClass() throws Exception {
        //https://admhelp.microfocus.com/leanft/en/latest/HelpCenter/Content/HowTo/CustomFrameworks.htm
        try{
            Reporter.generateReport();
            SDK.cleanup();
        }
        catch(Exception e){
        }
    }

    // Rest of the code came from and is based on
    //https://admhelp.microfocus.com/leanft/en/latest/HelpCenter/Content/HowTo/CustomFrameworks.htm
    @DataProvider
    public static Object[][] dataProviderAdd() {
        // @formatter:off
        return new Object[][] {
                { 0, 0, 0 },
                { 1, 2, 2 },
                { 2, 2, 4 },
                /* ... */
        };
        // @formatter:on
    }

    @Test
    @UseDataProvider("dataProviderAdd")
    public void testAdd(int a, int b, int expected) {
        // Given:

        // When:
        int result = a + b;

        // Then:
        //assertEquals(expected, result);

        //Leveraging the UFT Pro (LeanFT) Verify object which will automatically create the runresults.html report
        Verify.areEqual(expected, result);
    }
}