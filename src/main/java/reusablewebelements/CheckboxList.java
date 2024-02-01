package reusablewebelements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CheckboxList {
    private final SelenideElement basicElement;
    private final ElementsCollection optionsElementsList;
    private final String detachedAllCheckboxRelativeXpath;
    private final String checkboxRelativeXPath;
    private final String checkboxAttributeName;
    public SelenideElement getOption(String optionName){
        return optionsElementsList.findBy(Condition.text(optionName));
    }
    public SelenideElement getDetachedAllCheckbox(){
        return basicElement.$x(detachedAllCheckboxRelativeXpath);
    }
    public ArrayList<String> getOptionsList(){
        var optionsList = new ArrayList<String>();
        optionsElementsList.forEach(x->optionsList.add(x.$x("./span").getText()));
        return optionsList;
    }
    public Boolean getOptionCheckboxStatus(String optionName){
        return Boolean.parseBoolean(getOption(optionName).$x(checkboxRelativeXPath).getAttribute(checkboxAttributeName));
    }
    public HashMap<String, Boolean> getCheckboxStatuses(){
        var checkboxesMap = new HashMap<String, Boolean>();
        optionsElementsList.forEach(x->checkboxesMap.put(x.$x("./span").getText(), Boolean.parseBoolean(x.getAttribute(checkboxAttributeName))));
        return checkboxesMap;
    }
    private CheckboxList(CheckboxListBuilder builder) {
        this.basicElement = builder.basicElement;
        this.detachedAllCheckboxRelativeXpath = builder.detachedAllCheckboxRelativeXpath;
        this.checkboxRelativeXPath = builder.checkboxRelativeXPath;
        this.checkboxAttributeName = builder.checkboxAttributeName;
        this.optionsElementsList = basicElement.$$x(Objects.requireNonNullElse(builder.optionsListRelativeXPath, "./child::*/*"));
    }
    public static class CheckboxListBuilder {
        private final SelenideElement basicElement;
        private String optionsListRelativeXPath;
        private String detachedAllCheckboxRelativeXpath;
        private String checkboxRelativeXPath;
        private String checkboxAttributeName;

        public CheckboxListBuilder(SelenideElement basicElement){
            this.basicElement = basicElement;
        }
        public CheckboxListBuilder optionsListRelativeXPath(String xpathFromBasicElement){
            this.optionsListRelativeXPath = xpathFromBasicElement;
            return this;
        }
        public CheckboxListBuilder detachedAllCheckboxRelativeXpath(String xpathFromBasicElement){
            this.detachedAllCheckboxRelativeXpath = xpathFromBasicElement;
            return this;
        }
        public CheckboxListBuilder checkboxRelativeXPath(String xpathFromOptionElement){
            this.checkboxRelativeXPath = xpathFromOptionElement;
            return this;
        }
        public CheckboxListBuilder checkboxAttributeName(String attributeName){
            this.checkboxAttributeName = attributeName;
            return this;
        }
        public CheckboxList build(){
            return new CheckboxList(this);
        }

    }
}
