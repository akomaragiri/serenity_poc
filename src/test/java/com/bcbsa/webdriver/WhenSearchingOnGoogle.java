package com.bcbsa.webdriver;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

@RunWith(SerenityRunner.class)
public class WhenSearchingOnGoogle {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Test
    public void shouldInstantiateAWebDriverInstanceForAWebTest() {
        driver.get("http://www.google.com");

        driver.findElement(By.name("q")).sendKeys("firefly");

        new WebDriverWait(driver, 5);

        driver.findElement(By.name("btnK")).click();

        new WebDriverWait(driver, 5).until(titleContains("Google Search"));

        assertThat(driver.getTitle()).isEqualTo("firefly - Google Search");
    }
}