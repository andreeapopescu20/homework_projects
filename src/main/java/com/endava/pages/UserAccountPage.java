package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by andpopescu on 11/24/2016.
 */
public class UserAccountPage {
    private WebDriver driver;

    public UserAccountPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='tabs-wrapper']//a[contains(.,'Edit')]")
    private WebElement editDetailsButton;

    @FindBy(xpath = "//div[@id='tabs-wrapper']/h1")
    private WebElement userName;

    @FindBy(id = "edit-mail")
    private WebElement emailField;

    @FindBy(xpath = "//a[contains(.,'Log out')]")
    private WebElement logOutButton;


    public void checkUserInfo(String username, String email){
        editDetailsButton.click();

        if(driver.getCurrentUrl().contains("overlay")){
            driver.switchTo().defaultContent();
            WebElement frame = driver.findElement(By.xpath("//div[@id='overlay-container']/iframe[contains(@class,'overlay-active')]"));
            driver.switchTo().frame(frame);
        }

        String name = userName.getText();
        String mail = emailField.getAttribute("value");

        boolean rolePresent;
        try {
            driver.findElement(By.id("edit-roles-3"));
            rolePresent = true;
        } catch (NoSuchElementException e) {
            rolePresent = false;
        }

        if(rolePresent == true) {
            System.out.println("User " + name + " is administrator!");
        }
        else{
            System.out.println("User " + name + " is not administrator!");
        }

        if(name.equals(username)&& mail.equals(email)){
            System.out.println("The info for this user is correct!");
        }
    }

    public LogInPage logOut(){
        driver.switchTo().defaultContent();
        logOutButton.click();
        LogInPage logInPage = PageFactory.initElements(driver, LogInPage.class);
        return logInPage;
    }
}
