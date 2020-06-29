package Java.AST.instruction.IF_rule;

import Java.AST.Node;
import Java.AST.expr.Boolean_Expr;
import Java.AST.expr.Expression;
import Java.AST.instruction.functional_instuctions;

import java.util.List;

/**
 * Created by Jehad on 1/2/2020.
 */
public class else_if_rule extends functional_instuctions {
  public Expression getExpression() {
    return expression;
  }

  public void setExpression(Expression expression) {
    this.expression = expression;
  }

  private Expression expression;
}
