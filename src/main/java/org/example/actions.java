package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class actions {

    // create a sample of driver, make him static
    private static WebDriver driver;

    public static void setSettings() throws FileNotFoundException, IOException {
        System.setProperty("webdriver.chrome.driver", "externalLibraries/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        }

    public static void setPause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endOfWork() {
        driver.quit();
    }

    public static void gotoSite() {

        // go to start page
        driver.get("https://www.saucedemo.com");

        setPause();

        // find element by Xpath
        WebElement input_username = driver.findElement(By.xpath("descendant-or-self::*[contains(@placeholder,'Username')]"));
        // click element
        input_username.click();
        // send keys
        input_username.sendKeys("standard_user");

        setPause();

        // find element by CSSSelector
        WebElement input_password = driver.findElement(By.cssSelector("div.form_group:nth-child(2) > #password"));
        // click element
        input_password.click();
        // send keys
        input_password.sendKeys("secret_sauce");

        // find element by Name
        WebElement button_login = driver.findElement(By.name("login-button"));
        button_login.click();

        // scroll page till its end
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void check_list_of_goods(){
        By productsListLocator = By.className("inventory_item");
        List<WebElement> goods = driver.findElements(productsListLocator);

        for (int i = 0; i < ((List<?>) goods).size(); i++) {
            String currentProductTitle = goods.get(i).getText();
            System.out.println("Product title: " + currentProductTitle);
        }
    }

    public static void useFilter() {
        // find filter
        WebElement dropdown_list = driver.findElement(By.xpath("descendant-or-self::*[contains(@class,'product_sort_container')]"));
        // click element
        dropdown_list.click();

        setPause();

        // choose one filter settings
        WebElement option_list = driver.findElement(By.xpath("descendant-or-self::*[text()[contains(.,'Name (Z to A)')]]"));
        option_list.click();

        // choose one of goods and inspect his card
        WebElement option_card = driver.findElement(By.xpath("descendant-or-self::*[text()[contains(.,'Test.allTheThings() T-Shirt (Red)')]]"));
        option_card.click();

        setPause();

        // add the purchase to cart
        WebElement cart_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@name,'add-to-cart')]"));
        cart_button.click();

        setPause();

        // check what button has changer status to Remove
        WebElement remove_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'remove')]"));
        // checking...
        assertTrue(remove_button.isDisplayed());

        // get color of element
        String buttonColor = remove_button.getCssValue("color");
        if (buttonColor.equals("rgba(226, 35, 26, 1)")) {
            System.out.println("Rightful color " + buttonColor);
        } else {
            System.out.println("the button doesn't have that color " + buttonColor);
        }


        if (remove_button.isDisplayed()) {
            System.out.println("element appears on the page");
            // ok, we can go back to list of goods
            driver.navigate().back();

            // check red icon of products counter
            WebElement red_counter = driver.findElement(By.xpath("descendant-or-self::*[contains(@class,'shopping_cart_badge')]"));


        } else {
            System.err.println("can't find element");
        }

        setPause();
    }

    public static void makeADeal() {
        // click on burger button
        WebElement burger_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'react-burger-menu-btn')]"));
        burger_button.click();

        // open new tab in browser - card by link
        driver.manage().window();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.saucedemo.com/cart.html");

        setPause();

        // checkout of purchases
        WebElement checkout_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'checkout')]"));
        checkout_button.click();

        setPause();

        // fill the following inputs and click continue
        WebElement name_input_checkout = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'checkout_info_container')]//descendant-or-self::*[contains(@id,'first-name')]"));
        name_input_checkout.sendKeys("standard_user");
        WebElement last_input_checkout = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'checkout_info_container')]//descendant-or-self::*[contains(@id,'last-name')]"));
        last_input_checkout.sendKeys("secret_sauce");
        WebElement zip_checkout = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'postal-code')]"));
        zip_checkout.sendKeys("123");

        setPause();

        WebElement continue_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'continue')]"));
        continue_button.click();

        //finish the deal
        WebElement finish_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'finish')]"));
        finish_button.click();

        setPause();

        // check congrats page
        WebElement OK_img = driver.findElement(By.xpath("descendant-or-self::*[contains(@class,'pony_express')]"));
        assertTrue(OK_img.isDisplayed());

        if (OK_img.isDisplayed()) {
            System.out.println("element pony_express appears on the page");
        } else {
            System.err.println("can't find element");
        }


        WebElement back_button = driver.findElement(By.xpath("descendant-or-self::*[contains(@id,'back-to-products')]"));
        back_button.click();
        // go back to old tab
        driver.close();
    }
}


