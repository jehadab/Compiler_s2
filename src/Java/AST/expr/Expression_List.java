package Java.AST.expr;

import Java.AST.Node;
import Java.AST.instruction.Shortcut_Statments;

import java.util.ArrayList;

/**
 * Created by Jehad on 5/21/2020.
 */
public class Expression_List extends Node {


    Intral_Expression_Value intral_expression_value;


    String Op;
    Expression_List left_expr ;
    Expression_List right_expr ;
    One_Line_If_Expression one_line_if_expression;
    Unaray_Operator_Java unaray_operator_java ;
    Bracket_Expression bracket_expression ;
    Shortcut_Statments shortcut_statments ;


    public Shortcut_Statments getShortcut_statments() {
        return shortcut_statments;
    }

    public void setShortcut_statments(Shortcut_Statments shortcut_statments) {
        this.shortcut_statments = shortcut_statments;
    }

    public Intral_Expression_Value getIntral_expression_value() {
        return intral_expression_value;
    }

    public void setIntral_expression_value(Intral_Expression_Value intral_expression_value) {
        this.intral_expression_value = intral_expression_value;
    }

    public Bracket_Expression getBracket_expression() {
        return bracket_expression;
    }

    public void setBracket_expression(Bracket_Expression bracket_expression) {
        this.bracket_expression = bracket_expression;
    }

    public Unaray_Operator_Java getUnaray_operator_java() {
        return unaray_operator_java;
    }

    public void setUnaray_operator_java(Unaray_Operator_Java unaray_operator_java) {
        this.unaray_operator_java = unaray_operator_java;
    }

    public One_Line_If_Expression getOne_line_if_expression() {
        return one_line_if_expression;
    }

    public void setOne_line_if_expression(One_Line_If_Expression one_line_if_expression) {
        this.one_line_if_expression = one_line_if_expression;
    }


    public String getOp() {
        return Op;
    }

    public void setOp(String op) {
        Op = op;
    }

    public Expression_List getLeft_expr() {
        return left_expr;
    }

    public void setLeft_expr(Expression_List left_expr) {
        this.left_expr = left_expr;
    }

    public Expression_List getRight_expr() {
        return right_expr;
    }

    public void setRight_expr(Expression_List right_expr) {
        this.right_expr = right_expr;
    }


}
