package Java.AST.instruction.all_the_non_functional_instructions;

import Java.AST.Node;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Boolean_Infunction_Statment;

/**
 * Created by Jehad on 1/6/2020.
 */
public class Inside_one_line_if extends Node {
    public Boolean_Infunction_Statment getBoolean_infunction_statment() {
        return boolean_infunction_statment;
    }

    public void setBoolean_infunction_statment(Boolean_Infunction_Statment boolean_infunction_statment) {
        this.boolean_infunction_statment = boolean_infunction_statment;
    }

    public Arithmatic_expr getArithmatic_expr() {
        return arithmatic_expr;
    }

    public void setArithmatic_expr(Arithmatic_expr arithmatic_expr) {
        this.arithmatic_expr = arithmatic_expr;
    }

    Boolean_Infunction_Statment boolean_infunction_statment ;
    Arithmatic_expr arithmatic_expr ;
}
