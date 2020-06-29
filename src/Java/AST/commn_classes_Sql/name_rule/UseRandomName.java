package Java.AST.commn_classes_Sql.name_rule;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/2/2020.
 */
public class UseRandomName extends Node {
protected  String name;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
