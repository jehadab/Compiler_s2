package Java.AST.QueryStmt.Alter_Stmt;

import Java.AST.QueryStmt.Create_Stmt.ColumnDef;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class AddColumn extends AlterTableStmt {
private ColumnDef columnDef;

    @Override
    public void accept(AstVistor astVisitor) {
       astVisitor.visit(this);
       if (columnDef!=null){
          this.columnDef.accept(astVisitor);
       }
    }

    public ColumnDef getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(ColumnDef columnDef) {
        this.columnDef = columnDef;
    }
}
