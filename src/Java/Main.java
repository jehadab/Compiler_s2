package Java;

import Java.AST.Parse;
import Java.Base.BaseVisitor;
import Java.SymbolTable.*;

import generated.SQLLexer;
import generated.SQLParser;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main  {
    public static SymbolTable symbolTable = new SymbolTable();
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {
        try {
            File file = new File("..//..//samples//samples.txt");
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

        testCG();


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

    public static void testCG() throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {


        Class classType = createClassType("className",new ArrayList<Column>(),"tablePath","tableType");

        //runIt(classType); to use class



//        Class<?> c = Class.forName("Java.AST.expr.Variable_Name");
//        c.newInstance();
//        System.out.println(c);
//        System.out.println();
    }
    public static Class<?> createClassType(String className ,
                                       ArrayList<Column> columnArrayList,String tablePath
                                        ,String tableType) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {
        className = "TestClass1";
        tablePath = "E:";
        tableType = "user";
        String packagePath = "Java.SqlGenerated.TableClasses";
        Column column = new Column();
        Column column1 = new Column();
        Type type = new Type();
        type.setName("string");
        column.setColumn_name("name");
        column.setColumn_type(type);

        type.setName("double");
        column1.setColumn_name("name1");
        column1.setColumn_type(type);
        columnArrayList.add(column);
        columnArrayList.add(column1);
        String  stringTemplate =  (
                "header(name,packagePath)  ::=<< package <packagePath> ;<\\n> import java.util.List;\n \n public class <name> {>>" +
                "attribute(columns) ::=<<  <columns:{col |<\\n><\\t><col.column_type.name>    <col.column_name> ;}> >>" +
                "tableAttribute(tablePath,tableType) ::=<<<\\n><\\t>String tablePath = \"<tablePath>\";<\\n>" +
                "<\\t>String tableType = \"<tableType>\"; >> " +
                "staticList(className)::=<<<\\n> static List\\<<className>\\> entityObject  ;<\\n> >>" +
                "loadFunction()::= << public void load(){ System.out.println(\"hiiiii\");  " +
                        "<\\n> } >>" +
                "EOF()::=<<<\\n> }>>");


        STGroup stGroup = new STGroupString(stringTemplate);

        ST header = stGroup.getInstanceOf("header");
        header.add("name",className);
        header.add("packagePath",packagePath);

        ST attribute = stGroup.getInstanceOf("attribute");
        attribute.add("columns", columnArrayList);

        ST tableAttribute = stGroup.getInstanceOf("tableAttribute");
        tableAttribute.add("tablePath",tablePath);
        tableAttribute.add("tableType",tableType);

        ST staticList = stGroup.getInstanceOf("staticList");
        staticList.add("className",className);

        ST loadFunction = stGroup.getInstanceOf("loadFunction");

        ST EOF = stGroup.getInstanceOf("EOF");


//        ArrayList<String> s  = new ArrayList<>() ;
//        ArrayList<String> c  = new ArrayList<>() ;
//        for (Column col:columnArrayList
//                ) {
//            s.add(col.getColumn_name());
//            c.add(col.getColumn_type().getName());
//        }

//        attribute.add("columnType",columnArrayList);
//        attribute.add("columnName",columnArrayList);
        Class cls =null;

        try {//create class and write on it with ST
            File classFile = new File("SqlGenerated/TableClasses/"+className+".java");
            FileWriter fileWriter = new FileWriter(classFile ,false );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



                bufferedWriter.write(header.render());
                bufferedWriter.write(attribute.render());
                bufferedWriter.write(tableAttribute.render());
                bufferedWriter.write(staticList.render());
                bufferedWriter.write(loadFunction.render());
                bufferedWriter.write(EOF.render());
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.close();



//                Process p = Runtime.getRuntime().;
//                p.waitFor();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, "Java.SqlGenerated.TableClasses" + "." + "TestClass1");


            cls = Class.forName("Java.SqlGenerated.TableClasses." + "TestClass1");



             return cls;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return cls;

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
    }


