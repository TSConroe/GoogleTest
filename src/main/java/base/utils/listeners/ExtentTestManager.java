package base.utils.listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    static synchronized void startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
    }
}