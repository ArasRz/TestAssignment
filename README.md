# TestAssignment 

This project automates the testing of the **"New Account"** registration form using Selenium WebDriver and Cucumber.

---

##  Included Test Scenarios

1. **Create user – all correct**
    - A user is created successfully with valid inputs.

2. **Create user – last name is missing**
    - Form should reject submission due to missing last name.

3. **Create user – passwords do not match**
    - Form should show an error due to mismatched passwords.

4. **Create user – terms and conditions not accepted**
    - Form should not submit if the user hasn't accepted the terms.

---

## How to Run the Tests

1. Open the project in **IntelliJ IDEA**.
2. Right-click on the `Registration.feature` file.
3. Select **Run** to execute the scenarios.

Make sure you have:
- Java 17+
- Chrome browser installed
- ChromeDriver in your system path

---

## Tools & Tech Used

- Java
- Cucumber
- Selenium WebDriver
- IntelliJ IDEA
- JUnit 4

---

##  Report

CI.pdf
