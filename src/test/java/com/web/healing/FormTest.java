package com.web.healing;

import org.testng.annotations.Test;

import com.web.healing.pages.FormPage;

public class FormTest extends BaseTest {

    @Test
    public void testFormWithAllFields() {
        new FormPage(driver)
                .inputFirstName("John")
                .inputLastName("Doe")
                .inputEmail("jdoe@mail.com")
                .selectMaleRadioButton()
                .inputMobileNumber("1234567890")
                .setDateOfBirth("1985-03-24")
                .inputSubjects("Computer Science")
                .inputCurrentAddress("9 de Julio Av., Buenos Aires")
                .selectCountry("Argentina")
                .clickSubmitButton();

        screenshot();
    }
}
