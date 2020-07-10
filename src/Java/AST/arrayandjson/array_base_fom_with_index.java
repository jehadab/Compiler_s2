package Java.AST.arrayandjson;

import java.util.ArrayList;
import java.util.List;

public class array_base_fom_with_index  {   // arithmatic one also
    public array_base_fom_with_index (){
        this.array_base_form_variables = new ArrayList<>();
    }
    public String getArrayname() {
        return arrayname;
    }

    public void setArrayname(String arrayname) {
        this.arrayname = arrayname;
    }

    public List<Array_base_form_variables> getArray_base_form_variables() {
        return array_base_form_variables;
    }

    public void setArray_base_form_variables(List<Array_base_form_variables> array_base_form_variables) {
        this.array_base_form_variables = array_base_form_variables;
    }

    String arrayname;
    List<Array_base_form_variables>array_base_form_variables;

}
