package Java.SymbolTable;

public class Column {
    private String column_name;
    private Type column_type;
    private String TypeNumber  ;
    private String TypeString ;
    private String Typeboolean ;
    private String LastColumn ;

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
