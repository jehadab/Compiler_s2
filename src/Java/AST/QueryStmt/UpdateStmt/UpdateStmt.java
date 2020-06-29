package Java.AST.QueryStmt.UpdateStmt;

import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.ColumnName;
import Java.AST.commn_classes_Sql.name_rule.QualifiedTableName;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/3/2020.
 */
public class UpdateStmt extends Statement {
    QualifiedTableName qualifiedTableName;
    Expr whereExpr;
    List<Asign_Expr_to_column> ListOFAsign_expr_to_column = new ArrayList<>();

    public void addItemToListOfAsign_expr_to_column (Asign_Expr_to_column c)
    {
     ListOFAsign_expr_to_column.add(c);
    }

    public QualifiedTableName getQualifiedTableName() {
        return qualifiedTableName;
    }

    public void setQualifiedTableName(QualifiedTableName qualifiedTableName) {
        this.qualifiedTableName = qualifiedTableName;
    }



    public Expr getWhereExpr() {
        return whereExpr;
    }

    public void setWhereExpr(Expr whereExpr) {
        this.whereExpr = whereExpr;
    }

    public List<Asign_Expr_to_column> getListOFAsign_expr_to_column() {
        return ListOFAsign_expr_to_column;
    }

    public void setListOFAsign_expr_to_column(List<Asign_Expr_to_column> listOFAsign_expr_to_column) {
        ListOFAsign_expr_to_column = listOFAsign_expr_to_column;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(this.qualifiedTableName!=null){
            getQualifiedTableName().accept(astVisitor);
        }
        if(whereExpr!=null){
            getWhereExpr().accept(astVisitor);
        }
        for (int i = 0; i <ListOFAsign_expr_to_column.size() ; i++) {
            getListOFAsign_expr_to_column().get(i).accept(astVisitor);
        }

    }
}
