package Java.AST.instruction.IF_rule;

import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
import Java.AST.instruction.functional_instuctions;
import Java.AST.instruction.instructions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/1/2020.
 */
public class if_else extends functional_instuctions {

    public if_else() {
        else_if = new ArrayList<>();
    }

    public void add_Else_if_rule_in_if(Java.AST.instruction.instructions node){
        else_if.add(node);
    }

    public List<Java.AST.instruction.instructions> getElse_if() {
        return else_if;
    }

    public void setElse_if(List<Java.AST.instruction.instructions> else_if) {
        this.else_if = else_if;
    }

    public Java.AST.instruction.instructions getElse_rule() {
        return else_rule;
    }

    public void setElse_rule(Java.AST.instruction.instructions else_rule) {
        this.else_rule = else_rule;
    }


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    Expression expression ;

    private List<instructions> else_if ;

    private instructions else_rule ;

   /* public returning_in_if getR() {
        return r;
    }*/

   /* public void setR(returning_in_if r) {
        this.r = r;
    }*/
}
