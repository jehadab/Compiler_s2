package Java.AST.QueryStmt.SelectStmt;

import Java.AST.QueryStmt.Statement;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.List;

/**
 * Created by moham on 1/4/2020.
 */
public class select_stmt extends Statement{
    private SelectOrValue selectOrValue ;
    private List<Ordering_term> Listof_ordering_term;
    private Expr limitExpr;
    private Expr expr;
    private boolean offset=false;

    public boolean isOffset() {
        return offset;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public void addItemToListof_ordering_term (Ordering_term c){
        Listof_ordering_term.add(c);
    }

    public SelectOrValue getSelectOrValue() {
        return selectOrValue;
    }

    public void setSelectOrValue(SelectOrValue selectOrValue) {
        this.selectOrValue = selectOrValue;
    }

    public List<Ordering_term> getListof_ordering_term() {
        return Listof_ordering_term;
    }

    public void setListof_ordering_term(List<Ordering_term> listof_ordering_term) {
        Listof_ordering_term = listof_ordering_term;
    }

    public Expr getLimitExpr() {
        return limitExpr;
    }

    public void setLimitExpr(Expr limitExpr) {
        this.limitExpr = limitExpr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this );
        if(selectOrValue!=null){
            this.selectOrValue.accept(astVisitor);
        }
        if(limitExpr!=null){
            this.limitExpr.accept(astVisitor);
        }
        if(expr!=null){
            this.expr.accept(astVisitor);
        }
        for (int i = 0; i < Listof_ordering_term.size() ; i++) {
            this.getListof_ordering_term().get(i).accept(astVisitor);
        }
    }
}
