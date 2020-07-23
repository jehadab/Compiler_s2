package Java.AST.expr;

import Java.AST.Node;
import Java.AST.commn_classes_Sql.name_rule.ColumnName;
import Java.AST.commn_classes_Sql.name_rule.DataBaseName;
import Java.AST.commn_classes_Sql.name_rule.TableName;
import Java.Visitor.AstVistor;

import java.util.ArrayList;

/**
 * Created by moham on 1/2/2020.
 */
public class Expr extends Node {
private boolean isVisited = false ;
private Expr left ;
private Expr right;
private Literal_Value literal_value;
private DataBaseName dataBaseName;
private TableName tableName;
private ColumnName columnName;
private  String  op;

private ArrayList<Expr> Array_list_od_right_side = new ArrayList<>();

    public ArrayList<Expr> getArray_list_od_right_side() {
        return Array_list_od_right_side;
    }

    public void setArray_list_od_right_side(ArrayList<Expr> array_list_od_right_side) {
        Array_list_od_right_side = array_list_od_right_side;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getFunction_name() {
        return Function_name;
    }

    public void setFunction_name(String function_name) {
        Function_name = function_name;
    }

    private  String Function_name ;

    @Override
    public void accept(AstVistor astVisitor) {
        astVisitor.visit(this);
        if(left!=null){
            left.accept(astVisitor);
        }
        if(right!=null){
            right.accept(astVisitor);
        }
        if (literal_value!=null){
            literal_value.accept(astVisitor);
        }
        if (dataBaseName!=null){
            dataBaseName.accept(astVisitor);
        }
        if(tableName!=null){
            tableName.accept(astVisitor);
        }

    }

    public Expr() {
        this.isVisited = false;
        this.left = null;
        this.right = null;
        this.literal_value = null;
        this.dataBaseName = null;
        this.tableName = null;
        this.columnName =  null;

    }

    public boolean isVisited() {
        return isVisited;
    }

    public Expr getLeft() {
        return left;
    }

    public void setLeft(Expr left) {
        this.left = left;
    }

    public Expr getRight() {
        return right;
    }

    public void setRight(Expr right) {
        this.right = right;
    }

    public Literal_Value getLiteral_value() {
        return literal_value;
    }

    public void setLiteral_value(Literal_Value literal_value) {
        this.literal_value = literal_value;
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

    public ColumnName getColumnName() {
        return columnName;
    }

    public void setColumnName(ColumnName columnName) {
        this.columnName = columnName;
    }


    public void setVisited(boolean visited) {
        isVisited = visited;
    }
    public void add_to_list(Expr ex)
    {
       this.Array_list_od_right_side.add(ex);
    }
}

