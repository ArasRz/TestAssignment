package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I am on the New Supporter Account registration page")
    public void iAmOnTheNewSupporterAccountRegistrationPage() {
        driver = new ChromeDriver(); // Assign the driver properly
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().maximize();
    }

    @When("I fill in all required details correctly")
    public void iFillInAllRequiredDetailsCorrectly() throws InterruptedException {
        // Open Date Picker
        WebElement birthDateDropdown = driver.findElement(By.xpath("//*[@id=\"dp\"]"));
        birthDateDropdown.click();
        Thread.sleep(1000); // Allow UI to update

        // Select Year, Month, Day
        /*WebElement year = driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/thead/tr[2]/th[2]"));
        year.click();
        Thread.sleep(1000);

        WebElement month = driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[6]"));
        month.click();
        Thread.sleep(1000);

        WebElement day = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[2]/td[2]"));
        day.click();
        */
        // Fill in required details
        driver.findElement(By.id("member_firstname")).sendKeys("Joe");
        driver.findElement(By.id("member_lastname")).sendKeys("Doe");
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser@example.com");
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
        // Validate successful registration message
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        assertTrue(successMessage.isDisplayed());

        driver.quit(); // Close browser after test
    }
}
