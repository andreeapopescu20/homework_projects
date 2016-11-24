package com.endava.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(id="edit-roles-3")
    private WebElement role;

    public void checkUserInfo(String username, String email){
        editDetailsButton.click();

        if(driver.getCurrentUrl().equals("http://192.168.100.125/drupal-7.15/?q=user#overlay=%3Fq%3Duser%252F3%252Fedit")){
            WebElement frame = driver.findElement(By.xpath("//iframe[contains(@class,'overlay-active')]"));
            driver.switchTo().frame(frame);
        }
        String name = userName.getText();
        //System.out.println(name);
        String mail = emailField.getAttribute("value");
        //System.out.println(mail);

        if((!role.isDisplayed())&&(name.equals(username))&&(mail.equals(email))){
            System.out.println("The info is correct for this user");
        }


    }

}
