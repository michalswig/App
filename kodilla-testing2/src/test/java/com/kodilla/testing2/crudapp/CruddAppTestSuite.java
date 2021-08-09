package com.kodilla.testing2.crudapp;

import com.kodilla.testing2.config.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CruddAppTestSuite {

    private static final String BASE_URL = "https://michalswig.github.io/";
    private WebDriver driver;
    private Random generator = new Random();

    @BeforeEach
    public void initTests() {
        driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void cleanUpAfterTest() {
        driver.close();
    }

    private String createCrudAppTestTask() throws InterruptedException {
        final String XPATH_TASK_NAME = "//form[contains(@action,\"createTask\")]/fieldset[1]/input";
        final String XPATH_TASK_CONTENT = "//form[contains(@action,\"createTask\")]/fieldset[2]/textarea";
        final String XPATH_ADD_BUTTON = "//form[contains(@action,\"createTask\")]/fieldset[3]/button";
        String taskName = "Task number " + generator.nextInt(1000);
        String taskContent = taskName + " content";

        Thread.sleep(4000);

        WebElement name = driver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = driver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = driver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();
        Thread.sleep(4000);

        return taskName;
    }

    private void sendTestTaskToTrello(String taskName) throws InterruptedException {
        driver.navigate().refresh();

        while (!driver.findElement(By.xpath("//select[1]")).isDisplayed()) ;

        driver.findElements(
                        By.xpath("//form[@class=\"datatable__row\"]")).stream()
                .filter(anyForm ->
                        anyForm.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]"))
                                .getText().equals(taskName))
                .forEach(theForm -> {
                    WebElement selectElement = theForm.findElement(By.xpath(".//select[1]"));
                    Select select = new Select(selectElement);
                    select.selectByIndex(1);

                    WebElement buttonCreateCard =
                            theForm.findElement(By.xpath(".//button[contains(@class, \"card-creation\")]"));
                    buttonCreateCard.click();
                });
        Thread.sleep(4000);
    }

    private boolean checkTaskExistsInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URL = "https://trello.com/login";
        boolean result = false;
        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URL);

        driverTrello.findElement(By.id("user")).sendKeys("m.swigost@gmail.com");
        driverTrello.findElement(By.id("password")).sendKeys("Wojtek19");
        WebElement el = driverTrello.findElement(By.id("login"));
        el.submit();

        Thread.sleep(4000);

        driverTrello.findElement(By.id("password")).sendKeys("Wojtek19");
        driverTrello.findElement(By.id("login-submit")).submit();

        Thread.sleep(4000);

        driverTrello.findElements(By.xpath("//a[@class=\"board-tile mod-light-background\"]")).stream()
                .filter(aHref -> aHref.findElements(By.xpath(".//div[@title=\"Kodilla Application\"]")).size() > 0)
                .forEach(WebElement::click);

        Thread.sleep(4000);

        result =
                driverTrello.findElements(By.xpath("//div[@class=\"list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable\"]")).stream()
                        .anyMatch(theSpan -> theSpan.getText().equals(taskName));
        Thread.sleep(4000);
        driverTrello.close();

        return result;
    }

    private void cleanCrudTask(String taskName) throws InterruptedException {
        driver.navigate().refresh();

        while (!driver.findElement(By.xpath(("//select[1]"))).isDisplayed()) ;

        driver.findElements(By.xpath("//form[@class=\"datatable__row\"]")).stream()
                .filter(aHref -> aHref.findElement(By.xpath(".//p[@class=\"datatable__field-value\"]")).getText().equals(taskName))
                .forEach(aHref1 -> {
                    WebElement deleteButton = aHref1.findElement(By.xpath(".//fieldset[contains(@class, \"datatable__row-section datatable__row-section--button-section\")]/button[4]"));
                    deleteButton.click();
                });

        Thread.sleep(4000);
    }

    @Test
    public void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = createCrudAppTestTask();
        sendTestTaskToTrello(taskName);
        assertTrue(checkTaskExistsInTrello(taskName));
        cleanCrudTask(taskName);
    }


}
