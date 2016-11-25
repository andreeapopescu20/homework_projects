import com.endava.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import javax.jws.soap.SOAPBinding;
import java.util.concurrent.TimeUnit;

/**
 * Created by andpopescu on 11/22/2016.
 */
public class DrupalTest {

    private WebDriver driver;
    private LogInPage logInPage;

    private String adminUserName = "admin";

    private String firstUserName = "NormalUser";
    private String firstUserPassword = "pass";
    private String firstUserEmail = "normaluser@test.com";

    private String secondUserName = "AdminUser";
    private String secondUserPassword = "pass";
    private String secondUserEmail = "adminuser@test.com";

    @Before
    public void before(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logInPage = PageFactory.initElements(driver, LogInPage.class);
    }

    @After
    public void after(){
        driver.close();
    }

    @Test
    public void test(){
        //login on site with an admin user
        HomePage homePage = logInPage.logIn(adminUserName,"admin_pass");
        //check if the login was successful
        homePage.checkLogIn("Hello " + adminUserName);

        //create 2 users from People Management page
        PeoplePage peoplePage = homePage.manageUsers();
        CreateUserPage createUser = peoplePage.addUser();
        //user without admin privileges
        createUser.createNewUser(firstUserName, firstUserEmail,firstUserPassword, false);
        //user with admin privileges
        createUser.createNewUser(secondUserName, secondUserEmail,secondUserPassword, true);

        //logout and login with the first user created
        logInPage = createUser.logOut();
        homePage = logInPage.logIn(firstUserName,firstUserPassword);
        UserAccountPage userAccountPage = homePage.goToAccount();
        //check first user info
        userAccountPage.checkUserInfo(firstUserName,firstUserEmail);
        logInPage = userAccountPage.logOut();
        //check in database if the user exists
        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.checkDataSet(firstUserName,firstUserEmail);

        //login with the second user and follow the same procedure
        homePage = logInPage.logIn(secondUserName,secondUserPassword);
        userAccountPage = homePage.goToAccount();
        userAccountPage.checkUserInfo(secondUserName,secondUserEmail);
        userAccountPage.logOut();
        dbConn.checkDataSet(secondUserName,secondUserEmail);
    }
}
