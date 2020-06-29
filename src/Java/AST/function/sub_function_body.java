package Java.AST.function;


import Java.AST.instruction.*;
import java.util.List;

public class sub_function_body {
    List<instructions> instructions ;
    List<sub_function_body> sub;
        public List<Java.AST.instruction.instructions> getInstructions() {
        return instructions;

    }
        public void setInstructions(List<Java.AST.instruction.instructions> instructions) {
        this.instructions = instructions;
    }


    public List<sub_function_body> getSub() {
        return sub;
    }

    public void setSub(List<sub_function_body> sub) {
        this.sub = sub;
    }
    public void addInstraction(instructions instruction){
       this.instructions.add(instruction);
   }
    public void add_sub_fucntion_to_list(sub_function_body subb)
    {
        this.sub.add(subb);
    }

}
