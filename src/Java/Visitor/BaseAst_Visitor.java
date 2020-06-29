package Java.Visitor;

import Java.AST.Parse;
import Java.AST.QueryStmt.Alter_Stmt.*;
import Java.AST.QueryStmt.Create_Stmt.*;
import Java.AST.QueryStmt.Delete.DeleteStmt;
import Java.AST.QueryStmt.Drop.DropStmt;
import Java.AST.QueryStmt.InsertStmt.InsertStmt;
import Java.AST.QueryStmt.Join.Join_Clause;
import Java.AST.QueryStmt.Join.Join_Constrain;
import Java.AST.QueryStmt.Join.Join_Opreator;
import Java.AST.QueryStmt.SelectStmt.*;
import Java.AST.QueryStmt.Statement;
import Java.AST.QueryStmt.UpdateStmt.Asign_Expr_to_column;
import Java.AST.QueryStmt.UpdateStmt.UpdateStmt;
import Java.AST.Table_Constraint.ForeignKeyClause;
import Java.AST.Table_Constraint.IndexedColumn;
import Java.AST.Table_Constraint.TableConstraintPrimaryKey;
import Java.AST.commn_classes_Sql.name_rule.*;
import Java.AST.expr.ComonExprOpreator;
import Java.AST.expr.Expr;
import Java.AST.expr.Literal_Value;
import Java.AST.expr.Unary_Operator;

/**
 * Created by moham on 1/6/2020.
 */
public class BaseAst_Visitor  implements AstVistor{

    @Override
    public void visit(Parse p) {
        System.out.println("visit(accept) Parse");
    }

    @Override
    public void visit(Statement s) {
        System.out.println("visit(accept) Statement");
    }

    @Override
    public void visit(SelectFactoredStmt s) {
        System.out.println("visit(accept) SelectFactoredStmt");
    }

    @Override
    public void visit(DeleteStmt d) {
        System.out.println("visit(accept) DeleteStmt");
    }

    @Override
    public void visit(DropStmt d) {
        System.out.println("visit(accept) DropStmt");
    }

    @Override
    public void visit(CreateTableStmt c) {
        System.out.println("visit(accept) CreateTableStmt");
    }

    @Override
    public void visit(ColumnDef c) {
        System.out.println("visit(accept) ColumnDef");
    }

    @Override
    public void visit(ColumnConstraint c) {
        System.out.println("visit(accept) ColumnConstraint");
    }

    @Override
    public void visit(TableConstraint c) {
        System.out.println("visit(accept) TableConstraint");
    }

    @Override
    public void visit(TypeName t) {
        System.out.println("visit(accept) TypeName");
    }

    @Override
    public void visit(AlterTableStmt a) {
        System.out.println("visit(accept) AlterTableStmt");
    }

    @Override
    public void visit(RenameTable r) {
        System.out.println("visit(accept) RenameTable");
    }

    @Override
    public void visit(AlterTableAdd a) {
        System.out.println("visit(accept) AlterTableAdd");
    }

    @Override
    public void visit(AlterTableAddConstraint a) {
        System.out.println("visit(accept) AlterTableAddConstraint");
    }

    @Override
    public void visit(InsertStmt i) {
        System.out.println("visit(accept) InsertStmt");
    }

    @Override
    public void visit(UpdateStmt u) {
        System.out.println("visit(accept) UpdateStmt");
    }

    @Override
    public void visit(Asign_Expr_to_column a) {
        System.out.println("visit(accept) Asign_Expr_to_column");
    }

    @Override
    public void visit(select_stmt s) {
        System.out.println("visit(accept) select_stmt");
    }

    @Override
    public void visit(Select_Core s) {
        System.out.println("visit(accept) Select_Core");
    }

    @Override
    public void visit(List_Of_Expr l) {
        System.out.println("visit(accept) List_Of_Expr");
    }

    @Override
    public void visit(SelectOrValue s) {
        System.out.println("visit(accept) SelectOrValue");
    }

    @Override
    public void visit(Join_Clause j) {
        System.out.println("visit(accept) Join_Clause");
    }

    @Override
    public void visit(Join_Constrain j) {
        System.out.println("visit(accept) Join_Constrain");
    }

    @Override
    public void visit(Join_Opreator j) {
        System.out.println("visit(accept) Join_Opreator");
    }

    @Override
    public void visit(TableOrSubQuery t) {
        System.out.println("visit(accept) TableOrSubQuery");
    }

    @Override
    public void visit(Reslult_Cloumn r) {
        System.out.println("visit(accept) Reslult_Cloumn");
    }

    @Override
    public void visit(Ordering_term o) {
        System.out.println("visit(accept) Ordering_term");
    }

    @Override
    public void visit(AddColumn a) {
        System.out.println("visit(accept) AddColumn");
    }

    @Override
    public void visit(TableConstraintPrimaryKey t) {
        System.out.println("visit(accept) TableConstraintPrimaryKey");
    }

    @Override
    public void visit(IndexedColumn i) {
        System.out.println("visit(accept) IndexedColumn");
    }

    @Override
    public void visit(ForeignKeyClause f) {
        System.out.println("visit(accept) ForeignKeyClause");
    }

    @Override
    public void visit(Expr e) {
        System.out.println("visit(accept) Expr");
    }

    @Override
    public void visit(Literal_Value l) {
        System.out.println("visit(accept) Literal_Value");
    }

    @Override
    public void visit(ComonExprOpreator c) {
        System.out.println("visit(accept) ComonExprOpreator");
    }

    @Override
    public void visit(Unary_Operator u) {
        System.out.println("visit(accept) Unary_Operator");
    }

    @Override
    public void visit(FkTarget_ColumnName f) {
        System.out.println("visit(accept) FkTarget_ColumnName");
    }

    @Override
    public void visit(DataBaseName d) {
        System.out.println("visit(accept) DataBaseName");
    }

    @Override
    public void visit(Name n) {
        System.out.println("visit(accept) Name");
    }

    @Override
    public void visit(Fk_Origin_Column_Name f) {
        System.out.println("visit(accept) Fk_Origin_Column_Name");
    }

    @Override
    public void visit(TableName t) {
        System.out.println("visit(accept) TableName");
    }

    @Override
    public void visit(ColumnName c) {
        System.out.println("visit(accept) ColumnName");
    }

    @Override
    public void visit(FunctionName f) {
        System.out.println("visit(accept) FunctionName");
    }

    @Override
    public void visit(IndexName i) {
        System.out.println("visit(accept) IndexName");
    }

    @Override
    public void visit(QualifiedTableName q) {
        System.out.println("visit(accept) QualifiedTableName");
    }

    @Override
    public void visit(Column_alias c) {
        System.out.println("visit(accept) Column_alias");
    }

    @Override
    public void visit(Table_alias t) {
        System.out.println("visit(accept) Table_alias");
    }

    @Override
    public void visit(ForeignTable f) {
        System.out.println("visit(accept) ForeignTable");
    }

    @Override
    public void visit(UseRandomName u) {
        System.out.println("visit(accept) UseRandomName");
    }
}
