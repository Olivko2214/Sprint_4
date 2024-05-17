package pom;

import model.RentalPeriod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SecondOrderPageObject {

    private static final DateTimeFormatter RUSSIAN_DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private final WebDriver webDriver;
    //Поле ввода даты доставка
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выпадающее меню выбора срока аренды
    private final By rentalPeriod = By.xpath(".//div[text()='* Срок аренды']");
    //Кнопка Заказать под формой заполнения данных
    private final By completeOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Кнопка подтверждения заказа во всплывающем окне
    private final By confirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Заголовок окна с сообщением об успешном создании заказа
    private final By orderModalHelper = By.className("Order_ModalHeader__3FDaJ");


    public SecondOrderPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SecondOrderPageObject fillOrderForm(LocalDate deliveryDate, RentalPeriod period) {
        return fillDeliveryDate(deliveryDate).
                fillRentalPeriod(period);
    }

    public SecondOrderPageObject fillDeliveryDate(LocalDate deliveryDate) {
        webDriver.findElement(this.deliveryDate).sendKeys(
                RUSSIAN_DATE_FORMATTER.format(deliveryDate),
                Keys.RETURN
        );
        return this;
    }

    public SecondOrderPageObject fillRentalPeriod(RentalPeriod period) {
        webDriver.findElement(rentalPeriod).click();
        webDriver.findElement(
                        By.xpath(
                                ".//div[@class='Dropdown-option' and text()='" + period.getText() + "']"
                        )
                )
                .click();
        return this;
    }

    public SecondOrderPageObject clickOrderButton() {
        webDriver.findElement(completeOrder).click();
        return this;
    }

    public SecondOrderPageObject clickConfirmOrderButton() {
        webDriver.findElement(confirmOrderButton).click();
        return this;
    }

    public boolean isOrderModalHelperTextShown() {
        return webDriver.findElement(orderModalHelper).isDisplayed();
    }

    public String getOrderModalHelperText() {
        return webDriver.findElement(orderModalHelper).getText();
    }
}
