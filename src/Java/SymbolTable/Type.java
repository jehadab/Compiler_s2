//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import Java.Main;

import java.util.HashMap;
import java.util.Map;

public class Type extends Table {
    private String name;
    boolean isTable = false;
    private Map<String , Type> columns = new HashMap();
    private Scope scope;
    public static final String NUMBER_CONST = "number";
    public static final String STRING_CONST = "string";
    public static final String BOOLEAN_CONST = "boolean";
    public static final String UNDEFINDED = "undefined";

    public Scope getScope() {
        return scope;
    }

    public boolean isTable() {
        return isTable;
    }

    public void setTable(boolean table) {
        isTable = table;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Type> getColumns() {
        return this.columns;
    }
    public void setColumns(Map<String, Type> columns) {
        this.columns = columns;
    }

    public void addColumns(String string ,Type type ){
        columns.put(string, type);
    }
}
