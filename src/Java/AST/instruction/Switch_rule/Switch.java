package Java.AST.instruction.Switch_rule;

import Java.AST.expr.Expression;
import Java.AST.instruction.arithmetic_infunction_statment;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.instruction.non_functional_instructions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/5/2020.
 */
public class Switch extends non_functional_instructions  {

    public Switch(){
        this.cases = new ArrayList<>();
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public String getTermenal_node() {
        return termenal_node;
    }

    public void setTermenal_node(String termenal_node) {
        this.termenal_node = termenal_node;
    }



    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public Deafult getDeafult() {
        return deafult;
    }

    public void setDeafult(Deafult deafult) {
        this.deafult = deafult;
    }

    String variable_name;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    String termenal_node;
    Expression expression;

    List<Case>cases ;
    Deafult deafult ;


}
