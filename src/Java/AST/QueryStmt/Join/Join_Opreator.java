package Java.AST.QueryStmt.Join;

import Java.AST.Node;

/**
 * Created by moham on 1/6/2020.
 */
public class Join_Opreator extends Node {
    private boolean left_outer =false ;
    private boolean inner = false;
    private boolean join =false;



    public boolean isLeft_outer() {
        return left_outer;
    }

    public void setLeft_outer(boolean left_outer) {
        this.left_outer = left_outer;
    }

    public boolean isInner() {
        return inner;
    }

    public void setInner(boolean inner) {
        this.inner = inner;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }
}
