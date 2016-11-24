package com.endava.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

/**
 * Created by andpopescu on 11/22/2016.
 */
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[@id='toolbar-user']/li[@class = 'account first']/a")
    private WebElement userAccount;

    @FindBy(id = "toolbar-link-admin-people")
    private WebElement peopleMenu;

    @FindBy(xpath = "//div[@id='header']//a[contains(.,'My account')]")
    private WebElement myAccountButton;

    public void checkLogIn(String welcomeMessage) {
        Assert.assertTrue(userAccount.getText().equals(welcomeMessage));
    }

    public PeoplePage manageUsers() {
        peopleMenu.click();
        WebElement frameSwitch = driver.findElement(By.xpath("//iframe[contains(@class,'overlay-active')]"));
        driver.switchTo().frame(frameSwitch);
        PeoplePage peoplePage = PageFactory.initElements(driver, PeoplePage.class);
        return peoplePage;
    }

    public UserAccountPage goToAccount(){
        myAccountButton.click();
        UserAccountPage userAccountPage = PageFactory.initElements(driver, UserAccountPage.class);
        return userAccountPage;
    }
}


