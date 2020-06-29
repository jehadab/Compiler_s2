package Java.AST.Table_Constraint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class TableConstraintPrimaryKey {
    private List<IndexedColumn> ListOfIndexedColumn = new ArrayList<>();

    public List<IndexedColumn> getListOfIndexedColumn() {
        return ListOfIndexedColumn;
    }

    public void setListOfIndexedColumn(List<IndexedColumn> listOfIndexedColumn) {
        ListOfIndexedColumn = listOfIndexedColumn;
    }

    public void addItemToListOfIndexedColumn (IndexedColumn c){
        this.ListOfIndexedColumn.add(c);
    }




}
