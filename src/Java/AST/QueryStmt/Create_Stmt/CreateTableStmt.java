package Java.AST.QueryStmt.Create_Stmt;

import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.QueryStmt.Statement;
import Java.AST.commn_classes_Sql.name_rule.DataBaseName;
import Java.AST.commn_classes_Sql.name_rule.TableName;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 12/31/2019.
 */
public class CreateTableStmt extends Statement {
 private DataBaseName dataBaseName ;
 private TableName tableName;
 private List<TableConstraint> tableConstraints = new ArrayList<>();
 private List<ColumnDef> columnDefs = new ArrayList<>();
 private select_stmt select_stmt ;
 private DeclareTypeTable declareTypeTable;
 private DeclarePathTable declarePathTable;

    public DeclareTypeTable getDeclareTypeTable() {
        return declareTypeTable;
    }

    public void setDeclareTypeTable(DeclareTypeTable declareTypeTable) {
        this.declareTypeTable = declareTypeTable;
    }

    public DeclarePathTable getDeclarePathTable() {
        return declarePathTable;
    }

    public void setDeclarePathTable(DeclarePathTable declarePathTable) {
        this.declarePathTable = declarePathTable;
    }

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if (dataBaseName!=null){
            dataBaseName.accept(astVisitor);
        }
        if(tableName!=null){
            tableName.accept(astVisitor);
        }
        if(select_stmt!=null){
            select_stmt.accept(astVisitor);
        }
        for (int i = 0; i <tableConstraints.size() ; i++) {
            tableConstraints.get(i).accept(astVisitor);
        }
        for (int i = 0; i <columnDefs.size() ; i++) {
            columnDefs.get(i).accept(astVisitor);
        }
    }

    public Java.AST.QueryStmt.SelectStmt.select_stmt getSelect_stmt() {
        return select_stmt;
    }

    public void setSelect_stmt(Java.AST.QueryStmt.SelectStmt.select_stmt select_stmt) {
        this.select_stmt = select_stmt;
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

    public List<TableConstraint> getTableConstraints() {
        return tableConstraints;
    }

    public void setTableConstraints(List<TableConstraint> tableConstraints) {
        this.tableConstraints = tableConstraints;
    }

    public List<ColumnDef> getColumnDefs() {
        return columnDefs;
    }

    public void setColumnDefs(List<ColumnDef> columnDefs) {
        this.columnDefs = columnDefs;
    }
}
