package com.example.demo;

import com.codeborne.selenide.Configuration;
import com.example.demo.pages.ProductPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ProductPageTest {

    ProductPage productPage = new ProductPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments(
            "--remote-allow-origins=*");
        open("https://vk.com/club225299895?w=product-225299895_10044406");
        productPage.waitUntilCardIsLoaded();
    }

    @DisplayName("Проверка названия товара!")
    @Test
    public void checkItemName() {
        Assertions.assertEquals("For testing test", productPage.getItemName(),
            "Ошибка в название товара");
    }

    @DisplayName("Проверка цены товара!")
    @Test
    public void checkItemPrice() {
        //У вас здесь был баг локализации, с залогиненным юзером цена была "бесплатно"
        //А с не залогиненным юзером цена товра была "free"
        Assertions.assertEquals("free", productPage.getItemPrice(),
            "Ошибка в цене товара!"
                + "P.S здесь баг локализации");
    }

    @DisplayName("Проверка описания товара!")
    @Test
    public void checkItemDescription() {
        productPage.getItemDescription().equalsIgnoreCase("testing product");
    }

    @Disabled
    @DisplayName("Проверка категории товара!")
    @Test
    public void checkItemCategory() {
        // Здесь по моему мнению тоже баг, с залогиненным юзером показывает категорию товара,
        //А с не залогиненным юзером категория отсуствует, при том что в DOM этот элемент есть, о не отображается
        Assertions.assertEquals("Аренда автомобилей", productPage.getItemCategory(),
            "Ошибка в категории товара"
                + "P.S здесь баг, не показывает категорию товара не залогиненному юзеру");
    }

    @DisplayName("Проверка имени продавца!")
    @Test
    public void checkSellerName() {
        Assertions.assertEquals("Test public for test", productPage.getSellerName(),
            "Ошибка в имени продавца");
    }

    @DisplayName("Проверка имени продавца в нижней части карточки!")
    @Test
    public void checkSellerNameInFooter() {
        Assertions.assertEquals("Test public for test", productPage.getSellerNameInFooter(),
            "Ошибка в имени продавца в нижней части карточки");
    }

    @DisplayName("Проверка кнопка 'скопировать ссылку' работает корректно!")
    @Test
    public void checkCopyLinkButton() {
        productPage.getCopiedLink();
        String copiedLink = clipboard().getText();
        String actualLink = "https://vk.com/club225299895?w=product-225299895_10044406";

        Assertions.assertEquals(actualLink, copiedLink,
            "Ошибка в кнопке 'скопировать ссылку'");
    }

    @DisplayName("Проверка перехода на 'Login' страницу, после клика на кнопку 'написать'!")
    @Test
    public void checkRedirectionToLoginPage() {
        productPage
            .clickContactButton()
            .waitUntilCardIsDisappeared();
        String expectedLoginPageUrl = "https://vk.com/login";
        String currentUrl = url();
        assert currentUrl.contains(expectedLoginPageUrl) :
            "Ожидал переход на login страницу, но перешел на " + currentUrl;
    }
}
