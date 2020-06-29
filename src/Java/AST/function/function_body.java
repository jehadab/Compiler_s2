package Java.AST.function;
import Java.AST.instruction.*;
import Java.AST.Node;
import Java.AST.create.generalcreating;
import Java.AST.instruction.Returning.returnes_rule;

import java.util.ArrayList;
import java.util.List;

// could have error in the functionbody ....
public class function_body extends Node {
    List<instructions> instructions ;
   returnes_rule r ;
  sub_function_body sub_one ;

   public function_body(){
        this.instructions = new ArrayList<>();
    }

    public List<Java.AST.instruction.instructions> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Java.AST.instruction.instructions> instructions) {
        this.instructions = instructions;
    }

    public sub_function_body getSub_one() {
        return sub_one;
    }

    public void setSub_one(sub_function_body sub_one) {
        this.sub_one = sub_one;
    }

    public returnes_rule getR() {
        return r;
    }

    public void setR(returnes_rule r) {
        this.r = r;
    }

    public void addInstraction(instructions instruction){
        this.instructions.add(instruction);
    }


}
