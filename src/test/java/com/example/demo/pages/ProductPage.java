package com.example.demo.pages;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.clipboard;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class ProductPage {

  private SelenideElement
      ItemName = $(".ItemName"),
      ItemPrice = $(".MarketItemCard__price"),
      ItemDescription = $(".MarketItemCard__description"),
      ContactButton = $(".MarketServiceButton__text"),
      LikeButton = $(".ItemActions__iconLike"),
      FollowButton = $(byText("Подписаться")),
      ToStoreButton = $(byText("Перейти в магазин")),
      SellerName = $x("//div[@class='vkuiSimpleCell__content']"),
      ItemCategory = $x("(//div[@class='vkuiSimpleCell__content'])[3]"),
      SellerNameInFooter = $(".MarketItemCardShopInfo__ownerInner"),
      CopyLinkButton = $(".ItemActions__itemText");

  public ProductPage waitUntilCardIsLoaded() {
    ItemName.shouldBe(visible, Duration.ofSeconds(30));
     return this;
  }

  public ProductPage waitUntilCardIsDisappeared() {
    ItemName.should(disappear, Duration.ofSeconds(30));
    return this;
  }

  public String getItemName() {
    return ItemName.getText();
  }

  public String getItemPrice() {
    return ItemPrice.getText();
  }

  public String getItemDescription() {
    return ItemDescription.getText();
  }

  public String getItemCategory() {
    return ItemCategory.getText();
  }

  public String getSellerNameInFooter() {
    return SellerNameInFooter.getText();
  }

  public String getSellerName() {
    return SellerName.getText();
  }

  public String getContactButton() {
    return ContactButton.getText();
  }


  public String getCopiedLink() {
     CopyLinkButton.click();
     return clipboard().getText();
  }

  public ProductPage clickContactButton() {
    ContactButton.click();
    return this;
  }

  public ProductPage clickLikeButton() {
    LikeButton.click();
    return this;
  }

  public ProductPage clickFollowButton() {
    FollowButton.click();
    return this;
  }

  public ProductPage clickToStoreButton() {
    ToStoreButton.click();
    return this;
  }
}
