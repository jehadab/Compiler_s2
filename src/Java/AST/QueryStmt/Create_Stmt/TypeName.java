package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.Node;

/**
 * Created by moham on 1/1/2020.
 */
public class TypeName extends Node {
private String name ;
private String Lim1 ;
private String Lim2 ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLim1() {
        return Lim1;
    }

    public void setLim1(String lim1) {
        Lim1 = lim1;
    }

    public String getLim2() {
        return Lim2;
    }

    public void setLim2(String lim2) {
        Lim2 = lim2;
    }
}

