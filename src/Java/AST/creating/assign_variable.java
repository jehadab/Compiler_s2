package Java.AST.creating;

import Java.AST.Node;
import Java.AST.QueryStmt.SelectStmt.SelectFactoredStmt;
import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.assignmnet.Variable_with_opretor;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;
import com.sun.org.apache.bcel.internal.generic.Select;
//import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.ArrayList;
import java.util.List;

public class assign_variable extends Node {   // to do the arithmatic structure one ....
    select_stmt select ;
    SelectFactoredStmt factored;
    public assign_variable(){
        this.variable_with_opretor = new ArrayList<>();
    }

    public SelectFactoredStmt getFactored() {
        return factored;
    }

    public void setFactored(SelectFactoredStmt factored) {
        this.factored = factored;
    }

    public select_stmt getSelect() {
        return select;
    }

    public void setSelect(select_stmt select) {
        this.select = select;
    }

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


}
