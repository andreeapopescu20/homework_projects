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

    private String adminUsername = "admin";
    private String email = "fthhghg.Hahue@endava.com";

    private String firstUserName = "ovi8";
    private String firstUserPassword = "ovi8";
    private String firstUserEmail = "ovi8@ceva.com";

    @Before
    public void before(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logInPage = PageFactory.initElements(driver, LogInPage.class);
    }

    @After
    public void after(){
        //driver.close();
    }
    @Test
    public void test(){
    HomePage homePage = logInPage.logIn(adminUsername,"admin_pass");
    homePage.checkLogIn("Hello " + adminUsername);
    PeoplePage peoplePage = homePage.manageUsers();
    CreateUserPage createUser = peoplePage.addUser();

    createUser.createNewUser(firstUserName, firstUserEmail,firstUserPassword, false);
    logInPage = createUser.logOut();
    homePage = logInPage.logIn(firstUserName,firstUserPassword);
    UserAccountPage userAccountPage = homePage.goToAccount();
    userAccountPage.checkUserInfo(firstUserName,firstUserEmail);


    //createUser.createNewUser("andreeaAdmin", "cde@ceva.com","test123",true);


    //DatabaseConnection dbConn = new DatabaseConnection();
    //dbConn.checkDataSet(username,email);
    }
}
