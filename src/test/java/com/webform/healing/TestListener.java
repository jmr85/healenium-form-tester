package com.webform.healing;

import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.healenium.SelfHealingDriver;

import static com.webform.healing.utils.DriverHolder.getDriver;

public class TestListener implements ITestListener {

    // @Attachment(value = "Screenshot of {0}", type = "image/png")
    // public byte[] saveScreenshot(WebDriver driver) {
    //     return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    // }

    // @Attachment(value = "Screenshot of {0}", type = "image/png")
    // public byte[] saveScreenshot(WebDriver driver) {
    //     if (driver == null) {
    //         System.out.println("WARNING: Driver is null. Cannot take screenshot.");
    //         return new byte[0]; // Retorna un array vacío para evitar NullPointerException
    //     }
    //     return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    // }

    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] saveScreenshot(String testName) {
        System.out.println("Intentando tomar captura de pantalla para: " + testName);
        try {
            WebDriver driver = getDriver();
            if (driver == null) {
                System.out.println("ERROR: Driver es null al intentar tomar captura de pantalla");
                return new byte[0];
            }
            
            // Si es un SelfHealingDriver, obtener el delegate para el screenshot
            if (driver instanceof SelfHealingDriver) {
                System.out.println("Usando driver delegate para captura de pantalla");
                WebDriver delegate = ((SelfHealingDriver) driver).getDelegate();
                if (delegate instanceof TakesScreenshot) {
                    return ((TakesScreenshot) delegate).getScreenshotAs(OutputType.BYTES);
                } else {
                    System.out.println("ERROR: El delegate no implementa TakesScreenshot");
                }
            } 
            // Intentar directamente con el driver si no es SelfHealingDriver
            else if (driver instanceof TakesScreenshot) {
                System.out.println("Usando driver directamente para captura de pantalla");
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                System.out.println("ERROR: El driver no implementa TakesScreenshot");
            }
        } catch (Exception e) {
            System.out.println("ERROR al tomar captura de pantalla: " + e.getMessage());
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart: " + result.getMethod().getMethodName());
        System.out.println("onTestStart: " + result.getMethod().getMethodName());
        // ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        // extentTest.set(test);
        // TestLogger.log(Status.INFO, "Test started: " +
        // result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess: " + result.getMethod().getMethodName());
        WebDriver driver = getDriver();
        saveScreenshot(result.getMethod().getMethodName());
        // saveScreenshot(getDriver());

        // extentTest.get().pass("Test passed");
        // TestLogger.logPass("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(result.getMethod().getMethodName());
        System.out.println("onTestFailure");
        System.out.println("onTestFailure");
        System.out.println("onTestFailure");
        System.out.println("onTestFailure");
        System.out.println("onTestFailure");
        // extentTest.get().fail(result.getThrowable());
        // TestLogger.logFail("Test failed: " + result.getMethod().getMethodName());
        // Mas detalles sobre el fallo
        // TestLogger.log(Status.FAIL, "Exception: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // extentTest.get().skip("Test skipped");
        // TestLogger.log(Status.SKIP, "Test skipped: " +
        // result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart");
        System.out.println("onStart");
        System.out.println("onStart");
        System.out.println("onStart");
        // TestLogger.log(Status.INFO, "Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // TestLogger.log(Status.INFO, "Test suite finished: " + context.getName());
        // extent.flush();
    }

    // public static ExtentTest getTest() {
    // return extentTest.get();
    // }
}
