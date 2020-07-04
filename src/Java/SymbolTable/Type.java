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
    private Scope scope;
    public static final String NUMBER_CONST = "number";
    public static final String STRING_CONST = "string";
    public static final String BOOLEAN_CONST = "boolean";
    public static final String UNDEFINDED = "undefined";

    public Type() {
    }
    public Scope getScope() {
        return scope;
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

//    public boolean checkValidTypewithScoop(Scope scope){
//
//    }
    public Map<String, String> getColumns() {
        //return this.columns; todo mohammad
        return getColumns();
    }
    public void setColumns(Map<String, Type> columns) {
        this.columns = columns;
    }
    public void addColumnForType(String namecol,String  typecol){

        //columns.put(namecol ,typecol);todo mohammad
    }

}
