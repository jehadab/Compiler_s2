package Java.AST.commn_classes_Sql.name_rule;

import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/4/2020.
 */
public class Table_alias extends UseRandomName {
    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }
}
