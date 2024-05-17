import model.OrderData;
import model.RentalPeriod;
import model.UserData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.FirstOrderPageObject;
import pom.MainPageObject;
import pom.SecondOrderPageObject;

import java.time.LocalDate;

@RunWith(Parameterized.class)
public class SuccessOrderTest extends CommonTest {

    private static final String EXPECTED_MODAL_HELPER_TEXT = "Заказ оформлен";

    private MainPageObject mainPageObject;
    private FirstOrderPageObject firstOrderPageObject;
    private SecondOrderPageObject secondOrderPageObject;

    private final UserData userData;
    private final OrderData orderData;


    public SuccessOrderTest(UserData userData, OrderData orderData) {
        this.userData = userData;
        this.orderData = orderData;
    }

    @Before
    public void setup() {
        mainPageObject = new MainPageObject(getDriver());
        firstOrderPageObject = new FirstOrderPageObject(getDriver());
        secondOrderPageObject = new SecondOrderPageObject(getDriver());
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {
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
        mainPageObject.clickAcceptCookieButton()
                .clickHeaderOrderButton();
        firstOrderPageObject.fillOrderForm(
                        userData.getName(),
                        userData.getSecondName(),
                        userData.getAddress(),
                        userData.getMetroStation(),
                        userData.getPhoneNumber()
                )
                .clickNextButton();
        secondOrderPageObject.fillOrderForm(
                        orderData.getDeliveryDate(),
                        orderData.getPeriod()
                )
                .clickOrderButton()
                .clickConfirmOrderButton();
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
        mainPageObject.clickAcceptCookieButton()
                .clickMainPageOrderButton();
        firstOrderPageObject.fillOrderForm(
                        userData.getName(),
                        userData.getSecondName(),
                        userData.getAddress(),
                        userData.getMetroStation(),
                        userData.getPhoneNumber()
                )
                .clickNextButton();
        secondOrderPageObject.fillOrderForm(
                        orderData.getDeliveryDate(),
                        orderData.getPeriod()
                )
                .clickOrderButton()
                .clickConfirmOrderButton();
        Assert.assertTrue(
                "Всплывающее окно с сообщением об успешном создании заказа не обнаружено",
                secondOrderPageObject.isOrderModalHelperTextShown()
        );
        Assert.assertTrue(
                "Текст всплывающего окна с сообщением об успешном создании заказа не соответствует ожидаемому.",
                secondOrderPageObject.getOrderModalHelperText().contains(EXPECTED_MODAL_HELPER_TEXT)
        );
    }
}
