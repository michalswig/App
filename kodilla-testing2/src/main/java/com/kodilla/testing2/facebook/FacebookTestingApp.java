package com.kodilla.testing2.facebook;

import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class FacebookTestingApp {
    public static final String XPATH_ACCEPT = "//button[2]";
    public static final String XPATH_BUTTON_NEW_ACCOUNT = "//div[@class='_6ltg']/a[@data-testid='open-registration-form-button']";

    public static void main(String[] args) {
        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.FIREFOX);
        driver.get("https://pl-pl.facebook.com/");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(XPATH_ACCEPT)).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath(XPATH_BUTTON_NEW_ACCOUNT)).click();
        driver.findElement(By.name("firstname")).sendKeys("Adam");
        driver.findElement(By.name("lastname")).sendKeys("Nowak");
        driver.findElement(By.name("reg_email__")).sendKeys("a.nowak@wp.pl");
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("a.nowak@wp.pl");
        driver.findElement(By.id("password_step_input")).sendKeys("adamnowak");
        Select day = new Select(driver.findElement(By.xpath("//select[@id='day']")));
        day.selectByVisibleText("10");
        Select month = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        month.selectByVisibleText("sty");
        Select year = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        year.selectByVisibleText("1989");
        driver.findElement(By.xpath("//input[@value='2']")).click();
        driver.findElement(By.name("websubmit")).click();

    }
}