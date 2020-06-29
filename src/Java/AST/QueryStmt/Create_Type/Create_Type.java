package Java.AST.QueryStmt.Create_Type;
import Java.AST.QueryStmt.Statement;

import java.util.ArrayList;
import java.util.List;
public class Create_Type extends Statement {
    private String nameOfType ;
    private List<InsideCreateType> insideCreateTypeList = new ArrayList<>();

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public List<InsideCreateType> getInsideCreateTypeList() {
        return insideCreateTypeList;
    }

    public void setInsideCreateTypeList(List<InsideCreateType> insideCreateTypeList) {
        this.insideCreateTypeList = insideCreateTypeList;
    }

}


