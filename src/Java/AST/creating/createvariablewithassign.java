package Java.AST.creating;

public class createvariablewithassign extends gneralcreating {
    creat_json_with_assign json_wiht_assign ;
    create_variable_withassign var_wiht_assign ;
    create_array_wiht_assign array_with_assign ;
    public void setJson_wiht_assign(creat_json_with_assign json_wiht_assign) {
        this.json_wiht_assign = json_wiht_assign;
    }

    public creat_json_with_assign getJson_wiht_assign() {
        return json_wiht_assign;
    }

    public createvariablewithassign getVar_wiht_assign() {
        return var_wiht_assign;
    }

    public void setVar_wiht_assign( create_variable_withassign var_wiht_assign) {
        this.var_wiht_assign = var_wiht_assign;
    }

    public create_array_wiht_assign getArray_with_assign() {
        return array_with_assign;
    }

    public void setArray_with_assign(create_array_wiht_assign array_with_assign) {
        this.array_with_assign = array_with_assign;
    }
}
