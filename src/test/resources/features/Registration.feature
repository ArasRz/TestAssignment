Feature: New account registration
  To check that the registration form works correctly

  Scenario: Create user - works as expected
    Given I am on the New Supporter Account registration page
    When I fill in all required details correctly
    And I accept the Terms and Conditions
    And I submit the registration form
    Then the account should be created successfully
