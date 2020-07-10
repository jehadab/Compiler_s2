package Java.AST.assignmnet;

import Java.AST.Node;

/**
 * Created by Jehad on 1/5/2020.
 */
public class Variable_with_opretor extends Node {
    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    String variable_name;
    String operator;
}
