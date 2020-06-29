package Java.AST.QueryStmt.SelectStmt;

import Java.AST.Node;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/5/2020.
 */
public class List_Of_Expr extends Node {
    private List<Expr>  exprList= new ArrayList<>();

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        for (int i = 0; i <exprList.size() ; i++) {
            this.exprList.get(i).accept(astVisitor);
        }
    }

    public void addItemToexprList (Expr c){
        exprList.add(c);
    }

    public List<Expr> getExprList() {
        return exprList;
    }

    public void setExprList(List<Expr> exprList) {
        this.exprList = exprList;
    }
}
