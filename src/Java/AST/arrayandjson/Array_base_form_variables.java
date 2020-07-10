package Java.AST.arrayandjson;

import Java.AST.Node;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Expression;
import Java.AST.instruction.arithmetic_infunction_statment;

/**
 * Created by Jehad on 1/6/2020.
 */
public class Array_base_form_variables extends Node {


    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    String variable_name;

    Expression expression;

    String Number;

}
