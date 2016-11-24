package com.endava.pages;

import javafx.scene.web.WebView;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by andpopescu on 11/23/2016.
 */
public class CreateUserPage {
    private WebDriver driver;

    public CreateUserPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "edit-name")
    private WebElement usernameField;

    @FindBy(id = "edit-mail")
    private WebElement emailField;

    @FindBy(id = "edit-pass-pass1")
    private WebElement passwordField;

    @FindBy(id = "edit-pass-pass2")
    private WebElement confirmPasswordField;

    @FindBy(id = "edit-submit")
    private WebElement createButton;

    @FindBy(id="edit-roles-3")
    private WebElement role;

    @FindBy(xpath = "//div[@class='breadcrumb']/a[@title='Manage user accounts, roles, and permissions.']")
    private WebElement peoplePageButton;

    public void createNewUser(String username, String email, String password, Boolean isAdmin) {
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        if (isAdmin) {
            role.click();
        }
        createButton.submit();
    }

    /*
    public PeoplePage goBackToPeoplePage(){
        peoplePageButton.click();

        driver.switchTo().defaultContent();
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='overlay-element']"));
        driver.switchTo().frame(frame);

        PeoplePage peoplePage = PageFactory.initElements(driver, PeoplePage.class);
        return peoplePage;
    }
    */

    @FindBy(xpath = "//*[@id='toolbar-user']//a[contains(.,'Log out')]")
    private WebElement logOutButton;

    public LogInPage logOut(){
        driver.switchTo().defaultContent();
        logOutButton.click();
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        return logInPage;

    }
}


