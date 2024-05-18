package rule.browser;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BrowserRule extends ExternalResource {

    private BrowserType browserType;
    private WebDriver webDriver;

    @Override
    protected void before() {
        switch (browserType) {
            case FIREFOX:
                webDriver = new FirefoxDriver();
                return;
            case CHROME:
            default:
                webDriver = new ChromeDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.of(3, ChronoUnit.SECONDS));
    }

    @Override
    protected void after() {
        webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public BrowserRule browserType(BrowserType browserType) {
        this.browserType = browserType;
        return this;
    }
}
