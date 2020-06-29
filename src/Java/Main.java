package Java;

import Java.AST.FunctionDeclaration;
import Java.AST.Parse;
import Java.AST.create.generalcreating;
//import Java.AST.visitor_java.visite_general_creating;
import Java.Base.BaseVisitor;
import Java.SymbolTable.Symbol;
import Java.SymbolTable.SymbolTable;
import generated.SQLBaseListener;
import generated.SQLLexer;
import generated.SQLParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import org.*;

//import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    public static SymbolTable symbolTable = new SymbolTable();
    public static void main(String[] args) {
        try {



            File file = new File("../../samples/samples.txt");
            String fileName = file.getAbsolutePath();

            CharStream cs = fromFileName(fileName);
            SQLLexer lexer = new SQLLexer(cs);
            CommonTokenStream token = new CommonTokenStream(lexer);
            SQLParser parser = new SQLParser(token);
            ParseTree tree = parser.parse();
            Parse p = (Parse) new BaseVisitor().visit(tree);
            p.accept(new Java.Visitor.BaseAst_Visitor());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void showSymboleTable(){
        for (int i = 0; i <Main.symbolTable.getScopes().size() ; i++) {
            System.out.println("scope: "+symbolTable.getScopes().get(i).getId());
            if(symbolTable.getScopes().get(i).getParent() != null){
                System.out.println("father: "+symbolTable.getScopes().get(i).getParent().getId());
            }
            else
            {
                System.out.println("father: "+symbolTable.getScopes().get(i).getParent());
            }
            for ( Object symbol :symbolTable.getScopes().get(i).getSymbolMap().values().toArray()) {
                System.out.println("Symbol: "+ ((Symbol) symbol).getName());
            }

        }
    }
}
