package Java.AST.instruction.Returning;

import Java.AST.Node;
import Java.AST.instruction.arithmetic_infunction_statment;
import Java.AST.instruction.non_functional_instructions;


public class returnes_rule extends non_functional_instructions {

    return_type t ;

    public void setT(return_type t) {
        this.t = t;
    }

    public return_type getT() {
        return t;
    }
   // arithmetic_infunction_statment arithmatic ;

    ///public void setArithmatic(arithmetic_infunction_statment arithmatic) {
      //  this.arithmatic = arithmatic;
    //}

    //public arithmetic_infunction_statment getArithmatic() {
      //  return arithmatic;
    //}
    
}
