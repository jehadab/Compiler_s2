package Java.AST.QueryStmt;

import Java.AST.Node;
import Java.AST.instruction.non_functional_instructions;

public class Statement extends non_functional_instructions {

    private String name;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
