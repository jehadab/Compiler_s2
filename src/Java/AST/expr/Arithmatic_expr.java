package Java.AST.expr;
import Java.AST.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/3/2020.
 */
public class Arithmatic_expr extends Node {


    public Arithmatic_expr(){
        arithmatic_exprs_list = new ArrayList<>();
    }


    public String getTermenal_node() {
        return termenal_node;
    }

    public void setTermenal_node(String termenal_node) {
        this.termenal_node = termenal_node;
    }

    public List<Arithmatic_expr> getArithmatic_exprs_list() {
        return arithmatic_exprs_list;
    }

    public void setArithmatic_exprs_list(List<Arithmatic_expr> arithmatic_exprs_list) {
        this.arithmatic_exprs_list = arithmatic_exprs_list;
    }

    List<Arithmatic_expr> arithmatic_exprs_list ;

    String termenal_node;
    String variable_name;


    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }


   /* String identyfire;
    String call_function;
    String varible_from_object;
    String array_base_form_with_index;
    String NUMERIC_LITERAL;
    char ONE_CHAR_LETTER;*/


}
