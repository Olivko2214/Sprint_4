import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import rule.browser.BrowserRule;
import rule.browser.BrowserType;

public class CommonTest {

    private static final String START_PAGE = "https://qa-scooter.praktikum-services.ru/";

    @Rule
    public BrowserRule browserRule = new BrowserRule().browserType(BrowserType.CHROME);

    @Before
    public void setupCommon() {
        getDriver().get(START_PAGE);
    }

    protected WebDriver getDriver() {
        return this.browserRule.getWebDriver();
    }
}
