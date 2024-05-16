package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageObject {

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

    public void clickAcceptCookieButton() {
        webDriver.findElement(acceptCookieButton).click();
    }

    public void clickFirstAccordionHeading() {
        webDriver.findElement(firstAccordionHeading).click();
    }

    public boolean isFirstAccordionPanelShown() {
        return webDriver.findElement(firstAccordionPanel).isDisplayed();
    }

    public void clickHeaderOrderButton() {
        webDriver.findElement(headerOrderButton).click();
    }

    public void clickMainPageOrderButton() {
        webDriver.findElement(mainPageOrderButton).click();
    }
}
