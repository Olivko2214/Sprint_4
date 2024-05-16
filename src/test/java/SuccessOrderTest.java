import io.github.bonigarcia.wdm.WebDriverManager;
import model.OrderData;
import model.RentalPeriod;
import model.UserData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.FirstOrderPageObject;
import pageobject.MainPageObject;
import pageobject.SecondOrderPageObject;

import java.time.LocalDate;

@RunWith(Parameterized.class)
public class SuccessOrderTest {

    private static final String START_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private static final String EXPECTED_MODAL_HELPER_TEXT = "Заказ оформлен";


    private final WebDriverManager webDriverManager;
    private MainPageObject mainPageObject;
    private FirstOrderPageObject firstOrderPageObject;
    private SecondOrderPageObject secondOrderPageObject;

    private final UserData userData;
    private final OrderData orderData;

    public SuccessOrderTest(WebDriverManager webDriverManager, UserData userData, OrderData orderData) {
        this.webDriverManager = webDriverManager;
        this.userData = userData;
        this.orderData = orderData;
    }

    @Before
    public void setup() {
        WebDriver driver = webDriverManager.create();
        driver.get(START_PAGE);
        mainPageObject = new MainPageObject(driver);
        firstOrderPageObject = new FirstOrderPageObject(driver);
        secondOrderPageObject = new SecondOrderPageObject(driver);
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        WebDriverManager chromedriverManager = WebDriverManager.chromedriver();
        chromedriverManager.setup();

        WebDriverManager firefoxdriverManager = WebDriverManager.firefoxdriver();
        firefoxdriverManager.setup();

        return new Object[][]{
                {
                        chromedriverManager,
                        new UserData(
                                "Ольга",
                                "Ивкова",
                                "Улица дом",
                                "Боровицкая",
                                "+79999999999"
                        ),
                        new OrderData(
                                LocalDate.now().plusDays(1),
                                RentalPeriod.ONE_DAY
                        )
                },
                {
                        firefoxdriverManager,
                        new UserData(
                                "Иван",
                                "Иванов",
                                "Улица проживания Ивана",
                                "Сокольники",
                                "+79999999993"
                        ),
                        new OrderData(
                                LocalDate.now().plusDays(10),
                                RentalPeriod.SEVEN_DAYS
                        )
                }
        };
    }

    @Test
    public void successOrderFromHeaderButtonTest() {
        mainPageObject.clickAcceptCookieButton();
        mainPageObject.clickHeaderOrderButton();
        firstOrderPageObject.fillOrderForm(
                userData.getName(),
                userData.getSecondName(),
                userData.getAddress(),
                userData.getMetroStation(),
                userData.getPhoneNumber()
        );
        firstOrderPageObject.clickNextButton();
        secondOrderPageObject.fillOrderForm(
                orderData.getDeliveryDate(),
                orderData.getPeriod()
        );
        secondOrderPageObject.clickOrderButton();
        secondOrderPageObject.clickConfirmOrderButton();
        Assert.assertTrue(
                "Всплывающее окно с сообщением об успешном создании заказа не обнаружено",
                secondOrderPageObject.isOrderModalHelperTextShown()
        );
        Assert.assertTrue(
                "Текст всплывающего окна с сообщением об успешном создании заказа не соответствует ожидаемому.",
                secondOrderPageObject.getOrderModalHelperText().contains(EXPECTED_MODAL_HELPER_TEXT)
        );
    }

    @Test
    public void successOrderFromMainPageButtonTest() {
        mainPageObject.clickAcceptCookieButton();
        mainPageObject.clickMainPageOrderButton();
        firstOrderPageObject.fillOrderForm(
                userData.getName(),
                userData.getSecondName(),
                userData.getAddress(),
                userData.getMetroStation(),
                userData.getPhoneNumber()
        );
        firstOrderPageObject.clickNextButton();
        secondOrderPageObject.fillOrderForm(
                orderData.getDeliveryDate(),
                orderData.getPeriod()
        );
        secondOrderPageObject.clickOrderButton();
        secondOrderPageObject.clickConfirmOrderButton();
        Assert.assertTrue(
                "Всплывающее окно с сообщением об успешном создании заказа не обнаружено",
                secondOrderPageObject.isOrderModalHelperTextShown()
        );
        Assert.assertTrue(
                "Текст всплывающего окна с сообщением об успешном создании заказа не соответствует ожидаемому.",
                secondOrderPageObject.getOrderModalHelperText().contains(EXPECTED_MODAL_HELPER_TEXT)
        );
    }

    @After
    public void tearDown() {
        webDriverManager.quit();
    }
}
