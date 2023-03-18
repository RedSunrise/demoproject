package selectors;

import reusablewebelements.CheckboxList;
import reusablewebelements.Dropdown;

import static com.codeborne.selenide.Selenide.$x;

public class EditCounterPage {
    public CheckboxList getCheckboxList(){
        return new CheckboxList.CheckboxListBuilder($x("/html/body/div[2]/main/form/fieldset[1]/div[3]/div"))
                .optionsListRelativeXPath("./div[2]/child::*/*")
                .detachedAllCheckboxRelativeXpath("./div[1]/*")
                .build();
    }
    public Dropdown getNavBarDropdown(String dropdownName){
        return new Dropdown.DropdownBuilder(dropdownName)
                .dropdownBasicElementRelativeXPath("./..")
                .build();
    }
    public Dropdown getLanguageDropdown(){
        return new Dropdown.DropdownBuilder($x("/html/body/header/ol/li[1]/div"))
                .optionsListRelativeXPath("./ul/child::*")
                .build();
    }
}
