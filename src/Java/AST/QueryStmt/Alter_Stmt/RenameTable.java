package Java.AST.QueryStmt.Alter_Stmt;

import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/1/2020.
 */
public class RenameTable extends AlterTableStmt {
   private String newname;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }
}
