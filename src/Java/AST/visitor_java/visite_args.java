package Java.AST.visitor_java;

import Java.AST.ParametesandArgs.args;
import Java.AST.create.creating_var_without_assign;
import Java.AST.creating.createwihtoutassign;
import Java.AST.creating.creatingvariabelwithoutassing;
import Java.AST.creating.gneralcreating;
import generated.SQLBaseVisitor;
import generated.SQLParser;
import generated.SQLVisitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import java.util.ArrayList;
import java.util.List;

public class visite_args extends SQLBaseVisitor {
    @Override public List<args> visitArgs(SQLParser.ArgsContext ctx) {
        List<args> arg = new ArrayList<args>();
        args temp = new  args ();
        System.out.println("visite args ");
       // arg.add(visitGrnral_creating(ctx.grnral_creating(0)));
        for(int i=0;i<arg.size();i++)
        {
           /// arg.add(visitGrnral_creating(ctx.grnral_creating()));

           // temp.setCreating(visitGrnral_creating(ctx.grnral_creating()));

            arg.add(temp);
        }
        return arg;
    }

    @Override public gneralcreating visitGrnral_creating(SQLParser.Grnral_creatingContext ctx) {
        System.out.println("general creating ");
        gneralcreating general = new gneralcreating();
        general.setWihtoutassig(visitCreat_without_assign(ctx.creat_without_assign()));
        return general;
    }
    @Override public createwihtoutassign visitCreat_without_assign(SQLParser.Creat_without_assignContext ctx) {
        createwihtoutassign wihtoutassign = new createwihtoutassign();
       // wihtoutassign =  visitCreate_varible_without_assign(ctx.create_varible_without_assign());
        wihtoutassign.setVar( visitCreate_varible_without_assign(ctx.create_varible_without_assign()));

    return wihtoutassign;

    }
    @Override public creatingvariabelwithoutassing visitCreate_varible_without_assign(SQLParser.Create_varible_without_assignContext ctx) {
        System.out.println("creatingvariabelwithoutassing");
        creatingvariabelwithoutassing creatvaribelwihtout = new creatingvariabelwithoutassing();
        creatvaribelwihtout.setN(visitUse_random_name(ctx.varible_name().use_random_name()));
        return  creatvaribelwihtout;

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
