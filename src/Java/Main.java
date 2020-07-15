package Java;

import Files_code_Json_csv.employess;
import Files_code_Json_csv.json_testing;
import Java.AST.FunctionDeclaration;
import Java.AST.Parse;
import Java.Base.BaseVisitor;
import Java.SymbolTable.*;
import generated.SQLLexer;
import generated.SQLParser;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.File;
import java.io.IOException;
import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main  {
    public static SymbolTable symbolTable = new SymbolTable();
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, URISyntaxException, NoSuchMethodException, InvocationTargetException { ;
        try {
            File file = new File("C://Users//Dell//IdeaProjects//LOLO//samples//samples.txt");
            String fileName = file.getAbsolutePath();
            CharStream cs = fromFileName(fileName);
            SQLLexer lexer = new SQLLexer(cs);
            CommonTokenStream token = new CommonTokenStream(lexer);
            SQLParser parser = new SQLParser(token);
            ParseTree tree = parser.parse();
            Parse p = (Parse) new BaseVisitor().visit(tree);
            p.accept(new Java.Visitor.BaseAst_Visitor());

            CodeGeneration.run( p);

        } catch (IOException e) {
            e.printStackTrace();
        }
            testing_json_file();



    }

    public static void showSymboleTable(){
        System.out.println("scopes stored :"+symbolTable.getScopes().size() );
        System.out.println("_______________");
        for (int i = 0; i <symbolTable.getScopes().size() ; i++) {
            System.out.println("scope: "+symbolTable.getScopes().get(i).getId());
            if(symbolTable.getScopes().get(i).getParent() != null){
                System.out.println("father: "+symbolTable.getScopes().get(i).getParent().getId());
            }
            else
            {
                System.out.println("father: "+symbolTable.getScopes().get(i).getParent());
            }
            for ( Object symbol :symbolTable.getScopes().get(i).getSymbolMap().values().toArray()) {
                System.out.println("-Symbol: "+ ((Symbol) symbol).getName());
                System.out.println("-Symbol Scope: "+ ((Symbol) symbol).getScope().getId());
                System.out.println("-Symbol type: "+ ((Symbol) symbol).getType().getName());


            }
            System.out.println("_______________");

            for ( Object table :symbolTable.getScopes().get(i).getTableMap().values().toArray())
            {
                System.out.println("table: "+ ((Table) table).getTable_name());
                System.out.println("the columns in this table : ");
                for (Object col:((Table) table).getColumnMap().values().toArray()
                     ) {
                    System.out.println(((Column) col).getColumn_name()
                            +"  the column type : "+
                            ((Column) col).getColumn_type().getName());
                    System.out.println("  ");
                }
                System.out.println("  ] ");
            }
        }
        System.out.println();
        for (Type type:symbolTable.getDeclaredTypes()
             ) {
            System.out.println("type : " + type.getName() );
            for (Object column: type.getColumns().values().toArray()
                 ) {
                Type c = (Type) column;
                System.out.println("column name : " + c.getName());
//                System.out.println("column type : " + ((Column)column).getColumn_type().getName());

            }
        }
    }
    public static  void testing_json_file(){
        employess ee = new employess();
        json_testing testing = new json_testing();
        String employ ="employess";
        testing.get_data_from_json(ee,employ);
    }

    }


