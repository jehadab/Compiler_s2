package Java.AST;

import Java.AST.QueryStmt.Statement;
import Java.SymbolTable.AggregationFunction;

import java.util.ArrayList;
import java.util.List;

public class Parse extends Node{
    private List<Statement> sqlStmts = new ArrayList<Statement>();
    private List<FunctionDeclaration> functions = new ArrayList<FunctionDeclaration>();
    private List<AggregationFunction> ag = new ArrayList<AggregationFunction>();

    public List<AggregationFunction> getAg() {
        return ag;
    }

    public void setAg(List<AggregationFunction> ag) {
        this.ag = ag;
    }

    public void addQuery(Statement query){

        this.sqlStmts.add(query);
    }

    public void setSqlStmts(List<Statement> sqlStmts) {
        this.sqlStmts = sqlStmts;
    }

    public void setFunctions(List<FunctionDeclaration> functions) {
        this.functions = functions;
    }

    public List<Statement> getSqlStmts() {
        return sqlStmts;
    }

    public List<FunctionDeclaration> getFunctions() {
        return functions;
    }

    @Override
    public String toString(){
        System.out.println("here we are ");
        return "sql stmts = "+ getSqlStmts().get(0).getName();

    }
}
