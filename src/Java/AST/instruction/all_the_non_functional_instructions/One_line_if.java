package Java.AST.instruction.all_the_non_functional_instructions;

import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.instruction.all_the_non_functional_instructions.Inside_one_line_if;
import Java.AST.instruction.non_functional_instructions;

/**
 * Created by Jehad on 1/6/2020.
 */
public class One_line_if extends non_functional_instructions {
    public Boolean_Infunction_Statment getBoolean_infunction_statment() {
        return boolean_infunction_statment;
    }

    public void setBoolean_infunction_statment(Boolean_Infunction_Statment boolean_infunction_statment) {
        this.boolean_infunction_statment = boolean_infunction_statment;
    }

    public Inside_one_line_if getLeft_inside() {
        return left_inside;
    }

    public void setLeft_inside(Inside_one_line_if left_inside) {
        this.left_inside = left_inside;
    }

    public Inside_one_line_if getRight_inside() {
        return right_inside;
    }

    public void setRight_inside(Inside_one_line_if right_inside) {
        this.right_inside = right_inside;
    }

    Boolean_Infunction_Statment boolean_infunction_statment;
    Inside_one_line_if left_inside;
    Inside_one_line_if right_inside;
}
