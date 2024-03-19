package runnerCucumberPkg;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import java.text.SimpleDateFormat;
import java.util.Date;

@CucumberOptions(features = "src/test/resources/features/APITesting",
        glue = "org.example.stepDefAPItests",
        plugin = {"pretty","html:resources/cucumber-reports/cucu-report.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true)
public class testRunnerCucAPI extends AbstractTestNGCucumberTests{
    static SimpleDateFormat d1=new SimpleDateFormat("ddMMyyyy_hhmmss");
    static String str = "resources/cucumber-reports/cuc-report_"+d1.format(new Date());

    public static void main(String[] args)
    {
        System.out.println("PRINTING PATH---->"+str);
    }
}
