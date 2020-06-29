/*
*
*
* package Java.AST.visitor_java;

import Java.AST.ParametesandArgs.args;
import Java.AST.create.createvariableform;
import Java.AST.create.creating_var_without_assign;
import Java.AST.create.generalcreating;
import Java.AST.userandomname;
import generated.SQLBaseVisitor;
import generated.SQLParser;

///public class visite_general_creating extends SQLBaseVisitor {

    @Override
   // public generalcreating visitGrnral_creating(SQLParser.Grnral_creatingContext ctx) {

        generalcreating general = new generalcreating();
        System.out.println("visitGrnral_creating");
        if(ctx.create_varible_form()!=null) {
            general = visitCreate_varible_form(ctx.create_varible_form());
        }
        if(ctx.create_array_form()!=null){
            // todo visitArraform
        }

        if(ctx.create_json_form()!=null){
            // todo visitjesonform
        }
        return general;
    }
    @Override public createvariableform visitCreate_varible_form(SQLParser.Create_varible_formContext ctx) {
        System.out.println("createvariableform");
        //visitCreate_varible_without_assign withoutassgn = new visitCreate_varible_without_assign();
        createvariableform creat = new createvariableform();
         creat =visitCreate_varible_without_assign(ctx.create_varible_without_assign());
return creat;
    }
    @Override public creating_var_without_assign visitCreate_varible_without_assign(SQLParser.Create_varible_without_assignContext ctx)
    {
        System.out.println("creating_var_without_assign ");
        creating_var_without_assign var = new creating_var_without_assign();
       var.setCreating_var(visitUse_random_name(ctx.varible_name().use_random_name()));
      return   var;
    }
        @Override
        public String  visitUse_random_name(SQLParser.Use_random_nameContext ctx) {
            System.out.println("visitUse_random_name");
            String name ="";
            if(ctx.RANDOM_NAME()!= null)
            {
               name = ctx.RANDOM_NAME().getSymbol().getText();

            }
            return name ;
        }

}

* */