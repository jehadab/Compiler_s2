package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class ColumnConstraint extends Node {
private  String constraintname;
private String Constrainttype;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getConstraintname() {
        return constraintname;
    }

    public void setConstraintname(String constraintname) {
        this.constraintname = constraintname;
    }

    public String getConstrainttype() {
        return Constrainttype;
    }

    public void setConstrainttype(String constrainttype) {
        Constrainttype = constrainttype;
    }

}
