package Java.SymbolTable;

public class Column {
    private String column_name;
    private Type column_type;
    private String TypeNumber  ;
    private String TypeString ;
    private String Typeboolean ;
    private String LastColumn ;
    private String FirstColumn ;
    private String TypeObject;
    private String ParentTable;
    private String grandParant1;
    private String grandParant2;

    public String getGrandParant1() {
        return grandParant1;
    }

    public void setGrandParant1(String grandParant1) {
        this.grandParant1 = grandParant1;
    }

    public String getGrandParant2() {
        return grandParant2;
    }

    public void setGrandParant2(String grandParant2) {
        this.grandParant2 = grandParant2;
    }

    public String getParentTable() {
        return ParentTable;
    }

    public void setParentTable(String parentTable) {
        ParentTable = parentTable;
    }

    public String getFirstColumn() {
        return FirstColumn;
    }

    public String getTypeObject() {
        return TypeObject;
    }

    public void setTypeObject(String typeObject) {
        TypeObject = typeObject;
    }

    public void setFirstColumn(String firstColumn) {
        FirstColumn = firstColumn;
    }

    public String getLastColumn() {
        return LastColumn;
    }

    public void setLastColumn(String lastColumn) {
        LastColumn = lastColumn;
    }

    public String getTypeNumber() {
        return TypeNumber;
    }

    public void setTypeNumber(String typeNumber) {
        TypeNumber = typeNumber;
    }

    public String getTypeString() {
        return TypeString;
    }

    public void setTypeString(String typeString) {
        TypeString = typeString;
    }

    public String getTypeboolean() {
        return Typeboolean;
    }

    public void setTypeboolean(String typeboolean) {
        Typeboolean = typeboolean;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public Type getColumn_type() {
        return column_type;
    }

    public void setColumn_type(Type column_type) {
        this.column_type = column_type;
    }
}
