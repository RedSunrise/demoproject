package reusablewebelements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {
    private SelenideElement basicElement;
    private SelenideElement ancestorElement;
    private SelenideElement button;
    private ElementsCollection optionsList;
    private String dropdownName;

    public SelenideElement getDropdownNameElement(){
        return ancestorElement.$x(".//*[contains(text(),'"+dropdownName+"')]");
    }
    public SelenideElement getButton(){
        return button;
    }
    public SelenideElement getOption(String optionName){
        return optionsList.findBy(Condition.text(optionName));
    }
    private Dropdown(DropdownBuilder builder) {
        this.basicElement = builder.basicElement;
        this.dropdownName = builder.dropdownName;
        this.optionsList = basicElement.$$x(builder.optionsListRelativeXPath);

        if(builder.ancestorElement != null){
            this.ancestorElement = builder.ancestorElement;
        } else {
            this.ancestorElement = $x("/*");
        }
        if(builder.dropdownBasicElementRelativeXPath != null) {
            this.basicElement = getDropdownNameElement().$x(builder.dropdownBasicElementRelativeXPath);
        }
        if(builder.buttonRelativeXPath != null) {
            this.button = basicElement.$x(builder.buttonRelativeXPath);
        } else {
            this.button = basicElement;
        }
    }

    public static class DropdownBuilder {
        private SelenideElement basicElement;
        private SelenideElement ancestorElement;
        private String dropdownName;
        private String buttonRelativeXPath;
        private String optionsListRelativeXPath;
        private String dropdownBasicElementRelativeXPath;

        //Mandatory fields constructors
        public DropdownBuilder(SelenideElement basicElement){
            this.basicElement = basicElement;
        }
        public DropdownBuilder(String dropdownName){
            this.dropdownName = dropdownName;
        }
        public DropdownBuilder(SelenideElement ancestorElement, String dropdownName){
            this.ancestorElement = ancestorElement;
            this.dropdownName = dropdownName;
        }

        //Optional fields
        public DropdownBuilder dropdownBasicElementRelativeXPath(String xpathFromDropdownNameElement) {
            this.dropdownBasicElementRelativeXPath = xpathFromDropdownNameElement;
            return this;
        }
        public DropdownBuilder buttonRelativeXPath(String xpathFromBasicElement){
            this.buttonRelativeXPath = xpathFromBasicElement;
            return this;
        }
        public DropdownBuilder optionsListRelativeXPath(String xpathFromBasicElement){
            this.optionsListRelativeXPath = xpathFromBasicElement;
            return this;
        }
        public Dropdown build(){
            return new Dropdown(this);
        }

    }

}
