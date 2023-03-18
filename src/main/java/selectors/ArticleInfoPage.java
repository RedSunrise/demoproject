package selectors;

import reusablewebelements.Dropdown;

import static com.codeborne.selenide.Selenide.$x;

public class ArticleInfoPage {
    public Dropdown getUserDropdown(){
        return new Dropdown.DropdownBuilder($x("/html/body/header/nav/ol/li[3]")).build();
    }
}
