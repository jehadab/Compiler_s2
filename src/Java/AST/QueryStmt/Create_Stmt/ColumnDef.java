package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.Node;
import Java.SymbolTable.Type;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class ColumnDef extends Node {
    List<ColumnConstraint> ListOfColumnConstraint = new ArrayList<>();
    String columnName;
    private Type type = new Type();
    private String Lim1 ;
    private String Lim2 ;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getLim1() {
        return Lim1;
    }

    public void setLim1(String lim1) {
        Lim1 = lim1;
    }

    public String getLim2() {
        return Lim2;
    }

    public void setLim2(String lim2) {
        Lim2 = lim2;
    }



    public Type getType() {
        return type;
    }

    public void setType(Type typeName) {
        this.type = typeName;
    }


    public void addItemToListOfColumnConstraint(ColumnConstraint c){
        this.ListOfColumnConstraint.add(c);
    }



    public List<ColumnConstraint> getListOfColumnConstraint() {
        return ListOfColumnConstraint;
    }

    public void setListOfColumnConstraint(List<ColumnConstraint> listOfColumnConstraint) {
        ListOfColumnConstraint = listOfColumnConstraint;
    }







}
