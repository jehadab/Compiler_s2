package Java.AST.expr;

import Java.AST.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 5/19/2020.
 */
public class Expression extends Node {



    public Expression_List getExpression_list() {
        return expression_list;
    }

    public void setExpression_list(Expression_List expression_list) {
        this.expression_list = expression_list;
    }



    Expression_List expression_list ;

}
