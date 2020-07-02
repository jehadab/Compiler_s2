package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.Node;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/1/2020.
 */
public class ColumnDef extends Node {
    String name ;
    List<ColumnConstraint> ListOfColumnConstraint = new ArrayList<>();
    TypeName typeName;


    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
