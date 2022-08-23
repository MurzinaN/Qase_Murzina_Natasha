package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class BaseTest {
    protected final static String USERNAME = "solodchenko13@gmail.com";
    protected final static String PASSWORD = "qa19qa19";
    protected final static String PROJECT_NAME = "sharelane";
    protected LoginPage LoginPage;
    Faker faker = new Faker();

    @BeforeClass
    public void setUp() {
        LoginPage = new LoginPage();
    }

    @BeforeMethod
    public void navigate() {
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;

    }
}
