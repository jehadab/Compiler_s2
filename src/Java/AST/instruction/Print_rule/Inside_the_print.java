package Java.AST.instruction.Print_rule;

import Java.AST.Node;
import Java.AST.instruction.CallFunction;
import Java.AST.arrayandjson.variablefromobject;
import Java.AST.arrayandjson.array_base_fom_with_index;

/**
 * Created by Jehad on 1/6/2020.
 */
public class Inside_the_print extends Node {

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public CallFunction getCallFunction() {
        return callFunction;
    }

    public void setCallFunction(CallFunction callFunction) {
        this.callFunction = callFunction;
    }

    public variablefromobject getVariable_from_object() {
        return variable_from_object;
    }

    public void setVariable_from_object(variablefromobject variable_from_object) {
        this.variable_from_object = variable_from_object;
    }

    public array_base_fom_with_index getArray_base_with_index() {
        return array_base_with_index;
    }

    public void setArray_base_with_index(array_base_fom_with_index array_base_with_index) {
        this.array_base_with_index = array_base_with_index;
    }

    String  variable_name;

    public String getIdentifire() {
        return identifire;
    }

    public void setIdentifire(String identifire) {
        this.identifire = identifire;
    }

    String identifire ;
    CallFunction callFunction;
    variablefromobject variable_from_object;
    array_base_fom_with_index array_base_with_index;

}
