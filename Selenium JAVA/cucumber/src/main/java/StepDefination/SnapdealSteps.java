package StepDefination;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SnapdealSteps {
    WebDriver driver;

    @Given("launch the browser")
    public void launch_the_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("navigate to the Snapdeal website")
    public void navigate_to_the_snapdeal_website() {
        driver.get("https://www.snapdeal.com/");
    }

    @When("search for a specific product that exists")
    public void search_for_a_specific_product_that_exists() {
        String productName = "Divinext Plastic Assorted Tally Counting Digital Machine Finger Watch One Size\r\n";
        System.out.println("Searching for the product:"+productName);
        driver.findElement(By.id("inputValEnter")).sendKeys(productName);

    }

    @When("search for a specific product that does not exist")
    public void search_for_a_specific_product_that_does_not_exist() {
        String productName = "I PHONE 20";
        driver.findElement(By.id("inputValEnter")).sendKeys(productName);

    }

    @Then("verify the product is found")
    public void verify_the_product_is_found() {
        if (!driver.findElements(By.xpath("//*[@id=\"660206463981\"]/div[1]/a/picture/img")).isEmpty()) {
            System.out.println("Product Found");

        } else {
            System.out.println("Product Not Found");
        }
    }

    @Then("add the product to the cart")
    public void add_the_product_to_the_cart() {

        driver.findElement(By.xpath("//*[@id=\"660206463981\"]/div[1]/a/picture/img")).click();

        // Switch to the new window
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        driver.findElement(By.id("add-cart-button-id")).click();
        System.out.println("Product added to the cart!");
    }

    @Then("print the cart value")
    public void print_the_cart_value()
    {
        WebElement cartValue = driver.findElement(By.xpath("//*[@id=\"cartProductContainer\"]/div/div[2]/div[2]/div/div[1]/div[2]/span"));
        System.out.println("Cart Value: " + cartValue.getText());
    }


    @Then("close the browser")
    public void close_the_browser()
    {
        driver.quit();
    }
}

