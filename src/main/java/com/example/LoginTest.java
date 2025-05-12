package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:/Selenium/chromedriver-win64/chromedriver.exe"); // Update path as needed
        driver = new ChromeDriver();
    }

    @Test
    public void testSuccessfulLogin() {
        driver.get("http://localhost:5000/login.html");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Login successful!", result);
    }

    @Test
    public void testInvalidUsername() {
        driver.get("http://localhost:5000/login.html");
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Invalid username or password.", result);
    }

    @Test
    public void testInvalidPassword() {
        driver.get("http://localhost:5000/login.html");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        String result = driver.findElement(By.id("result")).getText();
        assertEquals("Invalid username or password.", result);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}