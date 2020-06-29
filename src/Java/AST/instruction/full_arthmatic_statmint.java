package Java.AST.instruction;
import Java.AST.expr.Arithmatic_expr;
import Java.AST.arrayandjson.variablefromobject;
import Java.AST.arrayandjson.array_base_fom_with_index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/4/2020.
 */
public class full_arthmatic_statmint extends instructions {

    public full_arthmatic_statmint(){
        use_random_name = new ArrayList<>();
        varible_from_object = new ArrayList<>();
        array_base_form_with_index = new ArrayList<>();
        any_arithmetic_oprator = new ArrayList<>();
    }

    public List<String> getUse_random_name() {
        return use_random_name;
    }

    public void setUse_random_name(List<String> use_random_name) {
        this.use_random_name = use_random_name;
    }

    public List<array_base_fom_with_index> getArray_base_form_with_index() {
        return array_base_form_with_index;
    }

    public void setArray_base_form_with_index(List<array_base_fom_with_index> array_base_form_with_index) {
        this.array_base_form_with_index = array_base_form_with_index;
    }

    public List<String> getAny_arithmetic_oprator() {
        return any_arithmetic_oprator;
    }

    public void setAny_arithmetic_oprator(List<String> any_arithmetic_oprator) {
        this.any_arithmetic_oprator = any_arithmetic_oprator;
    }
    public List<variablefromobject> getVarible_from_object() {
        return varible_from_object;
    }

    public void setVarible_from_object(List<variablefromobject> varible_from_object) {
        this.varible_from_object = varible_from_object;
    }

    public Arithmatic_expr getArithmatic_expr() {
        return arithmatic_expr;
    }

    public void setArithmatic_expr(Arithmatic_expr arithmatic_expr) {
        this.arithmatic_expr = arithmatic_expr;
    }

    Arithmatic_expr arithmatic_expr;
    List<String> use_random_name ;
    List<variablefromobject> varible_from_object;
    List <array_base_fom_with_index>array_base_form_with_index;
    List<String> any_arithmetic_oprator ;

}
