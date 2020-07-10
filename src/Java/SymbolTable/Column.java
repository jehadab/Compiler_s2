package Java.SymbolTable;

public class Column {
    private String column_name;
    private Type column_type;

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
