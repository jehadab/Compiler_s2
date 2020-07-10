package Java.AST.commn_classes_Sql.name_rule;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/4/2020.
 */
public class Column_alias extends Node {
    private String Contant;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getContant() {
        return Contant;
    }


    public void setContant(String contant) {
        Contant = contant;
    }
}
