package com.sbstechtest.helper;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateExtentReports extends BaseFunctions{

    static String filePathdate = null;
    public static ExtentReports extent;
    public static ExtentTest scenarioDef;
    public static ExtentTest stepDef;
    public static ExtentTest logDef;    
    

    public static void extentReport() {
    	extent = new ExtentReports();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
        Date date = new Date();
        filePathdate = dateFormat.format(date);
    }

    public static void flushReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.reportPath + "//" + filePathdate);
        reporter.config().setReportName("SBS Tech Test");
        reporter.config().setDocumentTitle("Test Automation Results");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("utf-8");

        extent.attachReporter(reporter);
        extent.flush();

        String defaultReportLocation = Constants.reportPath + "//" + filePathdate + "\\index.html";
        String desiredReportLocation = Constants.reportPath + "//" + filePathdate+".html";

        new File(defaultReportLocation).renameTo(new File(desiredReportLocation));

    }
}

