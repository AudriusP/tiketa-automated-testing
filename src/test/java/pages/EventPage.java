package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class EventPage {
    WebDriver driver;

    public EventPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1.title")
    private WebElement eventTitle;

    @FindBy(css = "div.title-block p")
    private WebElement eventPlace;

    @FindBy(css = "p.date-month")
    private WebElement eventMonthDay;

    public void verifyEventTitle(String eventTitle) {
        Assert.assertTrue(this.eventTitle.getText().contains(eventTitle));
    }

    public void verifyEventPlace(String eventPlace) {
        Assert.assertTrue(this.eventPlace.getText().contains(eventPlace));
    }

    public void verifyEventMonth(String eventMonth) {
        Assert.assertEquals(getEventMonth(), eventMonth);
    }

    public void waitForEventPageToBeShown() {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.title")));
    }

    private String getEventMonth() {
        return eventMonthDay.getText().split("/")[0];
    }
}
