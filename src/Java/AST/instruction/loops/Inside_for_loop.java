package Java.AST.instruction.loops;

import Java.AST.Node;
import Java.AST.creating.createarrywithoutassign;
import Java.AST.creating.assign_variable;


import Java.AST.assignmnet.*;
/**
 * Created by Jehad on 1/6/2020.
 */
public class Inside_for_loop extends Node {
    public assign_Array getAssign_array() {
        return assign_array;
    }

    public void setAssign_array(assign_Array assign_array) {
        this.assign_array = assign_array;
    }

    public assign_variable getVar() {
        return var;
    }

    public void setVar(assign_variable var) {
        this.var = var;
    }

    public createarrywithoutassign getCreate_arry_without_assign() {
        return create_arry_without_assign;
    }

    public void setCreate_arry_without_assign(createarrywithoutassign create_arry_without_assign) {
        this.create_arry_without_assign = create_arry_without_assign;
    }

    public String getShortcut_statments() {
        return shortcut_statments;
    }

    public void setShortcut_statments(String shortcut_statments) {
        this.shortcut_statments = shortcut_statments;
    }

    assign_Array assign_array ;
    assign_variable var ;
    createarrywithoutassign create_arry_without_assign;
    String shortcut_statments;
             /*arithmetic_infunction_statment*/

}
