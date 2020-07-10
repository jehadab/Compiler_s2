package Java.AST.instruction;

import Java.AST.ParametesandArgs.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/6/2020.
 */
public class CallFunction extends non_functional_instructions{

    public CallFunction(){
        this.parameters = new ArrayList<>();
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    String function_name ;
    List <Parameter> parameters;
}
