package Java.AST.instruction;

import Java.AST.Node;
import Java.AST.instruction.Returning.returnes_rule;
import Java.SymbolTable.AggregationFunction;

public class instructions   extends Node {
    public String getInstrucation_name() {
        return instrucation_name;
    }
    public AggregationFunction aggregation_function ;
    public void setInstrucation_name(String instrucation_name) {
        this.instrucation_name = instrucation_name;
    }

    public AggregationFunction getAggregation_function() {
        return aggregation_function;
    }

    public void setAggregation_function(AggregationFunction aggregation_function) {
        this.aggregation_function = aggregation_function;
    }

    private String instrucation_name ;

}
