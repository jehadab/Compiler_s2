package Java.AST.commn_classes_Sql.name_rule;

import Java.AST.Node;
import Java.AST.QueryStmt.Join.Join_Clause;
import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.QueryStmt.Statement;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/4/2020.
 */
public class TableOrSubQuery extends Statement {
DataBaseName dataBaseName;
TableName tableName;
Table_alias table_alias;
IndexName indexName;
List<TableOrSubQuery> LisTableOrSubQueries = new ArrayList<>();
Join_Clause join_clause;
select_stmt select_stmt;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(dataBaseName!=null){
            dataBaseName.accept(astVisitor);
        }
        if(tableName!=null){
            tableName.accept(astVisitor);
        }
        if(table_alias!=null){
            table_alias.accept(astVisitor);
        }
        if(join_clause!=null){
            join_clause.accept(astVisitor);
        }
        if(select_stmt!=null){
            select_stmt.accept(astVisitor);
        }
        for (int i = 0; i < LisTableOrSubQueries.size() ; i++) {
            LisTableOrSubQueries.get(i).accept(astVisitor);
        }
    }

    public Java.AST.QueryStmt.SelectStmt.select_stmt getSelect_stmt() {
        return select_stmt;
    }

    public void setSelect_stmt(Java.AST.QueryStmt.SelectStmt.select_stmt select_stmt) {
        this.select_stmt = select_stmt;
    }

    public Join_Clause getJoin_clause() {
        return join_clause;
    }

    public void setJoin_clause(Join_Clause join_clause) {
        this.join_clause = join_clause;
    }

    public TableOrSubQuery() {
        this.dataBaseName=null;
        this.table_alias=null;
        this.tableName=null;
        this.indexName=null;
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

    public Table_alias getTable_alias() {
        return table_alias;
    }

    public void setTable_alias(Table_alias table_alias) {
        this.table_alias = table_alias;
    }

    public IndexName getIndexName() {
        return indexName;
    }

    public void setIndexName(IndexName indexName) {
        this.indexName = indexName;
    }

    public List<TableOrSubQuery> getLisTableOrSubQueries() {
        return LisTableOrSubQueries;
    }

    public void setLisTableOrSubQueries(List<TableOrSubQuery> lisTableOrSubQueries) {
        LisTableOrSubQueries = lisTableOrSubQueries;
    }
}
