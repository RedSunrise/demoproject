package reusablewebelements;

import com.codeborne.selenide.SelenideElement;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Table {
    private  SelenideElement basicElement;
    private  String headersRowElementRelativeXPath;
    private int firstRowXPathPosition;
    private  String rowKeyRelativeXPath;
    private  String columnHeaderDefaultRelativeXPath;
    private  LinkedHashMap<LinkedList<Integer>, String> columnHeaderSpecificRelativeXPathsMap;
    private  LinkedList<SelenideElement> tableHeadersList;
    private  LinkedList<SelenideElement> rowKeysList;
    private  LinkedList<LinkedList<SelenideElement>> tableCellsListOfLists;
    private  LinkedList<SelenideElement> listOfRows;
    public SelenideElement getTableDataCell(String rowKey, String columnHeader){
        return getTableDataCellByIndex(getTableRowIndex(rowKey), getTableHeaderIndex(columnHeader));
    }
    private SelenideElement getTableDataCellByIndex(int rowIndex, int columnIndex){
        return tableCellsListOfLists.get(rowIndex).get(columnIndex);
    }
    private int getTableHeaderIndex(String columnHeader){
        for (int i = 0; i < tableHeadersList.size(); i++) {
            SelenideElement o = tableHeadersList.get(i);
            if(basicElement.$x(headersRowElementRelativeXPath+"/th["+(i+1)+"]/descendant-or-self::*[contains(text(), normalize-space('"+columnHeader+"'))]").exists()
                    && o.equals(basicElement.$x(headersRowElementRelativeXPath+"/th["+(i+1)+"]/descendant-or-self::*[contains(text(), normalize-space('"+columnHeader+"'))]")))
                    return i;
        }
        return -1;
    }
    private int getTableRowIndex(String rowKey){
        for (int i = 0; i < rowKeysList.size(); i++) {
            SelenideElement o = rowKeysList.get(i);
            if(basicElement.$x("./tbody/tr["+(i+1)+"]"+rowKeyRelativeXPath+"[contains(text(), normalize-space('"+rowKey+"'))]").exists()
                    && o.equals(basicElement.$x("./tbody/tr["+(i+1)+"]" + rowKeyRelativeXPath + "[contains(text(), normalize-space('" + rowKey + "'))]")))
                    return i;
        }
        return -1;
    }
    private Table(TableBuilder builder){
        this.basicElement = builder.basicElement;
        this.headersRowElementRelativeXPath = builder.headersRowElementRelativeXPath;
        this.firstRowXPathPosition = builder.firstRowXPathPosition;
        this.rowKeyRelativeXPath = builder.rowKeyRelativeXPath;
        this.columnHeaderDefaultRelativeXPath = builder.columnHeaderDefaultRelativeXPath;
        this.columnHeaderSpecificRelativeXPathsMap = builder.columnHeaderSpecificRelativeXPathsMap;
        this.tableHeadersList = new LinkedList<>(){{
            int tableHeadersListSize = builder.basicElement.$x(builder.headersRowElementRelativeXPath).$$x(".//th").size();
            for (int i = 1; i <= tableHeadersListSize; i++) {
                //Checking if this particular table header element has anything existing in default xpath
                if(builder.basicElement.$x(builder.headersRowElementRelativeXPath+"/th["+i+"]"+builder.columnHeaderDefaultRelativeXPath).exists()) {
                    //If it has - adding to header list
                    add(builder.basicElement.$x(builder.headersRowElementRelativeXPath+"/th["+i+"]"+builder.columnHeaderDefaultRelativeXPath));
                } else add(null); //Else adding null to this list index
            }
            //After that, when the most used XPaths are already added as default XPaths, we fill this list by remaining XPaths
            for (var columnPositions : columnHeaderSpecificRelativeXPathsMap.entrySet()) {
                for (int i = 0; i < columnPositions.getKey().size(); i++) {
                    var asd = columnPositions.getKey().get(i);
                    set(columnPositions.getKey().get(i)-1, builder.basicElement.$x(builder.headersRowElementRelativeXPath+"/th[" + columnPositions.getKey().getFirst() + "]" + columnPositions.getValue()));
                }
            }
        }};
        this.rowKeysList = new LinkedList<>(){{
            for (int i = 1; i < builder.basicElement.$x("./tbody").$$x(".//tr").size(); i++) {
                add(builder.basicElement.$x("./tbody/tr["+i+"]"+builder.rowKeyRelativeXPath));
            }
        }};
        this.listOfRows = new LinkedList<>() {{
            for (int i = builder.firstRowXPathPosition; i < builder.basicElement.$$x(".//tr").size(); i++) {
                add(builder.basicElement.$x("./tbody/tr["+i+"]"));
            }
        }};
        this.tableCellsListOfLists = new LinkedList<>() {{
            for (var tableRow : listOfRows) add(new LinkedList<>() {{
                        for (var tableDataCell : tableRow.$$x(".//td")) add(tableDataCell);}});
        }};
    }
    public static class TableBuilder{
        private final SelenideElement basicElement;
        private int firstRowXPathPosition;
        private String headersRowElementRelativeXPath;
        private String rowKeyRelativeXPath;
        private String columnHeaderDefaultRelativeXPath;
        private LinkedHashMap<LinkedList<Integer>, String> columnHeaderSpecificRelativeXPathsMap;

        public TableBuilder(SelenideElement basicElement) {
            this.basicElement = basicElement;
        }
        public TableBuilder headersRowElementRelativeXPath(String xpathFromBasicElement){
            this.headersRowElementRelativeXPath = xpathFromBasicElement;
            return this;
        }
        public TableBuilder firstRowXPathPosition(int firstRowXPathPosition){
            this.firstRowXPathPosition = firstRowXPathPosition;
            return this;
        }
        public TableBuilder rowKeyRelativeXPath(String xpathFromRowElement){
            this.rowKeyRelativeXPath = xpathFromRowElement;
            return this;
        }
        public TableBuilder columnHeaderDefaultRelativeXPath(String xpathFromRowElement){
            this.columnHeaderDefaultRelativeXPath = xpathFromRowElement;
            return this;
        }
        public TableBuilder columnHeaderSpecificRelativeXPaths(LinkedHashMap<LinkedList<Integer>, String> columnPositionsAndRelativeXPathsMap){
            this.columnHeaderSpecificRelativeXPathsMap = columnPositionsAndRelativeXPathsMap;
            return this;
        }

        public Table build(){
            return new Table(this);
        }
    }
}
