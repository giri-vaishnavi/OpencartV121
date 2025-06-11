package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {
       /* SimpleDateFormat df = new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
        Date dt = new Date();
        String currentdatetimestamp= df.format(dt);  */
    	
    	// Format timestamp for report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        // Set up Spark Reporter
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specifiy location
        sparkReporter.config().setDocumentTitle("opencart Automation Report"); //title of report
        sparkReporter.config().setReportName("opencart Functional Testing"); //name of report
        sparkReporter.config().setTheme(Theme.DARK);

        // Set up ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
         extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        //operating system and browser name getting from xml report
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped");
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        try {
            File htmlFile = new File(".\\reports\\" + repName);
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
   /* try {
        URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

        // Create the email message
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("girikhardekarvaishnavi@gmail.com", "password"));
        email.setSSLOnConnect(true);

        email.setFrom("girikhardekarvaishnavi@gmail.com"); // Sender
        email.setSubject("Test Results");
        email.setMsg("Please find Attached Report....");
        email.addTo("vaishnavi.02101996@gmail.com"); // Receiver
        email.attach(url, "extent report", "please check report...");

        email.send(); // send the email
        
    } catch (Exception e) {
        e.printStackTrace();
    } */
    }
}
