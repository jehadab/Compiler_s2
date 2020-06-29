package Java.AST.function;

import Java.AST.FunctionDeclaration;
import Java.AST.ParametesandArgs.args;
//import Java.AST.creating.create_with_assign;
import Java.AST.creating.createvariablewithassign;
import Java.AST.userandomname;

import java.util.ArrayList;
import java.util.List;

public class function_header extends FunctionDeclaration {
    // String functionname ;
    //function_body body = new function_body();
    //userandomname use = new userandomname();
    private List<args> arg = new ArrayList<args>();
    List<createvariablewithassign> with = new ArrayList<>();
    String name ;
    // list of parameteress


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArg(List<args> arg) {
        this.arg = arg;
    }

    public List<args> getArg() {
        return arg;
    }
    public void  pri (){
        for (int i=0;i<arg.size();i++){
            // System.out.println(arg.get(i).getCreating().getWihtoutassig().getVar().getN());
            if(arg.get(i).getCreating().getWihtoutassig().getArray()!=null)
                System.out.println("array variable  without assign "+arg.get(i).getCreating().getWihtoutassig().getArray().getWihtoutindex().getName());
            else if (arg.get(i).getCreating().getWihtoutassig().getVar().getN()!=null)
                System.out.println("variable without assign  "+arg.get(i).getCreating().getWihtoutassig().getVar().getN());
            else if (arg.get(i).getCreating().getWihtoutassig().getJson().getForm().toString()!=null)
                System.out.println("json without assign  "+arg.get(i).getCreating().getWihtoutassig().getVar().getN());
        }
        for (int j=0;j<with.size();j++)
        {
           if(with.get(j).getVar_wiht_assign()!=null)
           {
               System.out.println("variable with assign "+with.get(j).getVar_wiht_assign());
           }
           if(with.get(j).getArray_with_assign()!=null)
           {

           }
        }

    }
    public void add_item_to_arglist(args a ){
        this.arg.add(a);

    }
    public void add_item_to_with_assign_var_list(createvariablewithassign w ){
        this.with.add(w);

    }

}
