//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private String name;
    private Map<String, Type> columns = new HashMap();
    public static final String NUMBER_CONST = "number";
    public static final String STRING_CONST = "string";
    public static final String BOOLEAN_CONST = "boolean";

    public Type() {
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
}
