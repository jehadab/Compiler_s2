package Java;

import Files_code_Json_csv.json_testing;
import Java.AST.FunctionDeclaration;
import Java.AST.Parse;
import Java.AST.create.generalcreating;
//import Java.AST.visitor_java.visite_general_creating;
import Java.Base.BaseVisitor;
import Java.SymbolTable.*;
import generated.SQLBaseListener;
import generated.SQLLexer;
import generated.SQLParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    public static SymbolTable symbolTable = new SymbolTable();
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
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
            //json_testing t = new json_testing();
            //t.to_read_json_File();
        } catch (IOException e) {
            e.printStackTrace();
        }

        testCG();
    }



    int x =2;
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

    public static void testCG() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Type typ :symbolTable.getDeclaredTypes()) {
//           the type is table and have path and typeExtension
            if(typ.isTable()){
               String className  = returnTableName(typ);
               List<Column> columnList = returnTableColumn(typ);
               String classPath = returnTablePath(typ);
               String classExtension = returnTableExtension(typ);
               Class classType = createClassType(className,columnList,classPath,classExtension);
           }
////            the type is normal type
//           else{
//
//            }
        }


        //runIt(classType); to use class
//        Class<?> c = Class.forName("Java.AST.expr.Variable_Name");
//        c.newInstance();
//        System.out.println(c);
//        System.out.println();
    }
    public static Class<?> createClassType(String className ,
                                           List<Column> columnList,String tablePath
                                        ,String tableType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        className = "TestClass";
        String  stringTable =  (
                "a(name)  ::=<< package Java.SqlGenerated.TableClasses ;\n public class <name> {>>" +
                "b() ::=<< }>>" +
                        "");

        STGroup stGroup = new STGroupString(stringTable);
        ST st = stGroup.getInstanceOf("a");
        ST st2 = stGroup.getInstanceOf("b");
        st.add("name",className);

        try {//create class and write on it with ST
            FileWriter aWriter = new FileWriter("C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated//TableClasses//"+className+".java", false);
           aWriter.write(st.render());
           aWriter.write(st2.render());
            aWriter.flush();
            aWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Class c = Class.forName("Java.SqlGenerated.TableClasses."+className);
        return c;

    }

        public static void runIt(Class c) {
            try {
                Class params[] = {};
                Object paramsObj[] = {};
                Class thisClass = Class.forName("Java.SqlGenerated.TableClasses.testclass");
                Object iClass = thisClass.newInstance();
                Method thisMethod = thisClass.getDeclaredMethod("load", params);
                thisMethod.invoke(iClass, paramsObj);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static String returnTableName(Type typeclass){
        String result =  typeclass.getName();
        return  result;
    }

    public static List<Column> returnTableColumn(Type typeclass){
        List<Column> columnList = new ArrayList<>();
        for (Object col:typeclass.getColumnMap().values().toArray() ) {
            columnList.add((Column) col);
        }


        return columnList;
    }

    public static  String returnTablePath(Type typeclass){
        String result =  typeclass.getPath_of_table();
        return  result;
    }

    public static String returnTableExtension(Type typeclass){
        String result =  typeclass.getExtension_of_table();
        return  result;
    }


    }


