package Java.AST.expr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/4/2020.
 */
public class Boolean_Infunction_Statment {
    public Boolean_Infunction_Statment()
    {
        this.boolean_exprs = new ArrayList<>();
    }

    public List<Boolean_Expr> getBoolean_exprs() {
        return boolean_exprs;
    }

    public void setBoolean_exprs(List<Boolean_Expr> boolean_exprs) {
        this.boolean_exprs = boolean_exprs;
    }

    List<Boolean_Expr> boolean_exprs ;


}
