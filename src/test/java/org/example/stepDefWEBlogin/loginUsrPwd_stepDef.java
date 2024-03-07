package org.example.stepDefWEBlogin;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pagesWEB.loginUsrPwd_page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class loginUsrPwd_stepDef {
    private loginUsrPwd_page loginPageObject;
    private WebDriver driver;
    SoftAssert softASSERT = new SoftAssert();
    @Before
    public void setup()
    {
        driver=new ChromeDriver();
    }
    @After
    public void close()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
    @Given("User is on the login page")
    public void user_is_on_the_login_page() throws InterruptedException {
        loginPageObject = new loginUsrPwd_page(driver);
        String str = loginPageObject.loginPage();
        softASSERT.assertEquals(str, "Test login", "Login Text Not Matching");
        softASSERT.assertAll();
    }
    @When("User provided the valid username and password")
    public void user_provided_the_valid_username_and_password() throws InterruptedException {
        loginPageObject.enterUserName("student");
        loginPageObject.enterPassword("Password123");
        loginPageObject.clickSubmit();

    }
    @When("User provided the invalid username and wrong password")
    public void user_provided_the_invalid_username_and_password() throws InterruptedException {
        loginPageObject.enterUserName("student");
        loginPageObject.enterPassword("incorrectPassword");
        loginPageObject.clickSubmit();

    }
    @Then("User should not be logged in and should be shown an invalid password message")
    public void User_should_not_be_logged_in_and_should_be_shown_an_invalid_password_message() {
        softASSERT.assertEquals(loginPageObject.errorMessage(),"Your password is invalid!");
        softASSERT.assertAll();
        System.out.println("\nPRINTING----"+loginPageObject.errorMessage());
    }
    @Then("User should be logged in successfully and should be on homepage")
    public void User_should_be_logged_in_successfully_and_should_be_on_homepage() {
        softASSERT.assertEquals(loginPageObject.successLoginMessageMethod(),"Logged In Successfully");
        softASSERT.assertAll();
        System.out.println("\nPRINTING----"+loginPageObject.successLoginMessageMethod());
    }
}
