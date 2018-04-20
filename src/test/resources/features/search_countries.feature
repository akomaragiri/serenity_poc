Feature: Search countries using ISO code

  In order to ensure that I can retrieve country name using ISO codes
  I make an api call to search for a country using either 2 or 3 digit ISO code
  I see the country name in the api response

  Scenario: Should search countries by 2 digit ISO codes
    Given I have the webservice URL for two digit ISO codes
    When I search for the country code 'US'
    Then I should see the response containing 'United States of America'

  Scenario: Should search countries by 3 digit ISO codes
    Given I have the webservice URL for three digit ISO codes
    When I search for the country code 'IND'
    Then I should see the response containing 'India'
