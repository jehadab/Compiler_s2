package Java.AST.Table_Constraint;

import Java.AST.Node;

/**
 * Created by moham on 1/1/2020.
 */
public class IndexedColumn extends Node {
 private String columnname ;
 private  String  colectionname;
 private boolean ASK = false;
 private boolean DESK = false;

    public boolean isASK() {
        return ASK;
    }

    public void setASK(boolean ASK) {
        this.ASK = ASK;
    }

    public boolean isDESK() {
        return DESK;
    }

    public void setDESK(boolean DESK) {
        this.DESK = DESK;
    }

    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String getColectionname() {
        return colectionname;
    }

    public void setColectionname(String colectionname) {
        this.colectionname = colectionname;
    }
}
