package Java.AST.expr;

import Java.AST.Node;
import Java.AST.assignmnet.assignment;

public class Bracket_Expression extends Node {
    Expression_List expression_list;
    assignment assign;

    public Bracket_Expression() {
    }

    public assignment getAssign() {
        return this.assign;
    }

    public void setAssign(assignment assign) {
        this.assign = assign;
    }

    public Expression_List getExpression_list() {
        return this.expression_list;
    }

    public void setExpression_list(Expression_List expression_list) {
        this.expression_list = expression_list;
    }
}
