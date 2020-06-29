package Java.AST.arrayandjson;

import Java.AST.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 1/6/2020.
 */
public class Array_base_form_with_operetor extends Node {
    List< array_base_fom_with_index> array_base_fom_with_index;
    String opretor;

    public Array_base_form_with_operetor(){
        this.array_base_fom_with_index = new ArrayList<>();
    }
    public List<array_base_fom_with_index> getArray_base_fom() {
        return array_base_fom_with_index;
    }

    public void setArray_base_fom(List<array_base_fom_with_index> array_base_fom) {
        this.array_base_fom_with_index = array_base_fom;
    }

    public String getOpretor() {
        return opretor;
    }

    public void setOpretor(String opretor) {
        this.opretor = opretor;
    }
}
