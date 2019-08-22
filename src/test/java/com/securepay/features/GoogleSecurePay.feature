Feature: Google Search SecurePay
  Google Search SecurePay

  Scenario: Google Search SecurePay
    Given I go to google
    When  I search "SecurePay"
    When  I Click on the SecurePayLink
    When  I Click on ContactUs
    And   I should verify contact us page is loaded
    Then  I should be able to fill the form