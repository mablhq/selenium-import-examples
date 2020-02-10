require "selenium-webdriver"

client = Selenium::WebDriver::Remote::Http::Default.new
client.proxy = Selenium::WebDriver::Proxy.new(http: "http://localhost:8889")
driver = Selenium::WebDriver.for :remote, http_client: client, url: "http://localhost:9515"

driver.navigate.to("http://juliemr.github.io/protractor-demo/")

first_number = driver.find_element(xpath: "/html/body/div/div/form/input[1]")
first_number.send_keys("1")

second_number = driver.find_element(xpath: "/html/body/div/div/form/input[2]")
second_number.send_keys("2")

go_button = driver.find_element(id: "gobutton")
go_button.click

driver.quit

