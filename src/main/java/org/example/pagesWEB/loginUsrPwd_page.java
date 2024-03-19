package org.example.pagesWEB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginUsrPwd_page {
    private WebDriver driver;
    public loginUsrPwd_page(WebDriver localDriver)
    {
        this.driver=localDriver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//*[@id='login']/h2") WebElement loginPageText;
    @FindBy(id="username") WebElement username;
    @FindBy(id="password") WebElement password;
    @FindBy(id="submit") WebElement submitButton;
    @FindBy(id="error") WebElement errorMessage;
    @FindBy(xpath="//*[@id='loop-container']/div/article/div/h1")
    WebElement successLoginMessage;

    public String loginPage() throws InterruptedException {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        String str = loginPageText.getText();
        Thread.sleep(2000);
        return str;
    }
    public void enterUserName(String str)
    {
        username.sendKeys(str);
    }
    public void enterPassword(String str)
    {
        password.sendKeys(str);
    }
    public void clickSubmit() throws InterruptedException {
        Thread.sleep(2000);
        submitButton.click();
    }
    public String errorMessage()
    {
        return errorMessage.getText();
    }
    public String successLoginMessageMethod()
    {
        return successLoginMessage.getText();
    }
}
