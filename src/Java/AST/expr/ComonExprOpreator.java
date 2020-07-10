package Java.AST.expr;

import Java.AST.Node;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/3/2020.
 */
public class ComonExprOpreator extends Node {
private String Opreator ;

    public String getOpreator() {
        return Opreator;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

    public void setOpreator(String opreator) {
        Opreator = opreator;
    }
}
