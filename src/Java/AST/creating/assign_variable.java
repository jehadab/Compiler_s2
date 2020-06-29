package Java.AST.creating;

import Java.AST.Node;
import Java.AST.assignmnet.Variable_with_opretor;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
//import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.ArrayList;
import java.util.List;

public class assign_variable extends Node {   // to do the arithmatic structure one ....


    public assign_variable(){
        this.variable_with_opretor = new ArrayList<>();
    }



//    public Select getSelect() {
//        return select;
//    }
//
//    public void setSelect(Select select) {
//        this.select = select;
//    }

    public List<Variable_with_opretor> getVariable_with_opretor() {
        return variable_with_opretor;
    }

    public void setVariable_with_opretor(List<Variable_with_opretor> variable_with_opretor) {
        this.variable_with_opretor = variable_with_opretor;
    }
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    Expression expression;

    List<Variable_with_opretor> variable_with_opretor ;

//    Select select ;//todo select from SQL
//    //factored_select_stmt
}
