package reusablewebelements;

import com.codeborne.selenide.SelenideElement;

public class InputText {
    private SelenideElement basicElement;
    private SelenideElement ancestorElement;
    private SelenideElement inputField;
    private SelenideElement label;
    public SelenideElement getInputField(){
        return inputField;
    }
    public SelenideElement getLabel(){
        return label;
    }
    private InputText(InputTextBuilder builder){
        this.basicElement = builder.basicElement;
        this.ancestorElement = builder.ancestorElement;
        if(builder.label != null) {
            this.label = ancestorElement.$x(".//label[text()=\""+label+"\"]");
        } else {
            this.label = basicElement.$x("./label");
        }
        if(builder.inputFieldRelativeXPath != null) {
            this.inputField = basicElement.$x(builder.inputFieldRelativeXPath);
        } else {
            this.inputField = basicElement.$x("./input");
        }
    }
    public static class InputTextBuilder{
        private SelenideElement basicElement;
        private SelenideElement ancestorElement;
        private String label;
        private String placeholder;
        private String inputFieldRelativeXPath;
        //this.basicElement = ancestorElement.$(By.xpath(".//div[text()=\""+placeholder+"\"]/../../.."));
        public InputTextBuilder(SelenideElement anchorElement){
            this.basicElement = anchorElement;
        }
        public InputTextBuilder(SelenideElement ancestorElement, String label){
            this.basicElement = ancestorElement.$x(".//label[text()=\""+label+"\"]/..");
        }

        //        public InputTextBuilder placeholder(String placeholder){
//            this.placeholder = placeholder;
//            return this;
//        }
        public InputTextBuilder label(String label){
            this.label = label;
            return this;
        }
        public InputText build(){
            return new InputText(this);
        }
    }
}
