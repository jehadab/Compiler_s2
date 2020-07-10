package Java.AST.QueryStmt.Create_Type;
import Java.AST.QueryStmt.Statement;
import Java.SymbolTable.Type;

import java.util.ArrayList;
import java.util.List;
public class Create_Type extends Statement {
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    Type type = new Type();


//    private String nameOfType ;
//    private List<InsideCreateType> insideCreateTypeList = new ArrayList<>();
//
//    public void setNameOfType(String nameOfType) {
//        this.nameOfType = nameOfType;
//    }
//
//    public String getNameOfType() {
//        return nameOfType;
//    }
//
//    public List<InsideCreateType> getInsideCreateTypeList() {
//        return insideCreateTypeList;
//    }
//
//    public void setInsideCreateTypeList(List<InsideCreateType> insideCreateTypeList) {
//        this.insideCreateTypeList = insideCreateTypeList;
//    }

}


