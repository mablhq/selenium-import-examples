package com.mabl.example.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class UseCalculatorTest extends SeleniumTestWithProxySupport {
  @Test
  public void useCalculator() throws Exception {
    driver.get("http://juliemr.github.io/protractor-demo/");
    driver.manage().window().setSize(new Dimension(1450, 802));
    driver.findElement(By.cssSelector("[ng-model=first]")).click();
    driver.findElement(By.cssSelector("[ng-model=first]")).sendKeys("1");
    driver.findElement(By.cssSelector("[ng-model=second]")).click();
    driver.findElement(By.cssSelector("[ng-model=second]")).sendKeys("2");
    driver.findElement(By.id("gobutton")).click();
    Thread.sleep(3000L);
    assertThat(driver.findElement(By.cssSelector(".ng-binding:nth-child(5)")).getText(), is("3"));
  }
}
