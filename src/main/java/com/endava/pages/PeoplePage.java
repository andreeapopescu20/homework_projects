package com.endava.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by andpopescu on 11/22/2016.
 */
public class PeoplePage {
    private WebDriver driver;

    public PeoplePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[@class = 'action-links']/li/a")
    private WebElement addUserButton;

    public CreateUserPage addUser() {
        addUserButton.click();
        driver.switchTo().defaultContent();
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='overlay-element']"));
        driver.switchTo().frame(frame);
        CreateUserPage createUser = PageFactory.initElements(driver, CreateUserPage.class);
        return createUser;
    }
}

