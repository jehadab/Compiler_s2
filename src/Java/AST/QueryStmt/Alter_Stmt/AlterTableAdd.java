package Java.AST.QueryStmt.Alter_Stmt;

import Java.AST.QueryStmt.Create_Stmt.ColumnConstraint;
import Java.AST.QueryStmt.Create_Stmt.TableConstraint;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class AlterTableAdd extends  AlterTableStmt {
private TableConstraint tableConstraint;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(tableConstraint!=null){
            tableConstraint.accept(astVisitor);
        }
    }

    public TableConstraint getTableConstraint() {
        return tableConstraint;
    }

    public void setTableConstraint(TableConstraint tableConstraint) {
        this.tableConstraint = tableConstraint;
    }
}
