package com.mabl.example.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.Assert.assertEquals;

public class UseCalculatorLocalTest extends SeleniumTestWithProxySupport {

    public UseCalculatorLocalTest() {
        super(false);
    }

    @Test
    public void useCalculator() throws Exception {
        driver.get("http://juliemr.github.io/protractor-demo/");
        driver.manage().window().setSize(new Dimension(1450, 802));

        // Click calculator buttons: 1 + 2 =
        driver.findElement(By.cssSelector("[ng-model=first]")).click();
        driver.findElement(By.cssSelector("[ng-model=first]")).sendKeys("1");
        driver.findElement(By.cssSelector("[ng-model=second]")).click();
        driver.findElement(By.cssSelector("[ng-model=second]")).sendKeys("2");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("gobutton")));

        Thread.sleep(3000L);

        // Assert answer is 3
        assertEquals("3", driver.findElement(By.cssSelector(".ng-binding:nth-child(5)")).getText());
    }
}
