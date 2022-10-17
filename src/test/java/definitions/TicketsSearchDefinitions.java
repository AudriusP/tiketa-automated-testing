package definitions;

import base.BaseFunctions;
import pages.EventPage;
import pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import runner.TestRunner;

public class TicketsSearchDefinitions extends TestRunner {
    private SearchPage searchPage;
    private EventPage eventPage;

    @Before
    public void setUp() {
        searchPage = new SearchPage(driver);
        eventPage = new EventPage(driver);
    }

    @Given("User is on Tickets Search page {string}")
    public void userIsOnTicketsSearchPage(String pageUrl) {
        driver.get(pageUrl);
    }

    @When("User enters event caption {string} event")
    public void userEntersEventCaptionEvent(String eventCaption) {
        searchPage.enterAndSelectCaption(eventCaption);
    }

    @And("User chooses event place {string}")
    public void userChoosesEventPlace(String eventPlace) {
        searchPage.enterAndSelectEventPlace(eventPlace);
    }

    @And("User chooses year {string} and month {string} for event date")
    public void userChoosesYearAndMonthForEventDate(String year, String month) {
        searchPage.enterDateWholeMonth(year, month);
    }

    @And("User clicks SEARCH button")
    public void userClicksSEARCHButton() {
        searchPage.clickSearchButton();
    }

    @Then("List of available events is shown")
    public void listOfAvailableEventsIsShown() {
        searchPage.waitForEventsListToBeShown();
    }

    @When("User clicks on first event in the list")
    public void userClicksOnFirstEventInTheList() throws InterruptedException {
        Thread.sleep(1000);
        searchPage.clickEventById(0);
    }

    @Then("Event details page is opened")
    public void eventDetailsPageIsOpened() {
        eventPage.waitForEventPageToBeShown();
    }

    @Then("Verify event caption {string}, place {string}, month {string}")
    public void verifyEventCaptionPlaceYearMonth(String eventTitle, String eventPlace, String eventMonth) {
        eventPage.verifyEventTitle(eventTitle);
        eventPage.verifyEventPlace(eventPlace);
        eventPage.verifyEventMonth(eventMonth);
    }
}
