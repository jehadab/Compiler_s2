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
    List<TypeName> ListOfTypeName = new ArrayList<>();

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        for (int i = 0; i <ListOfColumnConstraint.size() ; i++) {
            this.ListOfColumnConstraint.get(i).accept(astVisitor);
        }
        for (int i = 0; i <ListOfTypeName.size() ; i++) {
            this.ListOfTypeName.get(i).accept(astVisitor);
        }
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

    public void addItemToListOfTypeName(TypeName c){
        this.ListOfTypeName.add(c);
    }

    public List<ColumnConstraint> getListOfColumnConstraint() {
        return ListOfColumnConstraint;
    }

    public void setListOfColumnConstraint(List<ColumnConstraint> listOfColumnConstraint) {
        ListOfColumnConstraint = listOfColumnConstraint;
    }

    public List<TypeName> getListOfTypeName() {
        return ListOfTypeName;
    }

    public void setListOfTypeName(List<TypeName> listOfTypeName) {
        ListOfTypeName = listOfTypeName;
    }



}
