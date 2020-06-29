package Java.AST.creating;

import Java.AST.instruction.non_functional_instructions;

public class gneralcreating extends non_functional_instructions {
    createwihtoutassign wihtoutassig  ;

    createvariablewithassign withassign ;
    String name ;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWihtoutassig(createwihtoutassign wihtoutassig) {
        this.wihtoutassig = wihtoutassig;
    }

    public createwihtoutassign getWihtoutassig() {
        return wihtoutassig;
    }

    public void setWithassign(createvariablewithassign withassign) {
        this.withassign = withassign;
    }

    public createvariablewithassign getWithassign() {
        return withassign;
    }
    
}

