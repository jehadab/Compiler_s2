package Java.AST.Visitor;

import Java.AST.FunctionDeclaration;
import Java.AST.Parse;
import Java.AST.QueryStmt.Statement;

public interface ASTVisitor {
    public void visit(Parse p);
    public void visit(FunctionDeclaration funcDec);
    public void visit(Statement stmt);
}
