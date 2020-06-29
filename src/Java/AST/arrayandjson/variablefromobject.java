package Java.AST.arrayandjson;

import Java.AST.creating.gneralcreating;
import Java.AST.userandomname;

public class variablefromobject {
   String name ;
    jsonform form = new jsonform();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setForm(jsonform form) {
        this.form = form;
    }

    public jsonform getForm() {
        return form;
    }
}
