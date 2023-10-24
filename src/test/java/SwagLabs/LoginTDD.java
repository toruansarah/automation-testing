package SwagLabs;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTDD {
    //login using TDD
    @Test
    public void loginTDD(){
        WebDriver driver;
        String baseUrl="https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))){
            String[] nextLine;
            while ((nextLine=reader.readNext())!=null){
                String email = nextLine[0]; //read col 1 for email
                String password = nextLine[1]; //read col 2 for password
                String status = nextLine[2]; //read col 3 for expected login status

                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //set timeout for waitdriver on waiting element
                driver.manage().window().maximize();
                driver.get(baseUrl);
                //interaksi element
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//input[@type='submit']")).click();
                //assert
                if(status.equals("success")){
                    driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
                    String username = driver.findElement(By.xpath("//span[contains(text(),'Products')]")).getText();
                    Assert.assertEquals(username,"secret_sauce");//usernamenya gaada
                }
                else {
                    String errorLogin = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
                    Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");

                }

            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
