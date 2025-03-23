package com.webform.healing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class FormPage {

    private By txtFirstName = By.id("firstName");
    private By txtLastName = By.id("lastName");
    private By txtEmail = By.id("email");
    private By chkMale = By.id("male");
    private By txtMobileNumber = By.id("mobile");
    private By dateOfBirthField = By.id("dateOfBirth");
    private By txtSubjects = By.id("subjects");
    private By chkSports = By.id("sports");
    private By ddlCountry = By.id("country");
    private By btnSubmit = By.xpath("//button[text()='Submit']");

    private WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }
 
    public FormPage inputFirstName(String firstName) {
        driver.findElement(txtFirstName).sendKeys(firstName);
        return this;
    }

    public FormPage inputLastName(String lastName) {
        driver.findElement(txtLastName).sendKeys(lastName);
        return this;
    }

    public FormPage inputEmail(String email) {
        driver.findElement(txtEmail).sendKeys(email);
        return this;
    }

    public FormPage setDateOfBirth(String dateValue) {
        // Formato esperado: yyyy-MM-dd (por ejemplo: 2025-03-22)
        WebElement dateField = driver.findElement(dateOfBirthField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + dateValue + "');", dateField);
        return this;
    }

    public FormPage inputSubjects(String subjects) {
        driver.findElement(txtSubjects).sendKeys(subjects);
        return this;
    }

    public FormPage selectSportsCheckbox() {
        WebElement sportsCheckbox = driver.findElement(chkSports);
        if (!sportsCheckbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sportsCheckbox);
        }
        return this;
    }
    
    public FormPage selectMaleRadioButton() {
        WebElement maleRadioButton = driver.findElement(chkMale);
        if (!maleRadioButton.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maleRadioButton);
        }
        return this;
    }

    public FormPage inputMobileNumber(String mobileNumber) {
        driver.findElement(txtMobileNumber).sendKeys(mobileNumber);
        return this;
    }

    public FormPage selectCountry(String country) {
        new Select(driver.findElement(ddlCountry)).selectByVisibleText(country);
        return this;
    }

    public void clickSubmitButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(btnSubmit));
    }
}