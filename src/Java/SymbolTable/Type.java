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
    private Scope scope;


    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addColumnForType(String namecol,String typecol){

        //columns.put(namecol ,typecol);
    }

//    public boolean checkValidTypewithScoop(Scope scope){
//
//    }


    public Map<String, String> getColumns() {
      //  return this.columns;
    }
}
//  if(typecol == NUMBER_CONST||typecol == STRING_CONST||typecol == BOOLEAN_CONST)
//          {
//
//          }