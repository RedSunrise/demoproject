package steps;

import io.qameta.allure.Step;
import selectors.ArticleInfoPage;
import selectors.EditCounterPage;
import selectors.WikiMainPage;

public class WikiSteps {
    WikiMainPage wikiMainPage = new WikiMainPage();
    ArticleInfoPage articleInfoPage = new ArticleInfoPage();
    EditCounterPage editCounterPage = new EditCounterPage();

    @Step
    public WikiSteps sendKeysAndPressEnter(String keys){
        wikiMainPage.getSearchbox().sendKeys(keys);
        wikiMainPage.getSearchbox().pressEnter();
        return this;
    }
    @Step
    public WikiSteps qwe(){
        articleInfoPage.getUserDropdown().getButton().click();
        return this;
    }
    @Step
    public WikiSteps clickCheckbox(String label){
        editCounterPage.getCheckboxList().getOption(label).click();
        return this;
    }
    @Step
    public WikiSteps clickDetachedAllCheckbox(){
        editCounterPage.getCheckboxList().getDetachedAllCheckbox().click();
        return this;
    }
    @Step
    public WikiSteps clickNavBarDropdownButton(){
        editCounterPage.getNavBarDropdown("User").getButton().click();
        return this;
    }
    @Step
    public WikiSteps clickLanguageDropdownButton(){
        editCounterPage.getLanguageDropdown().getButton().click();
        return this;
    }
    @Step
    public WikiSteps clickDropdownOption(String option){
        editCounterPage.getLanguageDropdown().getOption(option).click();
        return this;
    }
}
