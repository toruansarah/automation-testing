package SwagLabs.cucumber.stepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl="https://www.saucedemo.com/";
    @Given("Load homepage")
    public void load_homepage(){
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginPage = driver.findElement(By.xpath("//h4[contains(text(),'Accepted usernames are:')]")).getText();
        Assert.assertEquals(loginPage,"Accepted usernames are:");
    }
    @When("input valid username")
    public void input_valid_username(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }
    @And("input valid password")
    public void input_valid_password(){
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }
    @And("clicks Login button")
    public void clicks_Login_button(){
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @Then("Navigated to homepage")
    public void Navigated_to_homepage(){
        String homepage = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        Assert.assertEquals(homepage,"Products");
    }
}
