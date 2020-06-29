package Java.AST.commn_classes_Sql.name_rule;

import Java.AST.Node;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/4/2020.
 */
public class Reslult_Cloumn extends Node {
    private Expr expr;
    private TableName tableName ;
    private Column_alias column_alias;
    private boolean star;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(expr!=null){
            this.expr.accept(astVisitor);
        }
        if(tableName!=null){
            this.tableName.accept(astVisitor);
        }
        if(column_alias!=null){
            this.column_alias.accept(astVisitor);
        }
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public Column_alias getColumn_alias() {
        return column_alias;
    }

    public void setColumn_alias(Column_alias column_alias) {
        this.column_alias = column_alias;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}
