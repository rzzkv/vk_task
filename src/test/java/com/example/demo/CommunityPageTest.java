package com.example.demo;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import com.codeborne.selenide.Configuration;
import com.example.demo.pages.CommunityPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommunityPageTest {

  CommunityPage communityPage = new CommunityPage();
  String expectedLoginPageUrl = "https://vk.com/login";

  @BeforeAll
  public static void setUpAll() {
    Configuration.browserSize = "2560x1600";
  }

  @BeforeEach
  public void setUp() {
    Configuration.browserCapabilities = new ChromeOptions().addArguments(
        "--remote-allow-origins=*");
    open("https://vk.com/club225299895");
    communityPage.waitUntilCommunityPageIsLoaded();
  }

  @Order(1)
  @DisplayName("Проверка названия Сообщества!")
  @Test
  public void checkCommunityPageName() {
    Assertions.assertEquals("Test public for test", communityPage.getCommunityPageName(),
        "Ошибка в название Сообщества");
  }

  @DisplayName("Проверка названия Сообщества!")
  @Test
  public void checkWallModuleButtonsAreClickable() {
    communityPage
        .checkWallTabAllButtonIsClickable()
        .checkWallTabOwnButtonIsClickable()
        .checkWallSearchButtonIsClickable();
  }

  @DisplayName("Проверка названия Сообщества!")
  @Test
  public void checkInfoBoxAppears() {
    communityPage
        .checkInfoBoxAppears();
  }

  @DisplayName("Проверка названия Сообщества!")
  @Test
  public void checkContactsBoxAppears() {
    communityPage
        .checkContactsBoxAppears();
  }

  @DisplayName("Проверка перехода на 'Login' страницу, после клика на кнопку 'подписаться'!")
  @Test
  public void checkRedirectionToLoginPageAfterClickFollowButton() {
    communityPage
        .clickFollowButton()
        .waitUntilCommunityPageIsDisappeared();
    String currentUrl = url();
    assert currentUrl.contains(expectedLoginPageUrl) :
        "Ожидал переход на login страницу, но перешел на " + currentUrl;
  }

  @DisplayName("Проверка перехода на 'Login' страницу, после клика на кнопку 'подписчики'!")
  @Test
  public void checkRedirectionToLoginPageAfterClickFollowersLabel() {
    communityPage
        .clickFollowersLabel()
        .waitUntilCommunityPageIsDisappeared();
    String currentUrl = url();
    assert currentUrl.contains(expectedLoginPageUrl) :
        "Ожидал переход на login страницу, но перешел на " + currentUrl;
  }

  @DisplayName("Проверка перехода на страницу с услугами, после клика на кнопку 'показать все'!")
  @Test
  public void checkRedirectionToUslugiPageAfterClickShowAllServicesButton() {
    communityPage
        .clickShowAllServicesButton()
        .waitUntilCommunityPageIsDisappeared();
    String expectedUslugiPageUrl = "https://vk.com/uslugi";
    String currentUrl = url();
    assert currentUrl.contains(expectedUslugiPageUrl) :
        "Ожидал переход на страницу с услугами, но перешел на " + currentUrl;
  }
}
