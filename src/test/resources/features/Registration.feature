Feature: New account registration
  To check that the registration form works correctly

  Scenario: Create user - works as expected
    Given I am on the New Supporter Account registration page
    When I fill in all required details correctly
    And I accept the Terms and Conditions
    And I submit the registration form
    Then the account should be created successfully

  Scenario: Create user - lastname is missing
    Given I am on the New Supporter Account registration page
    When I fill in required details except the last name
    And I accept the Terms and Conditions
    And I submit the registration form
    Then the account should not be created

  Scenario: Create user - passwords do not match
    Given I am on the New Supporter Account registration page
    When I fill in all required details except matching passwords
    And I accept the Terms and Conditions
    And I submit the registration form
    Then I should see a password mismatch error

  Scenario: Create user – terms and conditions are not accepted
    Given I am on the New Supporter Account registration page
    When I fill in all required details correctly
    And I do not accept the Terms and Conditions
    And I submit the registration form
    Then the account should not be created


