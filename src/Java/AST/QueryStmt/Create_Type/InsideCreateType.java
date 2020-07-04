package Java.AST.QueryStmt.Create_Type;

import Java.AST.Node;
import Java.SymbolTable.Type;

public class InsideCreateType extends Node {
    private String nameOfColumnOfType ;
    private Type type;

    public String getNameOfColumnOfType() {
        return nameOfColumnOfType;
    }

    public void setNameOfColumnOfType(String nameOfColumnOfType) {
        this.nameOfColumnOfType = nameOfColumnOfType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
