package Java.AST.QueryStmt.SelectStmt;

import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.TableOrSubQuery;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.List;

public class SelectFactoredStmt extends Statement {
    private Select_Core select_core;
    private String fromItem;
    private List<Ordering_term> ordering_terms;
    private Expr limitExpr;
    private Expr expr;
    private boolean offset;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(select_core!=null){
            this.select_core.accept(astVisitor);
        }
        for (int i = 0; i <ordering_terms.size() ; i++) {
            this.ordering_terms.get(i).accept(astVisitor);
        }
        if(limitExpr!=null){
            this.limitExpr.accept(astVisitor);
        }
        if (expr!=null){
            this.expr.accept(astVisitor);
        }

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

    public boolean isOffset() {
        return offset;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public Select_Core getSelect_core() {
        return select_core;
    }

    public void setSelect_core(Select_Core select_core) {
        this.select_core = select_core;
    }

    public List<Ordering_term> getOrdering_terms() {
        return ordering_terms;
    }

    public void setOrdering_terms(List<Ordering_term> ordering_terms) {
        this.ordering_terms = ordering_terms;
    }

    public TableOrSubQuery getTableOrSubQuery() {
        return tableOrSubQuery;
    }

    public void setTableOrSubQuery(TableOrSubQuery tableOrSubQuery) {
        this.tableOrSubQuery = tableOrSubQuery;
    }

    private TableOrSubQuery tableOrSubQuery;

    public void setFromItem(String fromItem) {
        this.fromItem = fromItem;
    }

    public String getFromItem() {
        return fromItem;
    }
}
