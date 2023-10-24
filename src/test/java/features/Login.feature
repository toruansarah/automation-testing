@FirstRelease
Feature: Login functionality

  @Positive
  Scenario Outline: To verify if user successfully login
    # precondition
    Given user is on SauceDemo homepage
    #steps
    When user input <username> as username
    And user input <password> as password
    And user click enter
    # expected
    Then user verify <status> login result

    Examples:
      | username                | password      | status  |
      | standard_user           | secret_sauce  | success |
      | performance_glitch_user | secret_sauce  | success |
      | failed_user             | secret_sauce  | false   |

  @Negative
  Scenario Outline: Ensure user failed login with wrong username and password
    # precondition
    Given user is on SauceDemo homepage
    #steps
    When user input <username> as username
    And user input <password> as password
    And user click enter
    # expected
    Then user verify failed login

    Examples:
      | username                | password      |
      | failed_user             | secret_sauce  |

  @Negative
  Scenario Outline: To verify if user failed login with password null
    Given user is on SauceDemo homepage
    When user input <username> as username
    And user click enter
    Then user verify failed login

    Examples:
      | username                |
      | standard_user           |