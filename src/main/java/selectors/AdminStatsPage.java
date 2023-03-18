package selectors;

import reusablewebelements.CheckboxList;
import reusablewebelements.Table;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import static com.codeborne.selenide.Selenide.$x;

public class AdminStatsPage {
    public CheckboxList getCheckboxList(){
        return new CheckboxList.CheckboxListBuilder($x("//*[@id=\"content\"]/form/fieldset[1]/div[5]/div"))
                .optionsListRelativeXPath("./div[2]/child::*/*")
                .detachedAllCheckboxRelativeXpath("./div[1]/*")
                .build();
    }
    public Table getTable(){
        return new Table.TableBuilder($x("//*[@id=\"admins\"]/div/table"))
                .headersRowElementRelativeXPath("./thead/tr")
                .firstRowXPathPosition(1)
                .columnHeaderDefaultRelativeXPath("/span")
                .columnHeaderSpecificRelativeXPaths(new LinkedHashMap<>(){{
                        put(new LinkedList<>(){{
                            add(1);
                        }}, "/self::*");
//                        put(new LinkedList<>() {{
//                            add(2);add(3);add(4);add(5);
//                        }}, "/span");
                    }})
                .rowKeyRelativeXPath("/td")
                .build();
    }
}
