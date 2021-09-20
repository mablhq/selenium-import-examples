using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Net;

using System.Threading;
namespace SeleniumTest
{
    public class Tests
    {
        IWebDriver driver;

        [Test]
        public void AddNumbers()
        {
            ChromeOptions options = new ChromeOptions();
            var p = new WebProxy("http://localhost:8889/");
            HttpCommandExecutor commandExecutor = new HttpCommandExecutor(new Uri("http://localhost:9515"), TimeSpan.FromSeconds(600));
            commandExecutor.Proxy = p;
            RemoteWebDriver driver = new(commandExecutor, options.ToCapabilities());
            Thread.Sleep(1000);
            driver.Navigate().GoToUrl("http://juliemr.github.io/protractor-demo");
            var firstNumber = driver.FindElement(By.XPath("//input[@ng-model='first']"));
            var secondNumber = driver.FindElement(By.XPath("//input[@ng-model='second']"));
            var goButton = driver.FindElement(By.Id("gobutton"));
            firstNumber.SendKeys("1");
            secondNumber.SendKeys("2");
            goButton.Click();
            Thread.Sleep(3000);
            var latestResult = driver.FindElement(By.XPath("(//tr[1]/td[last()])")).Text;

            Assert.AreEqual("3", latestResult);
            driver.Close();
        }
    }
}