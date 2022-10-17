package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input.searchword-field.tt-input")
    private WebElement captionSearchField;

    @FindBy(css = "div.tt-suggestion")
    private List<WebElement> captionsSuggestions;

    @FindBy(css = "button#dropdownMenu4")
    private WebElement eventPlaceDropDown;

    @FindBy(xpath = "//button[@id='dropdownMenu4']/../input")
    private WebElement eventPlaceSearchField;

    @FindBy(css = "ul[aria-labelledby='dropdownMenu4'] a[role='menuitem'][style='display: block;']")
    private List<WebElement> placesSuggestions;

    @FindBy(css = "input[name='sf_DateFrom']")
    private WebElement dateFromField;

    @FindBy(css = "input[name='sf_DateTo']")
    private WebElement dateToField;

    @FindBy(css = "a.ui-state-default")
    private List<WebElement> calendarDays;

    @FindBy(css = "div.advanced-search-submit-btn button")
    private WebElement searchButton;

    @FindBy(css = "div.article")
    private List<WebElement> events;

    public void enterAndSelectCaption(String caption) {
        captionSearchField.sendKeys(caption);
        waitForCaptionsDropdownToAppear();
        captionsSuggestions.get(0).click();
    }

    public void enterAndSelectEventPlace(String eventPlace) {
        eventPlaceDropDown.click();
        eventPlaceSearchField.sendKeys(eventPlace);
        waitForEventPlacesDropdownToAppear();
        placesSuggestions.get(0).click();
    }

    public void enterDateWholeMonth(String year, String month) {
        dateFromField.sendKeys(String.format("%s-%s-01", year, month));
        String lastMonthDay = calendarDays.get(calendarDays.size() - 1).getText();
        dateToField.sendKeys(String.format("%s-%s-%s", year, month,
                lastMonthDay.length() == 1 ? "0" + lastMonthDay : lastMonthDay));
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickEventById(int id) {
        events.get(id).click();
    }

    private void waitForCaptionsDropdownToAppear() {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.tt-dropdown-menu")));
    }

    private void waitForEventPlacesDropdownToAppear() {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeToBe(By.cssSelector("ul[aria-labelledby='dropdownMenu4']"), "display", "block"));
    }

    public void waitForEventsListToBeShown() {
        new WebDriverWait(this.driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.article"), 0));
    }
}
