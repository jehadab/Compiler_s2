package Java.AST.QueryStmt.SelectStmt;

import Java.AST.Node;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/4/2020.
 */
public class Ordering_term extends Node {
    Expr expr ;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(expr!=null){
            this.expr.accept(astVisitor);
        }
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
