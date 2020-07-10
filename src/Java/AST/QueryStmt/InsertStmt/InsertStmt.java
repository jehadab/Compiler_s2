package Java.AST.QueryStmt.InsertStmt;

import Java.AST.QueryStmt.SelectStmt.select_stmt;
import Java.AST.QueryStmt.Statement;

import Java.AST.commn_classes_Sql.name_rule.ColumnName;
import Java.AST.commn_classes_Sql.name_rule.DataBaseName;
import Java.AST.commn_classes_Sql.name_rule.TableName;
import Java.AST.expr.Expr;
import Java.Visitor.AstVistor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 1/3/2020.
 */
public class InsertStmt extends Statement {
    DataBaseName dataBaseName ;
    TableName tableName ;
   List<ColumnName> listofcolumnName = new ArrayList<>();
   boolean defalut_value = false;
   List<Expr> listofexpr = new ArrayList<>();
   select_stmt sleSelect_stmt ;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if (dataBaseName!=null){
            dataBaseName.accept(astVisitor);
        }
        if(tableName!=null){
            tableName.accept(astVisitor);
        }
        for (int i = 0; i <listofcolumnName.size() ; i++) {
            listofcolumnName.get(i).accept(astVisitor);
        }
        for (int i = 0; i <listofexpr.size() ; i++) {
            listofexpr.get(i).accept(astVisitor);
        }
        if(this.sleSelect_stmt!=null){
            this.getSleSelect_stmt().accept(astVisitor);
        }
    }

    public select_stmt getSleSelect_stmt() {
        return sleSelect_stmt;
    }

    public void setSleSelect_stmt(select_stmt sleSelect_stmt) {
        this.sleSelect_stmt = sleSelect_stmt;
    }

    public void addItemToListOfColumnName(ColumnName c)
    {
        listofcolumnName.add(c);
    }

    public void addItemToListOfExpr(Expr c){
        listofexpr.add(c);
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

    public List<ColumnName> getListofcolumnName() {
        return listofcolumnName;
    }

    public void setListofcolumnName(List<ColumnName> listofcolumnName) {
        this.listofcolumnName = listofcolumnName;
    }

    public boolean isDefalut_value() {
        return defalut_value;
    }

    public void setDefalut_value(boolean defalut_value) {
        this.defalut_value = defalut_value;
    }

    public List<Expr> getListofexpr() {
        return listofexpr;
    }

    public void setListofexpr(List<Expr> listofexpr) {
        this.listofexpr = listofexpr;
    }
}
