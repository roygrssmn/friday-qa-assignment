package com.friday.pages.car_insurance_wizard;

import com.friday.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.friday.framework.DataFactory.getCurrentDate;

public class CarInsuranceWizard extends BasePage {

    WebDriver driver;

    public CarInsuranceWizard(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Select precondition step
    @FindBy(css = "button[data-test-id = 'quoting.selectPrecondition.keepingCarquoting.selectPrecondition.keepingCar.subLine']")
    public WebElement checkboxCarIsInsured;

    @FindBy(css = "button[data-test-id = 'quoting.selectPrecondition.buyingCarquoting.selectPrecondition.buyingCar.subLine']")
    public WebElement checkBoxCarIsRegistered;

    @FindBy(css = "input[name = 'inceptionDate']")
    public WebElement inputFieldInceptionDate;

    //Select registered owner step
    @FindBy(css = "button[data-test-id = 'shared.yes']")
    public WebElement checkBoxCarSharedYes;

    @FindBy(css = "button[data-test-id = 'shared.no']")
    public WebElement checkBoxCarSharedNo;

    @FindBy(css = "button[data-test-id = 'quoting.selectRegisteredOwner.used']")
    public WebElement checkBoxCarUsed;

    @FindBy(css = "button[data-test-id = 'quoting.selectRegisteredOwner.brandNew']")
    public WebElement checkBoxCarBrandNew;

    //Select vehicle step
    @FindBy(css = "button[name='list']")
    public WebElement buttonSelectFromList;

    @FindBy(css = "input[name = 'makeFilter']")
    public WebElement inputFieldCarManufacturerFilter;

    @FindBy(css = "button[name='make']:nth-child(1)")
    public WebElement buttonFirstCarManufacturer;

    @FindBy(css = "input[name='monthYear']")
    public WebElement inputFieldMonthYear;

    @FindBy(css = "input[name='hsn']")
    public WebElement inputFieldHsn;

    @FindBy(css = "input[name='tsn']")
    public WebElement getInputFieldTsn;

    //Select model step
    @FindBy(css = "input[name='modelFilter']")
    public WebElement inputFieldModelFilter;

    @FindBy(css = "button[name='model']:nth-child(1)")
    public WebElement buttonFirstCarModel;

    //Select body type step
    @FindBy(css = "button[name='bodyType']:nth-child(1)")
    public WebElement buttonFirstBodyType;

    //Select fuel type step
    @FindBy(css = "button[name='fuelType']:nth-child(1)")
    public WebElement buttonFirstFuelType;

    //Select engine power step
    @FindBy(css = "button[name='enginePower']:nth-child(1)")
    public WebElement buttonFirstCarEnginePower;

    //Select engine
    @FindBy(css = "button[name='engine']:nth-child(1)")
    public WebElement buttonFirstCarEngine;

    //enter registration date step
    @FindBy(css = "input[name='monthYearFirstRegistered']")
    public WebElement inputFieldMonthYearFirstRegistered;

    @FindBy(css = "input[name='monthYearOwnerRegistered']")
    public WebElement inputFieldMonthYearOwnerRegistered;

    //enter birth date step
    @FindBy(css = "input[name='birthDate']")
    public WebElement inputFieldBirthDate;

    //wizard buttons
    @FindBy(css = "button[data-test-id='wizardBackButton']")
    public WebElement buttonBack;

    @FindBy(css = "button[type='submit']")
    public WebElement buttonNext;

    @FindBy(css = "a[href='/quote/selectVehicle']")
    public WebElement linkToHsnTsn;

    public void launchWizard(){
        driver.get("https://hello.friday.de/quote/selectPrecondition");
    }

    public void completeSelectPreconditionStep(boolean isInsured){
        waitForElementToBeVisible(driver, waitTimeout(), checkboxCarIsInsured);
        if (isInsured){
            clickOnElement(checkboxCarIsInsured);
        } else {
            clickOnElement(checkBoxCarIsRegistered);
        }
        sendTextToElement(inputFieldInceptionDate, getCurrentDate("ddMMyyyy"));
        clickOnElement(buttonNext);
    }

    public void completeSelectRegisteredOwnerStep(boolean isCarShared, boolean isCarNew){
        waitForElementToBeVisible(driver, waitTimeout(), checkBoxCarSharedNo);
        if (isCarShared){
            clickOnElement(checkBoxCarSharedYes);
        } else {
            clickOnElement(checkBoxCarSharedNo);
        }
        if(isCarNew){
            clickOnElement(checkBoxCarBrandNew);
        } else {
            clickOnElement(checkBoxCarUsed);
        }
        clickOnElement(buttonNext);
    }

    public void selectCarManufacturer(String manufacturer){
        waitForElementToBeVisible(driver, waitTimeout(), inputFieldCarManufacturerFilter);
        sendTextToElement(inputFieldCarManufacturerFilter, manufacturer);
        clickOnElement(buttonFirstCarManufacturer);
    }

    public void selectCarModel(String model){
        waitForElementToBeVisible(driver, waitTimeout(), inputFieldModelFilter);
        sendTextToElement(inputFieldModelFilter, model);
        clickOnElement(buttonFirstCarModel);
    }

    public void selectCarBodyType(){
        waitForElementToBeVisible(driver, waitTimeout(),buttonFirstBodyType);
        clickOnElement(buttonFirstBodyType);
    }

    public void selectFuelType(){
        waitForElementToBeVisible(driver, waitTimeout(), buttonFirstFuelType);
        clickOnElement(buttonFirstFuelType);

    }

    public void selectEnginePower(){
        waitForElementToBeVisible(driver, waitTimeout(), buttonFirstCarEnginePower);
        clickOnElement(buttonFirstCarEnginePower);
    }

    public void selectEngine(){
        waitForElementToBeVisible(driver, waitTimeout(), buttonFirstCarEngine);
        clickOnElement(buttonFirstCarEngine);
    }

    public void enterRegistrationDate(){
        waitForElementToBeVisible(driver, waitTimeout(), inputFieldMonthYearFirstRegistered);
        sendTextToElement(inputFieldMonthYearFirstRegistered, getCurrentDate("MMyyyy"));
        clickOnElement(buttonNext);
        waitForElementToBeVisible(driver, waitTimeout(), inputFieldBirthDate);
    }

    public void enterBirthDate(){
        waitForElementToBeVisible(driver, waitTimeout(), inputFieldBirthDate);
        sendTextToElement(inputFieldBirthDate, getCurrentDate("ddMMyyyy"));
    }
}
