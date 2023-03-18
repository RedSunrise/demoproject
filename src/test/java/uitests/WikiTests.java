package uitests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectors.AdminStatsPage;
import steps.AdminStatsSteps;
import steps.WikiSteps;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverConditions.url;

public class WikiTests {
    AdminStatsPage adminStatsPage = new AdminStatsPage();
    WikiSteps wikiSteps = new WikiSteps();
    AdminStatsSteps adminStatsSteps = new AdminStatsSteps();
    @BeforeEach
    void setUp(){
        Configuration.headless = false;
        Configuration.browser = "chrome";
//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.driverManagerEnabled = false;
    }
//    @Test
    void testMethod(){
        Selenide.open("https://en.wikipedia.org/wiki/Main_Page");
        wikiSteps.sendKeysAndPressEnter("Kutaisi");
//        Assertions.assertThat(WikiMainPage.getSearchbox().getText()).isEqualTo("Rammstein");
    }
//    @Test
    void testMethod2(){
        Selenide.open("https://xtools.wmflabs.org/articleinfo/en.wikipedia.org/Kutaisi");
        //assertThat(DebtToThePennyPage.getMainTableHeader(1).getText()).isEqualTo("Data Table Name");
    }
//    @Test
    void test3(){
        Selenide.open("https://xtools.wmflabs.org/ec");
//        wikiSteps.asdasd();
        wikiSteps
                .clickCheckbox("Year counts")
                .clickDetachedAllCheckbox();
        wikiSteps
                .clickLanguageDropdownButton()
                .clickDropdownOption("Deutsch");
        Assertions.assertThat(Selenide.webdriver().shouldHave(url("https://xtools.wmflabs.org/ec?uselang=de")));
    }
    @Test
    void test4(){
        Selenide.open("https://xtools.wmflabs.org/adminstats");
        adminStatsSteps.clickDetachedAllCheckbox();
        adminStatsSteps.clickCheckbox("Delete");
        $x("/html/body/div[2]/main/form/button").click();
        Selenide.sleep(1000);
        System.out.println(adminStatsPage.getTable().getTableDataCell("2", "Username").getText());
        //System.out.println(adminStatsPage.getTable().getTableHeaderIndex("#"));
        Selenide.closeWebDriver();
    }
}
