package Java.AST.QueryStmt.SelectStmt;

import Java.AST.QueryStmt.Join.Join_Clause;
import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.Column_alias;
import Java.AST.commn_classes_Sql.name_rule.Reslult_Cloumn;
import Java.AST.commn_classes_Sql.name_rule.TableOrSubQuery;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/4/2020.
 */
public class SelectOrValue extends Statement {
private List<Reslult_Cloumn> ListOf_reslult_coulmn = new ArrayList<>();
private Column_alias column_alias ;
private List<TableOrSubQuery>  ListOftableOrSubQuery = new ArrayList<>() ;
private Join_Clause join_clause;
private Expr Where_expr;
private Expr Having_expr;
private  List<Expr> ListOfExpr= new ArrayList<>();

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        for (int i = 0; i <getListOf_reslult_coulmn().size(); i++) {
            getListOf_reslult_coulmn().get(i).accept(astVisitor);
        }
        for (int i = 0; i <ListOftableOrSubQuery.size() ; i++) {
            getListOftableOrSubQuery().get(i).accept(astVisitor);
        }
        if (column_alias!=null){
            getColumn_alias().accept(astVisitor);
        }
        if(join_clause!=null){
            getJoin_clause().accept(astVisitor);
        }
        if(Where_expr!=null){
            getWhere_expr().accept(astVisitor);
        }
        if(Having_expr!=null){
            getHaving_expr().accept(astVisitor);
        }
        for (int i = 0; i <ListOfExpr.size() ; i++) {
            getListOfExpr().get(i).accept(astVisitor);
        }
    }

    public void addItemToListOftableOrSubQuery(TableOrSubQuery c){
        ListOftableOrSubQuery.add(c);
    }

    public List<TableOrSubQuery> getListOftableOrSubQuery() {
        return ListOftableOrSubQuery;
    }

    public void setListOftableOrSubQuery(List<TableOrSubQuery> listOftableOrSubQuery) {
        ListOftableOrSubQuery = listOftableOrSubQuery;
    }

    public void addItemToListOfExpr (Expr c){
        ListOfExpr.add(c);
    }

    public List<Expr> getListOfExpr() {
        return ListOfExpr;
    }

    public void setListOfExpr(List<Expr> listOfExpr) {
        ListOfExpr = listOfExpr;
    }

    public Expr getHaving_expr() {
        return Having_expr;
    }

    public void setHaving_expr(Expr having_expr) {
        Having_expr = having_expr;
    }

    public Expr getWhere_expr() {
        return Where_expr;
    }

    public void setWhere_expr(Expr where_expr) {
        Where_expr = where_expr;
    }

    public Join_Clause getJoin_clause() {
        return join_clause;
    }

    public void setJoin_clause(Join_Clause join_clause) {
        this.join_clause = join_clause;
    }


    public void addItemToListOf_reslult_coulmn (Reslult_Cloumn c){
        ListOf_reslult_coulmn.add(c);
    }

    public List<Reslult_Cloumn> getListOf_reslult_coulmn() {
        return ListOf_reslult_coulmn;
    }

    public void setListOf_reslult_coulmn(List<Reslult_Cloumn> listOf_reslult_coulmn) {
        ListOf_reslult_coulmn = listOf_reslult_coulmn;
    }

    public Column_alias getColumn_alias() {
        return column_alias;
    }

    public void setColumn_alias(Column_alias column_alias) {
        this.column_alias = column_alias;
    }
}
