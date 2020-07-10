package Java.AST.QueryStmt.Drop;

import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.DataBaseName;
import Java.AST.commn_classes_Sql.name_rule.TableName;
import Java.Visitor.AstVistor;


/**
 * Created by moham on 12/31/2019.
 */
public class DropStmt extends Statement {
private DataBaseName dataBaseName;
private TableName tableName;
private boolean exist=false;


    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);

        if (dataBaseName!=null){
            this.dataBaseName.accept(astVisitor);
        }

        if (tableName!=null){
            this.tableName.accept(astVisitor);
        }

    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public DataBaseName getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(DataBaseName dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }
}
