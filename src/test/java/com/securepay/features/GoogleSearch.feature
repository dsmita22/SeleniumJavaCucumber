Feature: Google Search
  Google Search Keyword

  Scenario: Google Search SecurePay
    Given I go to google
    When I search "SecurePay"
    Then I should see "SecurePay Search Results"