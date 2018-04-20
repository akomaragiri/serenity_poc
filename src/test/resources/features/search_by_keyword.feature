Feature: Searching by keyword

  In order to find relevant articles
  I can go to google search page
  I want to be able to search articles containing certain words

  Scenario: Should list page title related to a specified keyword
    Given I am on the google page
    When I search for the word 'firefly'
    Then I should see the page title as 'firefly - Google Search'