package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I am on the New Supporter Account registration page")
    public void iAmOnTheNewSupporterAccountRegistrationPage() {
        driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().maximize();
    }

    @When("I fill in all required details correctly")
    public void iFillInAllRequiredDetailsCorrectly() throws InterruptedException {

        String year = "1988";
        String month = "Jun";
        String day   = "6";

        // Open Date Picker
        WebElement birthDateDropdown = driver.findElement(By.xpath("//input[@id='dp']"));
        birthDateDropdown.click();
        Thread.sleep(1000); // Allow UI to update

        // Click the calendar header to switch to month selection
        driver.findElement(By.xpath("//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        // Click the header again (now showing the year) to switch to year selection
        driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        // Navigate years until the target year is visible
        while (driver.findElements(By.xpath("//span[text()='" + year + "']")).isEmpty()) {
            driver.findElement(By.xpath("//div[@class='datepicker-years']//th[@class='prev']")).click();
            Thread.sleep(500);
        }
        driver.findElement(By.xpath("//span[text()='" + year + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[normalize-space()='" + month + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//td[normalize-space()='" + day + "']")).click();
        Thread.sleep(500);

        driver.findElement(By.id("member_firstname")).sendKeys("Joe");
        driver.findElement(By.id("member_lastname")).sendKeys("Doe");
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser9@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser9@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
    }

    @And("I accept the Terms and Conditions")
    public void iAcceptTheTermsAndConditions() {
        // Accept "Terms and Conditions"
        WebElement termsCheckbox = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/label/span[3]"));
        termsCheckbox.click();

        // Accept "I am over 18 years old"
        WebElement over18Checkbox = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[2]/label/span[3]"));
        over18Checkbox.click();

        // Accept "Code of Ethics and Conduct"
        WebElement ethicsCheckbox = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label/span[3]"));
        ethicsCheckbox.click();
    }

    @And("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        // Click "Confirm and Join"
        WebElement confirmButton = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[12]/input"));
        confirmButton.click();
    }

    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        // Wait for the heading to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement thankYouHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THANK YOU FOR CREATING AN ACCOUNT')]"))
        );

        assertTrue(thankYouHeading.isDisplayed());

        // Optionally print the Membership Number
        WebElement membershipNumber = driver.findElement(By.xpath("//h2[contains(text(),'A')]"));
        System.out.println("Account created. Membership number: " + membershipNumber.getText());

        //driver.quit();

    }

    @When("I fill in required details except the last name")
    public void iFillInRequiredDetailsExceptTheLastName() throws InterruptedException {
        String year = "1988";
        String month = "Jun";
        String day   = "6";

        WebElement birthDateDropdown = driver.findElement(By.xpath("//input[@id='dp']"));
        birthDateDropdown.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        while (driver.findElements(By.xpath("//span[text()='" + year + "']")).isEmpty()) {
            driver.findElement(By.xpath("//div[@class='datepicker-years']//th[@class='prev']")).click();
            Thread.sleep(500);
        }
        driver.findElement(By.xpath("//span[text()='" + year + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[normalize-space()='" + month + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//td[normalize-space()='" + day + "']")).click();
        Thread.sleep(500);

        driver.findElement(By.id("member_firstname")).sendKeys("Joe");
        // No last name
        driver.findElement(By.id("member_emailaddress")).sendKeys("missinglastname1@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("missinglastname1@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
    }

    @Then("the account should not be created")
    public void theAccountShouldNotBeCreated() {
        String currentUrl = driver.getCurrentUrl();
        // Still on registration page = fail to register = test passes
        assertTrue(currentUrl.contains("NewSupporterAccount"));

        // driver.quit();
    }

    @When("I fill in all required details except matching passwords")
    public void iFillInAllRequiredDetailsExceptMatchingPasswords() throws InterruptedException {
        String year = "1988";
        String month = "Jun";
        String day = "6";

        WebElement birthDateDropdown = driver.findElement(By.xpath("//input[@id='dp']"));
        birthDateDropdown.click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']")).click();
        Thread.sleep(700);
        while (driver.findElements(By.xpath("//span[text()='" + year + "']")).isEmpty()) {
            driver.findElement(By.xpath("//div[@class='datepicker-years']//th[@class='prev']")).click();
            Thread.sleep(500);
        }
        driver.findElement(By.xpath("//span[text()='" + year + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[normalize-space()='" + month + "']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//td[normalize-space()='" + day + "']")).click();
        Thread.sleep(500);

        driver.findElement(By.id("member_firstname")).sendKeys("Joe");
        driver.findElement(By.id("member_lastname")).sendKeys("Doe");
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser_mismatch@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser_mismatch@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("DifferentPassword!");
    }

    @Then("I should see a password mismatch error")
    public void iShouldSeeAPasswordMismatchError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Password did not match')]")
        ));
        assertTrue(error.isDisplayed());
        System.out.println("Password mismatch validation displayed.");
        // driver.quit();
    }

    @And("I do not accept the Terms and Conditions")
    public void iDoNotAcceptTheTermsAndConditions() {
        // Only tick the "I am over 18" and "Code of Ethics", but leave out Terms and Conditions
        WebElement over18Checkbox = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[2]/label/span[3]"));
        over18Checkbox.click();

        WebElement ethicsCheckbox = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label/span[3]"));
        ethicsCheckbox.click();
    }

}
