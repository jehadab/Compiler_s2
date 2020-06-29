package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class TableConstraint extends Node {
 private String costraintname;
 private String constrainttype;



    @Override
    public void accept(AstVistor astVisitor) {
       astVisitor.visit(this);
    }

    public String getConstrainttype() {
        return constrainttype;
    }

    public void setConstrainttype(String constrainttype) {
        this.constrainttype = constrainttype;
    }


    public String getCostraintname() {
        return costraintname;
    }

    public void setCostraintname(String costraintname) {
        this.costraintname = costraintname;
    }
//**************************************************
// todo add expr list item in coulmnConstraint class
//**************************************************

}
