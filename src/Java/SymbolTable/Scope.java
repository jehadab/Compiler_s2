//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scope {
    private String id;
    private Scope parent;
    private Map<String, Symbol> symbolMap = new LinkedHashMap();
    private Map<String,Table> tableMap = new LinkedHashMap<>();
    private Map<String ,Type> typeMap = new LinkedHashMap<>();


    public void setTableMap(Map<String, Table> tableMap) {
        this.tableMap = tableMap;
    }

    public Scope() {
    }

    public Map<String, Type> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, Type> typeMap) {
        this.typeMap = typeMap;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Scope getParent() {
        return this.parent;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    public Map<String, Symbol> getSymbolMap() {
        return this.symbolMap;
    }

    public void setSymbolMap(Map<String, Symbol> symbolMap) {
        this.symbolMap = symbolMap;
    }

    public void addSymbol(String name, Symbol symbol) {
        this.symbolMap.put(name, symbol);
    }

    public void addTable(String table_name, Table table) {
    this.tableMap.put(table_name , table);
    }
    public void addType(String type_name, Type type) {
        this.typeMap.put(type_name , type);
    }
    public Map<String, Table> getTableMap() {
        return tableMap;
    }
}
