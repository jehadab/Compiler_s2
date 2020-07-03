package Java.SymbolTable;

import Java.AST.QueryStmt.Create_Stmt.ColumnDef;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Map<String,Column> columnMap = new LinkedHashMap<>();
    private String table_name;
    private String extension_of_table;
    private String path_of_table;


    public String getPath_of_table() {
        return path_of_table;
    }

    public void setPath_of_table(String path_of_table) {
        this.path_of_table = path_of_table;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getExtension_of_table() {
        return extension_of_table;
    }

    public void setExtension_of_table(String extension_of_table) {
        this.extension_of_table = extension_of_table;
    }

    public void addColumnMap(String name , Column col){
        columnMap.put(name , col);
    }

    public Map<String, Column> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, Column> columnMap) {
        this.columnMap = columnMap;
    }


}