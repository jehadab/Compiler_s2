package Java.AST.instruction.loops;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
import Java.AST.instruction.functional_instuctions;
import Java.AST.creating.createwihtoutassign;
import Java.AST.creating.createvariablewithassign;

/**
 * Created by Jehad on 1/5/2020.
 */
public class For_Loop_Rule extends functional_instuctions {
    public Inside_for_loop getLeft_inside_for_loop() {
        return left_inside_for_loop;
    }

    public void setLeft_inside_for_loop(Inside_for_loop left_inside_for_loop) {
        this.left_inside_for_loop = left_inside_for_loop;
    }

    public Inside_for_loop getRight_inside_for_loop() {
        return right_inside_for_loop;
    }

    public void setRight_inside_for_loop(Inside_for_loop right_inside_for_loop) {
        this.right_inside_for_loop = right_inside_for_loop;
    }

    public createvariablewithassign getVar_with_asgn() {
        return var_with_asgn;
    }

    public void setVar_with_asgn(createvariablewithassign var_with_asgn) {
        this.var_with_asgn = var_with_asgn;
    }

    public createwihtoutassign getVar_without_asgn() {
        return var_without_asgn;
    }

    public void setVar_without_asgn(createwihtoutassign var_without_asgn) {
        this.var_without_asgn = var_without_asgn;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    Inside_for_loop left_inside_for_loop ;
    Inside_for_loop right_inside_for_loop ;
    createvariablewithassign var_with_asgn;
    createwihtoutassign var_without_asgn;
    Expression expression ;

}
