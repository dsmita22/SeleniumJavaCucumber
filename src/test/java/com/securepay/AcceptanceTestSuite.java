package com.securepay;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CucumberOptions(features = "src/test/resources/features", glue ={"tests.hooks","test.steps"})
public class AcceptanceTestSuite extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
    private static Logger log = LogManager.getLogger(AcceptanceTestSuite.class);

    @AfterSuite
    public static void generateReport() {
        log.info("......Generating the Report....");
        File reportOutputDirectory = new File("target");

        List<String> jsonFiles = new ArrayList<>();
        File e = new File("target/jsonReports/cucumber.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "selenium");

        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "chrome");
        jsonFiles.add(e.getAbsolutePath());
        log.info("......Generating the Report in process....");

        new ReportBuilder(jsonFiles, configuration).generateReports();
    }
}
