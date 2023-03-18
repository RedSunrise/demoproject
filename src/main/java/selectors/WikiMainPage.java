package selectors;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WikiMainPage {
    private SelenideElement searchbox = $(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[1]"));
    public SelenideElement setSearchboxValue(String value){
        searchbox.sendKeys(value);
        return searchbox;
    }
    public SelenideElement getSearchbox(){
        return searchbox;
    }
}
