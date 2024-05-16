import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.MainPageObject;


@RunWith(Parameterized.class)
public class MainPageTest {

    private static final String START_PAGE = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriverManager webDriverManager;

    private MainPageObject mainPageObject;


    public MainPageTest(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @Before
    public void setup() {
        WebDriver driver = webDriverManager.create();
        driver.get(START_PAGE);
        mainPageObject = new MainPageObject(driver);
    }

    @Parameterized.Parameters
    public static Object[] getParameters() {
        WebDriverManager chromedriverManager = WebDriverManager.chromedriver();
        chromedriverManager.setup();

        WebDriverManager firefoxdriverManager = WebDriverManager.firefoxdriver();
        firefoxdriverManager.setup();

        return new Object[]{
                chromedriverManager,
                firefoxdriverManager,
        };
    }

    @Test
    public void accordionTextShownTest() {
        mainPageObject.clickAcceptCookieButton();
        mainPageObject.clickFirstAccordionHeading();
        Assert.assertTrue(mainPageObject.isFirstAccordionPanelShown());
    }

    @After
    public void tearDown() {
        webDriverManager.quit();
    }
}
