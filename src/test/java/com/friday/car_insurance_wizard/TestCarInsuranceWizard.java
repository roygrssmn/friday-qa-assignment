package com.friday.car_insurance_wizard;

import com.friday.framework.WebDriverExtension;
import com.friday.framework.WebDriverTest;
import com.friday.pages.car_insurance_wizard.CarInsuranceWizard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.stream.Stream;

@ExtendWith(WebDriverExtension.class)
public class TestCarInsuranceWizard extends WebDriverTest {

    CarInsuranceWizard wizard;
    WebDriver driver;

    private static Stream<Arguments> createCarManufacturersAndModels(){
        return Stream.of(
                Arguments.of("VW", "Passat"),
                Arguments.of("VW", "Caddy"),
                Arguments.of("VW", "Transporter"),
                Arguments.of("TOYOTA", "Camry"),
                Arguments.of("TOYOTA", "Carina"),
                Arguments.of("TOYOTA", "Avensis"),
                Arguments.of("FORD", "Escort"),
                Arguments.of("FORD", "Focus"),
                Arguments.of("FORD", "Mondeo")
        );
    }

    @BeforeEach
    public void setup() throws MalformedURLException {
        wizard = new CarInsuranceWizard(getDriver());
        wizard.launchWizard();
        driver = wizard.getDriver();
    }

    @ParameterizedTest(name = "Manufacturer_{0}_model_{1}")
    @MethodSource("createCarManufacturersAndModels")
    public void testCarInsuranceWizard(String carManufacturer, String carModel){
        wizard.completeSelectPreconditionStep(true);
        wizard.completeSelectRegisteredOwnerStep(false, true);
        wizard.selectCarManufacturer(carManufacturer);
        wizard.selectCarModel(carModel);
        wizard.selectCarBodyType();
        wizard.selectFuelType();
        wizard.selectEnginePower();
        wizard.selectEngine();
        wizard.enterRegistrationDate();
        wizard.enterBirthDate();

        Assertions.assertTrue(wizard.buttonNext.isEnabled());
    }



}
