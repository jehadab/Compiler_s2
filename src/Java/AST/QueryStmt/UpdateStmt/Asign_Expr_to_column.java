package Java.AST.QueryStmt.UpdateStmt;

import Java.AST.Node;
import Java.AST.commn_classes_Sql.name_rule.ColumnName;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/3/2020.
 */
public class Asign_Expr_to_column extends Node {
private ColumnName columnName;
private Expr expr;

    public ColumnName getColumnName() {
        return columnName;
    }

    public void setColumnName(ColumnName columnName) {
        this.columnName = columnName;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(columnName!=null){
            columnName.accept(astVisitor);
        }
        if (this.expr!=null){
            expr.accept(astVisitor);
        }
    }
}
