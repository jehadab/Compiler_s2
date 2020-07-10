package Java.AST.assignmnet;

import Java.AST.creating.assign_variable;
import Java.AST.creating.jsonassign;
import Java.AST.instruction.non_functional_instructions;

public class assignment  extends non_functional_instructions  {             // extends from instructions
    assign_Array array ;
    jsonassign json ;
    assign_variable var ;
    public void setJson(jsonassign json) {
        this.json = json;
    }

    public jsonassign getJson() {
        return json;
    }

    public void setArray(assign_Array array) {
        this.array = array;
    }

    public assign_Array getArray() {
        return array;
    }

    public void setVar(assign_variable var) {
        this.var = var;
    }

    public assign_variable getVar() {
        return var;
    }

}
