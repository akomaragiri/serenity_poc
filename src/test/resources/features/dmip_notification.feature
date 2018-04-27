Feature: Unified Notification

  Scenario: Should see notification on the UI for A1C_2ND_HALF_TEST_NOT_COMPLTD sub type
    Given The contractId is 'R60378743', memberId is '14045622'
    When I trigger a 'A1C_2ND_HALF_TEST_NOT_COMPLTD' notification behind the scenes
    And I login using 'ASPEN', 'Littestr#4', '5622'
    And I navigate to the notifications page to view notifications
    Then I should see the corresponding notification header for this sub type
    And I should see the corresponding notification body for this sub type

  Scenario: Should see notification on the UI for ORDER_SHPD_CONS sub type
    Given The contractId is 'R60655075', memberId is '13871689'
    And The carrierName is 'USPS', numberOfPrescriptions is '4', shipDate is '04/27/2018', trackingNumber is '1243'
    When I trigger a 'ORDER_SHPD_CONS' notification behind the scenes
    And I login using 'R2UAT510', 'Uattest@1', '1689'
    And I navigate to the notifications page to view notifications
    Then I should see the corresponding notification header for this sub type
    And I should see the corresponding notification body for this sub type