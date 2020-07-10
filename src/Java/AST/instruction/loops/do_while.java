package Java.AST.instruction.loops;

import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
import Java.AST.instruction.functional_instuctions;

import java.util.List;

/**
 * Created by Jehad on 1/1/2020.
 */
public class do_while extends functional_instuctions {

    Expression expression ;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
