package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstOrderPageObject {

    private final WebDriver webDriver;

    //Поле ввода имени
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By secondNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции метро
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле ввода номера телефона
    private final By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");


    public FirstOrderPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickNextButton() {
        webDriver.findElement(nextButton).click();
    }

    public void fillOrderForm(String name, String secondName, String address, String metroStation, String phoneNumber) {
        fillName(name);
        fillSecondName(secondName);
        fillAddress(address);
        fillAndSelectMetroStation(metroStation, 0);
        fillPhoneNumber(phoneNumber);
    }

    public void fillName(String name) {
        webDriver.findElement(nameInput).sendKeys(name);
    }

    public void fillSecondName(String secondName) {
        webDriver.findElement(secondNameInput).sendKeys(secondName);
    }

    public void fillAddress(String address) {
        webDriver.findElement(addressInput).sendKeys(address);
    }

    public void fillAndSelectMetroStation(String metroStation, int option) {
        webDriver.findElement(metroStationInput).sendKeys(metroStation);
        webDriver.findElement(
                        By.xpath(
                                ".//li[@class='select-search__row' and @data-index='" + option + "']"
                        )
                )
                .click();
    }

    public void fillPhoneNumber(String phoneNumber) {
        webDriver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
}
