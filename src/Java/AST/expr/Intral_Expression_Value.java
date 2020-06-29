//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.AST.expr;

import Java.AST.Node;
import Java.AST.arrayandjson.array_base_fom_with_index;
import Java.AST.arrayandjson.variablefromobject;
import Java.AST.instruction.CallFunction;

public class Intral_Expression_Value extends Node {
    Variable_Name variable_name;
    String identyfire;
    CallFunction call_function;
    String varible_from_object;
    array_base_fom_with_index array_Base_Form_With_Index;
    String NUMERIC_LITERAL;
    char ONE_CHAR_LETTER;
    variablefromobject variable_From_Object;
    String ture_or_False;

    public Intral_Expression_Value() {
    }

    public Variable_Name getVariable_name() {
        return this.variable_name;
    }

    public void setVariable_name(Variable_Name variable_name) {
        this.variable_name = variable_name;
    }

    public String getIdentyfire() {
        return this.identyfire;
    }

    public void setIdentyfire(String identyfire) {
        this.identyfire = identyfire;
    }

    public CallFunction getCall_function() {
        return this.call_function;
    }

    public void setCall_function(CallFunction call_function) {
        this.call_function = call_function;
    }

    public String getVarible_from_object() {
        return this.varible_from_object;
    }

    public void setVarible_from_object(String varible_from_object) {
        this.varible_from_object = varible_from_object;
    }

    public array_base_fom_with_index getArray_Base_Form_With_Index() {
        return this.array_Base_Form_With_Index;
    }

    public void setArray_Base_Form_With_Index(array_base_fom_with_index array_Base_Form_With_Index) {
        this.array_Base_Form_With_Index = array_Base_Form_With_Index;
    }

    public String getNUMERIC_LITERAL() {
        return this.NUMERIC_LITERAL;
    }

    public void setNUMERIC_LITERAL(String NUMERIC_LITERAL) {
        this.NUMERIC_LITERAL = NUMERIC_LITERAL;
    }

    public char getONE_CHAR_LETTER() {
        return this.ONE_CHAR_LETTER;
    }

    public void setONE_CHAR_LETTER(char ONE_CHAR_LETTER) {
        this.ONE_CHAR_LETTER = ONE_CHAR_LETTER;
    }

    public variablefromobject getVariable_From_Object() {
        return this.variable_From_Object;
    }

    public void setVariable_From_Object(variablefromobject variable_From_Object) {
        this.variable_From_Object = variable_From_Object;
    }

    public String getTure_or_False() {
        return this.ture_or_False;
    }

    public void setTure_or_False(String ture_or_False) {
        this.ture_or_False = ture_or_False;
    }
}
