Feature: Tickets search

  Scenario Outline: Search for ticket
    Given User is on Tickets Search page "https://www.tiketa.lt/EN/search"
    When User enters event caption "<eventCaption>" event
    And User chooses event place "<eventPlace>"
    And User chooses year "<eventYear>" and month "<eventMonth>" for event date
    And User clicks SEARCH button
    Then List of available events is shown
    When User clicks on first event in the list
    Then Event details page is opened
    Then Verify event caption "<eventCaption>", place "<eventPlace>", month "<eventMonth>"

    Examples:
      | eventCaption   | eventPlace                       | eventYear | eventMonth |
      | Alter Ego      | Naujasis teatras - kamerinė salė | 2022      | 12         |
