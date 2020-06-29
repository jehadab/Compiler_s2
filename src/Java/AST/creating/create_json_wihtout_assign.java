package Java.AST.creating;

import Java.AST.arrayandjson.jsonform;

public class create_json_wihtout_assign extends createwihtoutassign {
    jsonform form = new jsonform();

    public void setForm(jsonform form) {
        this.form = form;
    }

    public jsonform getForm() {
        return form;
    }
}
