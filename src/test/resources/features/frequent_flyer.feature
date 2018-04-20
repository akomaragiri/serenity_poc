Feature: Frequent Flyer Program

  In order to provide incentives to travellers
  I want a frequent flyer program
  I can use to track frequent travellers

  Scenario: Should increment balance every time a frequent flyer travels
    Given I have a frequent flyer account with 10000 points
    When I fly 1000 kilometers
    Then I should have 10100 points