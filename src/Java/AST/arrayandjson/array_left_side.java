package Java.AST.arrayandjson;

import Java.AST.Node;
import Java.AST.ParametesandArgs.args;
import Java.AST.QueryStmt.SelectStmt.SelectFactoredStmt;
import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.userandomname;

import java.util.ArrayList;
import java.util.List;

public class array_left_side extends Node {
    //private List<Integer > in = new ArrayList<Integer>();
    //private List<Character > ch  = new ArrayList<Character>();
    //private List<Boolean> bol = new ArrayList<Boolean>();
    String  name ;
    array_value_form form ;
    select_stmt select;
    SelectFactoredStmt select_factored ;

   //  factored_select;
    public String  getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setForm(array_value_form form) {
        this.form = form;
    }

    public array_value_form getForm() {
        return form;
    }

}
