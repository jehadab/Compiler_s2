package Java.AST.Table_Constraint;

import Java.AST.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class TableConstraintKey extends Node {
    protected String name ;
    protected List<IndexedColumn> ListOfIndexedColumn = new ArrayList<>();
    public void addItemToListOfIndexedColumn(IndexedColumn c){
        this.ListOfIndexedColumn.add(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IndexedColumn> getListOfIndexedColumn() {
        return ListOfIndexedColumn;
    }

    public void setListOfIndexedColumn(List<IndexedColumn> listOfIndexedColumn) {
        ListOfIndexedColumn = listOfIndexedColumn;
    }
}
