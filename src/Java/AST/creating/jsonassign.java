package Java.AST.creating;

import Java.AST.arrayandjson.jsonform;
import Java.AST.arrayandjson.jsonstatment;

public class jsonassign extends creat_json_with_assign {
    jsonstatment json = new jsonstatment(); // i can go to json form
    jsonform form = new jsonform();

    public void setJson(jsonstatment json) {
        this.json = json;
    }

    public jsonstatment getJson() {
        return json;
    }

    public void setForm(jsonform form) {
        this.form = form;
    }

    public jsonform getForm() {
        return form;
    }
}
