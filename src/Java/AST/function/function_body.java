package Java.AST.function;
import Java.AST.Node;
import Java.AST.instruction.Returning.returnes_rule;

import java.util.ArrayList;
import java.util.List;

public class function_body extends Node {

    List<Object> nodes = new ArrayList<Object>();
    returnes_rule r ;
    sub_function_body sub_one ;


    public List<Object> getInstructions() {
        return nodes;
    }

    public void setlist(List<Object> instructions) {
        this.nodes = instructions;
    }
    public void addNode(Object Node){
        this.nodes.add(Node);
    }
    public sub_function_body getSub_one() {
        return sub_one;
    }

    public void setSub_one(sub_function_body sub_one) {
        this.sub_one = sub_one;
    }

    public returnes_rule getR() {
        return r;
    }

    public void setR(returnes_rule r) {
        this.r = r;
    }



}
