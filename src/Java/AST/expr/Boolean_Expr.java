package Java.AST.expr;

import Java.AST.Node;
import Java.AST.arrayandjson.array_base_fom_with_index;
import Java.AST.arrayandjson.variablefromobject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/4/2020.
 */
public class Boolean_Expr extends Node {
    public Boolean_Expr()
    {
        boolean_exprs_list = new ArrayList<>();
        array_base_fom_with_indices = new ArrayList<>();
        variable_from_objects = new ArrayList<>();


    }
    public List<Boolean_Expr> getBoolean_exprs_list() {
        return boolean_exprs_list;
    }

    public void setBoolean_exprs_list(List<Boolean_Expr> boolean_exprs_list) {
        this.boolean_exprs_list = boolean_exprs_list;
    }

    public String getTermenal_node() {
        return termenal_node;
    }

    public void setTermenal_node(String termenal_node) {
        this.termenal_node = termenal_node;
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public List<array_base_fom_with_index> getArray_base_fom_with_indices() {
        return array_base_fom_with_indices;
    }

    public void setArray_base_fom_with_indices(List<array_base_fom_with_index> array_base_fom_with_indices) {
        this.array_base_fom_with_indices = array_base_fom_with_indices;
    }

    public List<variablefromobject> getVariablefromobjects() {
        return variable_from_objects;
    }

    public void setVariablefromobjects(List<variablefromobject> variablefromobjects) {
        this.variable_from_objects = variablefromobjects;
    }

    List<array_base_fom_with_index> array_base_fom_with_indices ;
    List <variablefromobject> variable_from_objects;
    List<Boolean_Expr> boolean_exprs_list ;

    String termenal_node;
    String variable_name;

    Arithmatic_expr arithmatic_expr;

    public List<variablefromobject> getVariable_from_objects() {
        return variable_from_objects;
    }

    public void setVariable_from_objects(List<variablefromobject> variable_from_objects) {
        this.variable_from_objects = variable_from_objects;
    }

    public Arithmatic_expr getArithmatic_expr() {
        return arithmatic_expr;
    }

    public void setArithmatic_expr(Arithmatic_expr arithmatic_expr) {
        this.arithmatic_expr = arithmatic_expr;
    }
}
