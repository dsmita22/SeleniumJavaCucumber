package com.securepay;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(
        plugin = {"pretty", "json:target/report/cucumber2.json"},
        strict = true,
        features = {"src/test/java/com/securepay/features"},
        glue = "com.securepay.steps"
)
public class AcceptanceTestSuite extends AbstractTestNGCucumberTests {
    private static Logger log = LogManager.getLogger(AcceptanceTestSuite.class);

    @AfterSuite
    public static void generateReport() {
        log.info("......Generating the Report....");
        File reportOutputDirectory = new File("target");

        String projectName = "SecurePay Test Project";

        List<String> jsonFiles = new ArrayList<>();
        File e = new File("target/report/cucumber2.json");
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "chrome");
        jsonFiles.add(e.getAbsolutePath());
        log.info("......Generating the Report in process....");

        new ReportBuilder(jsonFiles, configuration).generateReports();
    }
}