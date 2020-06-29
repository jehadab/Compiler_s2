package Java.AST.expr;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/2/2020.
 */
public class Literal_Value extends Node {
private String returnType;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public  String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
