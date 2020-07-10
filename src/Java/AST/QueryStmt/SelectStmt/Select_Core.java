package Java.AST.QueryStmt.SelectStmt;

import Java.AST.QueryStmt.Join.Join_Clause;
import Java.AST.commn_classes_Sql.name_rule.Reslult_Cloumn;
import Java.AST.commn_classes_Sql.name_rule.TableOrSubQuery;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/5/2020.
 */
public class Select_Core extends select_stmt {
private List<Reslult_Cloumn> reslult_cloumnList = new ArrayList<>();
private List<TableOrSubQuery> tableOrSubQueryList = new ArrayList<>();
private Expr whereExpr ;
private Expr havingExpr ;
private List<Expr> exprList_Group ;
private List<Expr>  exprList_Values;
private List<List_Of_Expr> list_of_exprList;
    private Join_Clause join_clause;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        for (int i = 0; i <reslult_cloumnList.size() ; i++) {
            this.reslult_cloumnList.get(i).accept(astVisitor);
        }
        for (int i = 0; i <tableOrSubQueryList.size() ; i++) {
            this.tableOrSubQueryList.get(i).accept(astVisitor);
        }
        for (int i = 0; i <exprList_Group.size() ; i++) {
            this.exprList_Group.get(i).accept(astVisitor);
        }
        for (int i = 0; i < exprList_Values.size() ; i++) {
            this.exprList_Values.get(i).accept(astVisitor);
        }
        for (int i = 0; i <list_of_exprList.size() ; i++) {
            this.list_of_exprList.get(i).accept(astVisitor);
        }
        if(join_clause!=null){
            this.join_clause.accept(astVisitor);
        }
        if(whereExpr!=null){
            this.whereExpr.accept(astVisitor);
        }
        if(havingExpr!=null){
            this.havingExpr.accept(astVisitor);
        }
    }

    public Expr getHavingExpr() {
        return havingExpr;
    }

    public void setHavingExpr(Expr havingExpr) {
        this.havingExpr = havingExpr;
    }

    public Join_Clause getJoin_clause() {
        return join_clause;
    }

    public void setJoin_clause(Join_Clause join_clause) {
        this.join_clause = join_clause;
    }

    public List<Expr> getExprList_Group() {
        return exprList_Group;
    }

    public void setExprList_Group(List<Expr> exprList_Group) {
        this.exprList_Group = exprList_Group;
    }

    public List<Expr> getExprList_Values() {
        return exprList_Values;
    }

    public void setExprList_Values(List<Expr> exprList_Values) {
        this.exprList_Values = exprList_Values;
    }

    public List<List_Of_Expr> getList_of_exprList() {
        return list_of_exprList;
    }

    public void setList_of_exprList(List<List_Of_Expr> list_of_exprList) {
        this.list_of_exprList = list_of_exprList;
    }

    public List<Reslult_Cloumn> getReslult_cloumnList() {
        return reslult_cloumnList;
    }

    public void setReslult_cloumnList(List<Reslult_Cloumn> reslult_cloumnList) {
        this.reslult_cloumnList = reslult_cloumnList;
    }

    public List<TableOrSubQuery> getTableOrSubQueryList() {
        return tableOrSubQueryList;
    }

    public void setTableOrSubQueryList(List<TableOrSubQuery> tableOrSubQueryList) {
        this.tableOrSubQueryList = tableOrSubQueryList;
    }

    public Expr getWhereExpr() {
        return whereExpr;
    }

    public void setWhereExpr(Expr whereExpr) {
        this.whereExpr = whereExpr;
    }

    public List<Expr> getExprList() {
        return exprList_Group;
    }

    public void setExprList(List<Expr> exprList) {
        this.exprList_Group = exprList;
    }
}
