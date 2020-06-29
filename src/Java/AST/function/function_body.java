package Java.AST.function;
import Java.AST.Node;
import Java.AST.instruction.Returning.returnes_rule;

import java.util.ArrayList;
import java.util.List;

public class function_body extends Node {

    List<Object> nodes = new ArrayList<Object>();
    returnes_rule r ;

    public List<Object> getInstructions() {
        return nodes;
    }

    public void setlist(List<Object> instructions) {
        this.nodes = instructions;
    }
    public void addNode(Object Node){
        this.nodes.add(Node);
    }

    public returnes_rule getR() {
        return r;
    }

    public void setR(returnes_rule r) {
        this.r = r;
    }



}
