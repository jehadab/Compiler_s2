package Java.AST.commn_classes_Sql.name_rule;


import Java.Main;
import Java.SymbolTable.Scope;
import Java.SymbolTable.Table;
import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/2/2020.
 */
public class TableName extends UseRandomName {
    private boolean validtablename = false ;
    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

}
