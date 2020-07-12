//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import Java.AST.function.function_header;
import Java.AST.instruction.arithmetic_infunction_statment;

import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<Scope> scopes = new ArrayList();
    private ArrayList<Type> declaredTypes = new ArrayList();
    private ArrayList<function_header> functions= new ArrayList<function_header>();
    private ArrayList<AggregationFunction> agg = new ArrayList<AggregationFunction>();
    public SymbolTable() {
    }

    public ArrayList<function_header> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<function_header> functions) {
        this.functions = functions;
    }
public void add_functions(function_header new_function ){this.functions.add(new_function);}
    public ArrayList<Scope> getScopes() {
        return this.scopes;
    }

    public void setScopes(ArrayList<Scope> scopes) {
        this.scopes = scopes;
    }

    public ArrayList<Type> getDeclaredTypes() {
        return this.declaredTypes;
    }

    public void setDeclaredTypes(ArrayList<Type> declaredTypes) {
        this.declaredTypes = declaredTypes;
    }

    public void addScope(Scope scope) {
        this.scopes.add(scope);
    }

    public ArrayList<AggregationFunction> getAgg() {
        return agg;
    }

    public void setAgg(ArrayList<AggregationFunction> agg) {
        this.agg = agg;
    }

    public void addType(Type type) {
        this.declaredTypes.add(type);
    }
}
