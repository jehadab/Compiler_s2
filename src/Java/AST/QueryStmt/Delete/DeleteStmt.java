package Java.AST.QueryStmt.Delete;

import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.QualifiedTableName;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 12/30/2019.
 */
public class DeleteStmt extends Statement {
    private QualifiedTableName qualifiedTableName;
    private Expr expr;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(this.qualifiedTableName!=null){
            this.qualifiedTableName.accept(astVisitor);
        }
        if (this.expr!=null){
            this.expr.accept(astVisitor);
        }
    }


    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public QualifiedTableName getQualifiedTableName() {
        return qualifiedTableName;
    }

    public void setQualifiedTableName(QualifiedTableName qualifiedTableName) {
        this.qualifiedTableName = qualifiedTableName;
    }
}







