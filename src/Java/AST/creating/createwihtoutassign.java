package Java.AST.creating;

public class createwihtoutassign  extends gneralcreating{
    creatingvariabelwithoutassing  var  ;
    createarrywithoutassign array ;
    create_json_wihtout_assign json ;
    public void setVar(creatingvariabelwithoutassing var) {
        this.var = var;
    }

    public void setArray(createarrywithoutassign array) {
        this.array = array;
    }

    public creatingvariabelwithoutassing getVar() {
        return var;
    }

    public createarrywithoutassign getArray() {
        return array;
    }

    public void setJson(create_json_wihtout_assign json) {
        this.json = json;
    }

    public create_json_wihtout_assign getJson() {
        return json;
    }

}
