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
public interface AstVistor  {
    public void visit(Parse p);
    public void visit(Statement s);
    public void visit(SelectFactoredStmt s);
    public void visit(DeleteStmt d);
    public void visit(DropStmt d);
    public void visit(CreateTableStmt c);
    public void visit(ColumnDef c);
    public void visit(ColumnConstraint c);
    public void visit(TableConstraint c);
    public void visit(TypeName t);
    public void visit(AlterTableStmt a);
    public void visit(RenameTable r);
    public void visit(AlterTableAdd a);
    public void visit(AlterTableAddConstraint a);
    public void visit(InsertStmt i);
    public void visit(UpdateStmt u);
    public void visit(Asign_Expr_to_column a);
    public void visit(select_stmt s);
    public void visit(Select_Core s);
    public void visit(List_Of_Expr l);
    public void visit(SelectOrValue s);
    public void visit(Join_Clause j);
    public void visit(Join_Constrain j);
    public void visit(Join_Opreator j);
    public  void visit(TableOrSubQuery t);
    public  void visit(Reslult_Cloumn r);
    public  void visit(Ordering_term o);
    public  void visit(AddColumn a);
    public  void visit(TableConstraintPrimaryKey t);
    public  void visit(IndexedColumn i);
    public  void visit(ForeignKeyClause f);
    public  void visit(Expr e);
    public  void visit(Literal_Value l);
    public  void visit(ComonExprOpreator c);
    public  void visit(Unary_Operator u);
    public  void visit(FkTarget_ColumnName f);
    public  void visit(DataBaseName d);
    public  void visit(Name n);
    public  void visit(Fk_Origin_Column_Name f);
    public  void visit(TableName t);
    public  void visit(ColumnName c);
    public  void visit(FunctionName f);
    public  void visit(IndexName i);
    public  void visit(QualifiedTableName q);
    public  void visit(Column_alias c);
    public  void visit(Table_alias t);
    public void visit(ForeignTable f);
    public void visit(UseRandomName u);
}
