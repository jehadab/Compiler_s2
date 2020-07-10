package Java.AST.QueryStmt.Alter_Stmt;

import Java.AST.QueryStmt.Create_Stmt.TableConstraint;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class AlterTableAddConstraint extends AlterTableStmt {
    private String tablename;
    private TableConstraint tableConstraint;

    @Override
    public void accept(AstVistor astVisitor) {
       astVisitor.visit(this);
       if(tableConstraint!=null){
           tableConstraint.accept(astVisitor);
       }
    }

    @Override
    public String getTablename() {
        return tablename;
    }

    @Override
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public TableConstraint getTableConstraint() {
        return tableConstraint;
    }

    public void setTableConstraint(TableConstraint tableConstraint) {
        this.tableConstraint = tableConstraint;
    }
}
