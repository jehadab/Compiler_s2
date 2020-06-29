package Java.AST.instruction;

/**
 * Created by Jehad on 1/4/2020.
 */
public class arithmetic_infunction_statment extends non_functional_instructions {


    public Java.AST.instruction.full_arthmatic_statmint getFull_arthmatic_statmint() {
        return full_arthmatic_statmint;
    }

    public void setFull_arthmatic_statmint(Java.AST.instruction.full_arthmatic_statmint full_arthmatic_statmint) {
        this.full_arthmatic_statmint = full_arthmatic_statmint;
    }

    public String getShortcut_instruction() {
        return shortcut_instruction;
    }

    public void setShortcut_instruction(String shortcut_instruction) {
        this.shortcut_instruction = shortcut_instruction;
    }

    full_arthmatic_statmint full_arthmatic_statmint;
    String shortcut_instruction;
}
