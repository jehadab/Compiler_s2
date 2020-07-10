package Java.AST.QueryStmt.Join;

import Java.AST.Node;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/6/2020.
 */
public class Join_Constrain extends Node {
    private Expr expr;

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
