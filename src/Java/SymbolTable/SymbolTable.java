//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import java.util.ArrayList;

public class SymbolTable {
    private ArrayList<Scope> scopes = new ArrayList();
    private ArrayList<Type> declaredTypes = new ArrayList();

    public SymbolTable() {
    }

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

    public void addType(Type type) {
        this.declaredTypes.add(type);
    }
}
