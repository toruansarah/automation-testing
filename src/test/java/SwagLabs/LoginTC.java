package SwagLabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTC {
    @Test
    public void success_login_case(){
        WebDriver driver;
        String baseUrl="https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();
        //chromedriver setup
        driver = new ChromeDriver();
        //open browser
        driver.manage().window().maximize();
        //open url
        driver.get(baseUrl);
        //assert loginpage
        String loginPage = driver.findElement(By.xpath("//h4[contains(text(),'Accepted usernames are:')]")).getText();
        Assert.assertEquals(loginPage,"Accepted usernames are:");
        //input username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //input valid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click login
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //assert homepage
        String homepage = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
        Assert.assertEquals(homepage,"Products");
        //exit
        driver.close();
    }

    @Test
    public void failed_login_case(){
        WebDriver driver;
        String baseUrl="https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();
        //chromedriver setup
        driver = new ChromeDriver();
        //open browser
        driver.manage().window().maximize();
        //timeout browser
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //open url
        driver.get(baseUrl);
        //assert loginpage
        String loginPage = driver.findElement(By.xpath("//h4[contains(text(),'Accepted usernames are:')]")).getText();
        Assert.assertEquals(loginPage,"Accepted usernames are:");
        //input email
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //input invalid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce_invalid");
        //click login
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String errorLogin = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        //String errorLogin = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")).getText();
        Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}
