Feature: Google Search SecurePay
  Google Search SecurePay

  Scenario: Google Search SecurePay
    Given I go to google
    When I search "SecurePay"
    Then I should see "SecurePay Search Results"
    When I Click on the Link
    Then I should be navigate to SecurePay homePage
    When I Click on ContactUs
    And I should verify contact us page is loaded
    Then I should be able to fill the form