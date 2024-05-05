package com.example.demo.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class CommunityPage {
  private SelenideElement
      WallSearchButton = $(".ui_tab_search"),
      CommunityPageName = $(".page_name"),
      WallTabAll = $("._wall_tab_all"),
      WallTabOwn = $("._wall_tab_own"),
      FollowersLabel = $(byText("Подписчики")),
      ContactsLabel = $(byText("Контакты")),
      GroupInfo = $(".group_info_row .line_value"),
      FollowButton = $("#page_actions"),
      ShowAllServicesButton = $(".FlatButton--secondary"),
      InfoBox = $("#wk_content"),
      ContactsBox = $(".box_layout");

  public CommunityPage waitUntilCommunityPageIsLoaded() {
    CommunityPageName.shouldBe(visible, Duration.ofSeconds(30));
    return this;
  }

  public CommunityPage waitUntilCommunityPageIsDisappeared() {
    CommunityPageName.should(disappear, Duration.ofSeconds(30));
    return this;
  }

  public String getCommunityPageName() {
    CommunityPageName.scrollTo();
    return CommunityPageName.getText();
  }

  public CommunityPage checkWallSearchButtonIsClickable() {
    WallSearchButton.scrollTo();
    WallSearchButton.should(clickable);
    return this;
  }

  public CommunityPage checkWallTabAllButtonIsClickable() {
    WallTabAll.scrollTo();
    WallTabAll.should(clickable);
    return this;
  }

  public CommunityPage checkWallTabOwnButtonIsClickable() {
    WallTabOwn.scrollTo();
    WallTabOwn.should(clickable);
    return this;
  }

  public CommunityPage checkInfoBoxAppears() {
    GroupInfo.scrollTo();
    GroupInfo.click();
    InfoBox.should(appear, Duration.ofSeconds(15));
    return this;
  }

  public CommunityPage checkContactsBoxAppears() {
    ContactsLabel.scrollTo();
    ContactsLabel.click();
    ContactsBox.should(appear, Duration.ofSeconds(15));
    return this;
  }

  public CommunityPage clickFollowersLabel() {
    FollowersLabel.click();
    return this;
  }

  public CommunityPage clickFollowButton() {
    FollowButton.click();
    return this;
  }

  public CommunityPage clickShowAllServicesButton() {
    ShowAllServicesButton.scrollTo();
    ShowAllServicesButton.click();
    return this;
  }
}
