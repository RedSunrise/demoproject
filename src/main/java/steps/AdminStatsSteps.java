package steps;

import io.qameta.allure.Step;
import selectors.AdminStatsPage;

public class AdminStatsSteps {
    AdminStatsPage adminStatsPage = new AdminStatsPage();
    @Step
    public AdminStatsSteps clickCheckbox(String label){
        adminStatsPage.getCheckboxList().getOption(label).click();
        return this;
    }
    @Step
    public AdminStatsSteps clickDetachedAllCheckbox(){
        adminStatsPage.getCheckboxList().getDetachedAllCheckbox().click();
        return this;
    }
}
