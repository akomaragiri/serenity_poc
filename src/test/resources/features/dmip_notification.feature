Feature: Unified Notification

  Scenario: Should see notification on the UI for A1C_2ND_HALF_TEST_NOT_COMPLTD sub type
    Given I trigger a 'A1C_2ND_HALF_TEST_NOT_COMPLTD' notification behind the scenes
    When I navigate to the notifications page to view notifications
    Then I should see the corresponding notification for this sub type