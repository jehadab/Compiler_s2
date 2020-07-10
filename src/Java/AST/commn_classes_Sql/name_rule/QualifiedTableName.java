package Java.AST.commn_classes_Sql.name_rule;

import Java.Visitor.AstVistor;

/**
 * Created by moham on 1/3/2020.
 */
public class QualifiedTableName extends UseRandomName {
    DataBaseName dataBaseName;
    TableName tableName;
    IndexName indexName;

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

    public IndexName getIndexName() {
        return indexName;
    }

    public void setIndexName(IndexName indexName) {
        this.indexName = indexName;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
    if(dataBaseName!=null){
        this.dataBaseName.accept(astVisitor);
    }
    }
}
