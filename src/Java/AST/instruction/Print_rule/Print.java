package Java.AST.instruction.Print_rule;

import Java.AST.instruction.non_functional_instructions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/6/2020.
 */
public class Print extends non_functional_instructions {

    public Print (){
        prints = new ArrayList<>();
    }

    public List<Inside_the_print> getPrints() {
        return prints;
    }

    public void setPrints(List<Inside_the_print> prints) {
        this.prints = prints;
    }

    List<Inside_the_print> prints;

}
