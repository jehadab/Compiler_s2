package Java.AST.commn_classes_Sql.name_rule;

import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/2/2020.
 */
public class DataBaseName extends UseRandomName {

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    }

}
