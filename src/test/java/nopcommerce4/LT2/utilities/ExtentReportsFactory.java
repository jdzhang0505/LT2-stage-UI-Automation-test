package nopcommerce4.LT2.utilities;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportsFactory {
	public static ExtentReports exReport;

	
	public static ExtentReports getExReportInstance() {
		exReport=new ExtentReports(Contents.ReportPath,false);
		exReport.addSystemInfo("PlatFrom","windows");
		return exReport;
		
	}
}
