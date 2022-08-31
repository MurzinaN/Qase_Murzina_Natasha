package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.PropertyReader;

public class BaseTest {
    protected final static String USERNAME = System.getenv().getOrDefault("USERNAME", PropertyReader.getProperty("qace.username"));
    protected final static String PASSWORD = System.getenv().getOrDefault("PASSWORD", PropertyReader.getProperty("qace.password"));
    protected final static String PROJECT_NAME = "sharelane";
    protected LoginPage loginPage;
    Faker faker = new Faker();

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void navigate() {
        Configuration.baseUrl = PropertyReader.getProperty("qace.base_url");
        Configuration.timeout = 10000;

    }
}
