import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.MainPageObject;


@RunWith(Parameterized.class)
public class MainPageTest extends CommonTest {

    private final String question;
    private final String answer;

    private MainPageObject mainPageObject;


    public MainPageTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void setup() {
        mainPageObject = new MainPageObject(getDriver());
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. " +
                                "Если хотите покататься с друзьями, можете просто сделать несколько " +
                                "заказов — один за другим."
                },
                {
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. " +
                                "Мы привозим самокат 8 мая в течение дня. " +
                                "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                                "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая " +
                                "в 20:30."
                },
                {
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                },
                {
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                                "по красивому номеру 1010."
                },
                {
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                                "даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                },
                {
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, " +
                                "объяснительной записки тоже не попросим. Все же свои."
                },
                {
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                }
        };
    }

    @Test
    public void accordionTextShownTest() {
        mainPageObject.clickAcceptCookieButton().clickQuestionByText(this.question);
        boolean isAnswerShown = mainPageObject.checkAnswerIsShown(this.question);
        Assert.assertTrue(
                String.format("После нажатия на вопрос: '%s' не появился элемент с ответом", question),
                isAnswerShown
        );
        String answerTextByQuestion = mainPageObject.getAnswerTextByQuestion(question);
        Assert.assertEquals(
                String.format("Текст ответа по вопросу: '%s' не соответствует ожидаемому", question),
                answer,
                answerTextByQuestion
        );
    }
}
