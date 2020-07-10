package Java.AST.function;
import java.util.ArrayList;
import java.util.List;

public class sub_function_body {

    public List<Object> getInstructions() {
        return nodes;
    }

    public void setInstructions(List<Object> instructions) {
        this.nodes = instructions;
    }
    public void addNode(Object Node){
        this.nodes.add(Node);
    }


    List<Object> nodes = new ArrayList<Object>();



}
