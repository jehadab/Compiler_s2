package Java.AST.create;

import Java.AST.userandomname;
import generated.SQLParser;

public class creating_var_without_assign  extends createvariableform {
   private userandomname use = new userandomname();

    public void setUse(userandomname use) {
        this.use = use;
    }

    public userandomname getUse() {
        return use;
    }


}
