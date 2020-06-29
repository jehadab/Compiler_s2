package Java.AST.instruction;

import Java.AST.instruction.Returning.exting_oop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/2/2020.
 */
public class functional_instuctions extends  instructions {

    public functional_instuctions() {
        this.instructions = instructions = new ArrayList<>();
    }

    public List<Java.AST.instruction.instructions> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Java.AST.instruction.instructions> instructions) {
        this.instructions = instructions;
    }
    public void addinstruction (instructions instructions)
    {
        this.instructions.add(instructions);
    }
    public instructions  check_it(int i)
    {
        return instructions.get(i);
    }

    List<instructions> instructions;
    exting_oop loop;

    public exting_oop getLoop() {
        return loop;
    }

    public void setLoop(exting_oop loop) {
        this.loop = loop;
    }




}
