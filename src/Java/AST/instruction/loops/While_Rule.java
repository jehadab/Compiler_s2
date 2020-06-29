package Java.AST.instruction.loops;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
import Java.AST.instruction.functional_instuctions;

/**
 /**
 * Created by Jehad on 1/5/2020.
 */
public class While_Rule extends functional_instuctions {
    Expression expression ;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
