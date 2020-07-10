package Java.AST.expr;

import Java.AST.Node;
import Java.AST.QueryStmt.SelectStmt.Select_Core;

public class WhereWithInForSelect extends Node {
    WhereExpr whereExpr;
    Select_Core select_core ;

    public WhereExpr getWhereExpr() {
        return whereExpr;
    }

    public void setWhereExpr(WhereExpr whereExpr) {
        this.whereExpr = whereExpr;
    }

    public Select_Core getSelect_core() {
        return select_core;
    }

    public void setSelect_core(Select_Core select_core) {
        this.select_core = select_core;
    }

}
