package Java.AST.QueryStmt.Alter_Stmt;

import Java.AST.QueryStmt.Statement;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class AlterTableStmt extends Statement {
    private String tablename ;
    private Object altertype ;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Object getAltertype() {
        return altertype;
    }

    public void setAltertype( Object altertype) {
        this.altertype = altertype;
    }
}
