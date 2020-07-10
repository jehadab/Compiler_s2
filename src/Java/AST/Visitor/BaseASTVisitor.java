package Java.AST.Visitor;

import Java.AST.FunctionDeclaration;
import Java.AST.Parse;
import Java.AST.QueryStmt.Statement;

public class BaseASTVisitor implements ASTVisitor {
    @Override
    public void visit(Parse p) {
        System.out.println("ast parse ");
    }

    @Override
    public void visit(FunctionDeclaration funcDec) {
        System.out.println("ast FunctionDeclaration ");

    }

    @Override
    public void visit(Statement stmt) {
        System.out.println("ast Statement ");

    }
}
