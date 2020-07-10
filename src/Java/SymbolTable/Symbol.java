//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

public class Symbol {
    private String name;
    private Type type;
    private Scope scope;
    private boolean isParam;

    public Symbol() {
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public Scope getScope() {
        return this.scope;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean getIsParam() {
        return this.isParam;
    }

    public void setIsParam(boolean param) {
        this.isParam = param;
    }
}
