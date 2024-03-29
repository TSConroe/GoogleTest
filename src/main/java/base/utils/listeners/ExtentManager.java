package base.utils.listeners;

import com.relevantcodes.extentreports.ExtentReports;

class ExtentManager {

    private static ExtentReports extent;

    synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir + "\\ExtentReports\\ExtentReportResults.html", true);
        }
        return extent;
    }
}
