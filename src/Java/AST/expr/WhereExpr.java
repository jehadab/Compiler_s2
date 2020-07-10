package Java.AST.expr;

import Java.AST.Node;
import Java.AST.QueryStmt.SelectStmt.Select_Core;

public class WhereExpr extends Node {
    Expr expr ;

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
