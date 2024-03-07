Feature: Login from homepage using username and password

  Scenario: TC1: To verify login using incorrect username and password
    Given User is on the login page
    When User provided the invalid username and wrong password
    Then User should not be logged in and should be shown an invalid password message

  Scenario: TC2: To verify login using correct username and password
    Given User is on the login page
    When User provided the valid username and password
    Then User should be logged in successfully and should be on homepage
