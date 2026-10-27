package utilities;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.Baseclass;

public class ExtentReportManager implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    
    // ThreadLocal ensures thread-safety during parallel test execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    
    private String repName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        File reportDir = new File("./reports/");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        sparkReporter.config().setDocumentTitle("flasho");
        sparkReporter.config().setReportName("flasho Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "flasho");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        if (os != null) extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        if (browser != null) extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (includedGroups != null && !includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getSimpleName() + " :: " + result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        test.assignCategory(result.getMethod().getGroups());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, result.getName() + " executed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getName() + " failed");
        
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.INFO, result.getThrowable());
        }

        try {
            Object testInstance = result.getInstance();
            String imgPath;
            if (testInstance instanceof Baseclass) {
                imgPath = Baseclass.captureScreen(result.getName());
            } else {
                new Baseclass();
				imgPath = Baseclass.captureScreen(result.getName());
            }

            if (imgPath != null) {
                extentTest.get().addScreenCaptureFromPath(imgPath);
            }
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, result.getName() + " was skipped");
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.INFO, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext testContext) {
        // Essential step: writes and flushes the report to disk
        extent.flush();
        extentTest.remove();

        // Optional: Automatically opens the report in your default browser upon completion
        try {
            File reportFile = new File("./reports/" + repName);
            if (!GraphicsEnvironment.isHeadless() && Desktop.isDesktopSupported() && reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}