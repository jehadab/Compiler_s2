package Java.AST.assignmnet;

import Java.AST.Node;
import Java.AST.QueryStmt.SelectStmt.SelectFactoredStmt;
import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.arrayandjson.Array_base_form_with_operetor;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.expr.Boolean_Infunction_Statment;
import Java.AST.expr.Expression;

import java.util.ArrayList;
import java.util.List;

public class assign_Array extends Node {   // to do the arithmatic statmnet .......


    List<Array_base_form_with_operetor> array_base_form_with_operetors ;

    Expression expression ;

    select_stmt select ;
    SelectFactoredStmt select_factored ;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public select_stmt getSelect() {
        return select;
    }

    public void setSelect(select_stmt select) {
        this.select = select;
    }

    public SelectFactoredStmt getSelect_factored() {
        return select_factored;
    }

    public void setSelect_factored(SelectFactoredStmt select_factored) {
        this.select_factored = select_factored;
    }

    Boolean_Infunction_Statment boolean_infunction_statment;

    public assign_Array (){
        array_base_form_with_operetors = new ArrayList<>();
    }


    public List<Array_base_form_with_operetor> getArray_base_form_with_operetors() {
        return array_base_form_with_operetors;
    }

    public void setArray_base_form_with_operetors(List<Array_base_form_with_operetor> array_base_form_with_operetors) {
        this.array_base_form_with_operetors = array_base_form_with_operetors;
    }



}
