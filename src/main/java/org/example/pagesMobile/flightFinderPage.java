package org.example.pagesMobile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.accessibility.Accessibility;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
public class flightFinderPage {
    private AndroidDriver driver;
    //Constructor
    public flightFinderPage(AndroidDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//android.widget.TextView[@text='MakeMyTrip']") WebElement MakeMyTripIcon;
    @FindBy(xpath = "//android.widget.TextView[@text='Flights']") WebElement FlightsButton;
    @FindBy(id = "com.makemytrip:id/selected_from_city_text_layout") WebElement From;
    @FindBy(id = "com.makemytrip:id/departure_city_input") WebElement FromTxt;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.makemytrip:id/city_code\" and @text=\"MAA\"]\n")
    WebElement FromTxtClick;
    @FindBy(id = "com.makemytrip:id/selected_to_city_text_layout") WebElement To;
    @FindBy(id = "com.makemytrip:id/arrival_city_input") WebElement ToTxt;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.makemytrip:id/city_code\" and @text=\"DEL\"]\n")
    WebElement ToTxtClick;
    @FindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.makemytrip:id/from_date_layout\"]\n")
    WebElement Depdate;
    @FindBy(xpath = "//MonthViewV2[@content-desc=\"15 MAR 2024 Tap to select\"]\n")
    WebElement DepClickNotSelected;
    @FindBy(xpath = "//MonthViewV2[@content-desc=\"15 MAR 2024 Selected\"]\n")
    WebElement DepClickSelected;
    @FindBy(id = "com.makemytrip:id/btnDone")
    WebElement DepDone;
    @FindBy(xpath = "//android.widget.TextView[@text='OKAY, GOT IT!' or @text='OKAY, GOT IT' or @text='OKAY,GOT IT']")
    WebElement LockPriceButton;
    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.makemytrip:id/search_button_flat\"]\n")
    WebElement SearchFlight;
    @FindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.makemytrip:id/recycler_view\"])[2]/android.view.ViewGroup[1]\n")
    WebElement DismissButton;
    @FindBy(xpath = "(//android.widget.LinearLayout[@resource-id=\"com.makemytrip:id/journey_timing_layout\"])[1]\n")
    WebElement select1stFlightOption;
    @FindBy(xpath = "//android.widget.TextView[@text='BOOK NOW']")
    WebElement bookselectedFlightOption;
    @FindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    WebElement continueSelectedFlightOption;
    @FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.makemytrip:id/checkbox\"])[1]\n")
    WebElement selectTraveller1stOption;
    @FindBy(xpath = "//android.widget.TextView[@text='Travel Unsecured']")
    WebElement travelUnsecuredButton;
    @FindBy(xpath = "//android.widget.TextView[@text='CONFIRM']")
    WebElement reviewConfirmButton;
    public void bookFlightsPage() throws InterruptedException {
        if (isElementPresent("//android.widget.TextView[@text='BACK']"))
        {
            driver.findElement(By.xpath("//android.widget.TextView[@text='BACK']")).click();
        }
        while (!isElementPresent("//android.widget.TextView[@content-desc=\"Phone\"]\n"))
        {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            if (isElementPresent("//android.widget.TextView[@text='BACK']"))
            {
                driver.findElement(By.xpath("//android.widget.TextView[@text='BACK']")).click();
            }
        }
        MakeMyTripIcon.click();
        Thread.sleep(1000);

        if(isElementPresent("//android.widget.TextView[@text='Liked using our App?']"))
        {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        }

        if(isElementPresent("//android.widget.TextView[@text='DISMISS']"))
        {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        }

        if(isElementPresent("//android.widget.TextView[@text='Non-Stop']"))
        {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Non-Stop']")).click();
        }

        if(isElementPresent("//android.widget.TextView[@text='Liked using our App?']"))
        {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        }
        FlightsButton.click();
        Thread.sleep(3000);
    }
    public void userEnterDetails() throws InterruptedException, IOException {

        From.click();
        FromTxt.sendKeys("MAA");
        FromTxtClick.click();

        To.click();
        ToTxt.sendKeys("DEL");
        ToTxtClick.click();

        Depdate.click();
        if (isElementPresent("//MonthViewV2[@content-desc=\"15 MAR 2024 Selected\"]\n"))
        {
            DepClickSelected.click();
        }
        else if (isElementPresent("//MonthViewV2[@content-desc=\"15 MAR 2024 Tap to select\"]\n"))
        {
            DepClickNotSelected.click();
        }
        DepDone.click();

        //Click on Search Flights
        SearchFlight.click();
        captureScreenshot();
        Thread.sleep(3000);
    }

    public void bookingConfirmation() throws InterruptedException, IOException {
        if(isElementPresent("//android.widget.TextView[@text='OKAY, GOT IT!' or @text='OKAY, GOT IT\n']"))
        {
            LockPriceButton.click();
        }
        if(isElementPresent("(//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.makemytrip:id/recycler_view\"])[2]/android.view.ViewGroup[1]\n"))
        {
            DismissButton.click();
        }

        select1stFlightOption.click();
        bookselectedFlightOption.click();
        continueSelectedFlightOption.click();
        continueSelectedFlightOption.click();
        selectTraveller1stOption.click();
        continueSelectedFlightOption.click();

        if(isElementPresent("//android.widget.TextView[@text='Travel Unsecured']"))
        {
            travelUnsecuredButton.click();
        }
        reviewConfirmButton.click();
        Thread.sleep(3000);

        if(isElementPresent("//android.widget.TextView[@text='Skip Seat Selection']")) {
            driver.findElement(By.xpath("//android.widget.TextView[@text='Skip Seat Selection']")).click();
            Thread.sleep(3000);
            if (isElementPresent("//android.widget.TextView[@text='Skip Seat Selection']")) {
                driver.findElement(By.xpath("//android.widget.TextView[@text='Skip Seat Selection']")).click();
            }
            continueSelectedFlightOption.click();
            continueSelectedFlightOption.click();
            continueSelectedFlightOption.click();
        }
        captureScreenshot();
    }

    public void exitFromApp() throws InterruptedException, IOException {
        if (isElementPresent("//android.widget.TextView[@text='BACK']"))
        {
            driver.findElement(By.xpath("//android.widget.TextView[@text='BACK']")).click();
        }

        while (!isElementPresent("//android.widget.TextView[@content-desc=\"Phone\"]\n"))
        {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            if (isElementPresent("//android.widget.TextView[@text='BACK']"))
            {
                driver.findElement(By.xpath("//android.widget.TextView[@text='BACK']")).click();
            }
        }
        captureScreenshot();
        Thread.sleep(3000);
    }
    //User defined method to find if any unexpected element is present or not
    public boolean isElementPresent(String xpath)
    {
        try
        {
            driver.findElement(By.xpath(xpath));
        }
        catch (Throwable t)
        {
            return false;
        }
        return true;
    }

    public void captureScreenshot() throws IOException {
        File file1 = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file1, new File("./screenshots/screenshot.jpg"));
    }
}
