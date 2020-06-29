package Java.AST.expr;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/2/2020.
 */
public class Unary_Operator extends Node {
    private String operator;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
