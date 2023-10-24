Feature: login

  Scenario: Valid Login
    Given Load homepage
    When input valid username
    And input valid password
    And clicks Login button
    Then Navigated to homepage
