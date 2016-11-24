package com.endava.pages;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by andpopescu on 11/22/2016.
 */
public class LogInPage {
    private WebDriver driver;

    public LogInPage(WebDriver driver){
        this.driver = driver;
        if(!this.driver.getCurrentUrl().startsWith("http")) {
            this.driver.get("http://192.168.100.125/drupal-7.15/");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @FindBy(id = "edit-name")
    private WebElement usernameField;

    @FindBy(id = "edit-pass")
    private WebElement passwordField;

    @FindBy(id = "edit-submit")
    private WebElement logInButton;

    public HomePage logIn(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        logInButton.click();
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        return homePage;
    }
}
