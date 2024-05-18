package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageObject {

    private static final String QUESTION_BY_TEXT_XPATH = ".//div[text()='%s']";
    private static final String ANSWER_RELATIVE_TO_QUESTION_XPATH = "./../following-sibling::div";

    private final WebDriver webDriver;

    //Кнопка для принятия куков
    private final By acceptCookieButton = By.id("rcc-confirm-button");
    //Первый вопрос из аккордеона вопросов о важном
    private final By firstAccordionHeading = By.id("accordion__heading-0");
    //Текст, открывающийся после клика на первый вопрос из аккордеона вопросов о важном
    private final By firstAccordionPanel = By.id("accordion__panel-0");
    //Кнопка Заказать из заголовка
    private final By headerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Кнопка Заказать с основной страницы
    private final By mainPageOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    public MainPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public MainPageObject clickAcceptCookieButton() {
        webDriver.findElement(acceptCookieButton).click();
        return this;
    }

    public MainPageObject clickQuestionByText(String question) {
        webDriver.findElement(By.xpath(String.format(QUESTION_BY_TEXT_XPATH, question))).click();
        return this;
    }

    public boolean checkAnswerIsShown(String question) {
        return webDriver.findElement(By.xpath(String.format(QUESTION_BY_TEXT_XPATH, question)))
                .findElement(By.xpath(ANSWER_RELATIVE_TO_QUESTION_XPATH)).isDisplayed();
    }

    public String getAnswerTextByQuestion(String question) {
        return webDriver.findElement(By.xpath(String.format(QUESTION_BY_TEXT_XPATH, question)))
                .findElement(By.xpath(ANSWER_RELATIVE_TO_QUESTION_XPATH)).getText();
    }

    public boolean isFirstAccordionPanelShown() {
        return webDriver.findElement(firstAccordionPanel).isDisplayed();
    }

    public MainPageObject clickHeaderOrderButton() {
        webDriver.findElement(headerOrderButton).click();
        return this;
    }

    public MainPageObject clickMainPageOrderButton() {
        webDriver.findElement(mainPageOrderButton).click();
        return this;
    }
}
