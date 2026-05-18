package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Locators
    private By accountsOverviewTitle = By.xpath("//h1[@class='title' and contains(text(),'Accounts Overview')]");
    private By welcomeMessage = By.cssSelector(".smallText");
    private By logoutLink = By.linkText("Log Out");
    private By accountsTable = By.id("accountTable");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAccountsOverviewVisible() {
        return driver.findElement(accountsOverviewTitle).isDisplayed();
    }

    public String getAccountsOverviewTitleText() {
        return driver.findElement(accountsOverviewTitle).getText();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public String getWelcomeMessageText() {
        return driver.findElement(welcomeMessage).getText();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isAccountsTableVisible() {
        return driver.findElement(accountsTable).isDisplayed();
    }

    public boolean isHomePageDisplayed() {
        return driver.findElements(accountsOverviewTitle).size() > 0;
    }
}
