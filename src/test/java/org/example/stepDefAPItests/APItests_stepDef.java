package org.example.stepDefAPItests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pagesAPI.APItests_page;
import org.testng.asserts.SoftAssert;

public class APItests_stepDef {
    APItests_page apitestsObject;
    SoftAssert softAssert = new SoftAssert();
    @Then("User should receive {int} as a response code")
    public void user_should_receive_as_a_response_code(Integer int1) {
     apitestsObject = new APItests_page();
     softAssert.assertEquals(apitestsObject.getreqresdata1(), 200);
     softAssert.assertAll();
    }
}
