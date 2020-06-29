package Java.AST.QueryStmt;

import Java.AST.Node;

public class Statement extends Node {

    private String name;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
