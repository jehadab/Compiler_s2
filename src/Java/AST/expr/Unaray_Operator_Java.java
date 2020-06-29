//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.AST.expr;

import Java.AST.Node;

public class Unaray_Operator_Java extends Node {
    String Op;
    Expression_List expression_list;

    public Unaray_Operator_Java() {
    }

    public String getOp() {
        return this.Op;
    }

    public void setOp(String op) {
        this.Op = op;
    }

    public Expression_List getExpression_list() {
        return this.expression_list;
    }

    public void setExpression_list(Expression_List expression_list) {
        this.expression_list = expression_list;
    }
}
