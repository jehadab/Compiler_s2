package Java.AST.instruction.Switch_rule;

import Java.AST.instruction.Returning.returnes_rule;
import Java.AST.instruction.functional_instuctions;

/**
 * Created by Jehad on 1/5/2020.
 */
public class Deafult extends functional_instuctions {
    public returnes_rule getR() {
        return r;
    }

    public void setR(returnes_rule r) {
        this.r = r;
    }

    returnes_rule r;

}
