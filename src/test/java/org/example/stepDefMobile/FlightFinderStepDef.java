package org.example.stepDefMobile;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pagesMobile.flightFinderPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FlightFinderStepDef {
    private flightFinderPage flightFinderObject;
    private AndroidDriver driver;
    private DesiredCapabilities cap;
    SoftAssert softassert = new SoftAssert();
    @Before
    public void setup() throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("appium:platformVersion","12");
        cap.setCapability("appium:automationName","UIAutomator2");
        cap.setCapability("appium:deviceName","R9ZR30CGCPM");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
    }
    @After
    public void tearDown()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }
    @Given("User is on book flights page")
    public void user_is_on_book_flights_page() throws InterruptedException {
        System.out.println("Inside @Given in Step Def");
        flightFinderObject = new flightFinderPage(driver);
        flightFinderObject.bookFlightsPage();
    }
    @When("User enters the source, destination & travel date")
    public void user_enters_the_source_destination_travel_date() throws InterruptedException, IOException {
        System.out.println("Inside @When in Step Def");
        flightFinderObject.userEnterDetails();
    }
    @Then("Final confirmation of booking page should appear")
    public void final_confirmation_of_booking_page_should_appear() throws InterruptedException, IOException {
        System.out.println("Inside @Then in Step Def");
        flightFinderObject.bookingConfirmation();
        softassert.assertTrue(flightFinderObject.isElementPresent("//android.widget.TextView[@text='Select Payment Mode']"), "Select Payment Mode not present");
        softassert.assertAll();
    }
    @When("Exit from flights page")
    public void exitFromFlightsPage() throws InterruptedException, IOException {
        System.out.println("Inside @When in Step Def");
        flightFinderObject.exitFromApp();
        softassert.assertEquals(true, true, "1st Scenario - When PASS");
        softassert.assertAll();
    }
    @Given("given in a scenario")
    public void givenInAScenario() {
        System.out.println("Given: Before Soft Assert");
        softassert.assertTrue(true, "Given If FAIL");
        System.out.println("Given: After Soft Assert");
        softassert.assertAll();
    }
    @When("when in a scenario")
    public void whenInAScenario() {
        System.out.println("When: Before Soft Assert");
        softassert.assertTrue(true, "When If FAIL");
        System.out.println("When: After Soft Assert");
        softassert.assertAll();
    }
    @Then("Experiment Assert")
    public void Experiment_Assert() {
        System.out.println("Then: Before Hard assert");
        Assert.assertEquals(3,3);
        System.out.println("Then: After Hard assert");
    }
}
