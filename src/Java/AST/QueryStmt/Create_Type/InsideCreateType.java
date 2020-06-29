package Java.AST.QueryStmt.Create_Type;

import Java.AST.Node;

public class InsideCreateType extends Node {
    private String nameOfColumnOfType ;
    private String type;

    public String getNameOfColumnOfType() {
        return nameOfColumnOfType;
    }

    public void setNameOfColumnOfType(String nameOfColumnOfType) {
        this.nameOfColumnOfType = nameOfColumnOfType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
