package Java;

import Java.SymbolTable.Column;
import Java.SymbolTable.Type;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jehad on 7/13/2020.
 */
public class CodeGeneration {
    public static void run() throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {
        for (Type typ :Main.symbolTable.getDeclaredTypes()) {
//           the type is table and have path and typeExtension
            if(typ.isTable()){

                String className  = returnTableName(typ);
                ArrayList<Column> columnList = returnTableColumn(typ);
                String classPath = returnTablePath(typ);
                String classExtension = returnTableExtension(typ);
                Class classType = createClassType(className,columnList,classPath,classExtension);
            }
        }
    }
    public static Class<?> createClassType(String className ,
                                           List<Column> columnArrayList, String tablePath
            , String tableType) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {

        String packagePath = "Java.SqlGenerated.TableClasses";

        String  stringTemplate =  (
                "header(name,packagePath)  ::=<< package <packagePath> ;<\\n> import java.util.List;\n \n public class <name> {>>" +
                        "attribute(columns) ::=<<  <columns:{col |<\\n><\\t><col.column_type.name>    <col.column_name> ;}> >>" +
                        "tableAttribute(tablePath,tableType) ::=<<<\\n><\\t>String tablePath = <tablePath>;<\\n>" +
                        "<\\t>String tableType = <tableType>; >> " +
                        "staticList(className)::=<<<\\n> static List\\<<className>\\> entityObject  ;<\\n> >>" +
                        "loadFunction()::= << public void load(){ System.out.println(\"hiiiii\");  " +
                        "<\\n> } >>" +
                        "EOF()::=<<<\\n> }>>");


        STGroup stGroup = new STGroupString(stringTemplate);

        ST header = stGroup.getInstanceOf("header");
        header.add("name",className);
        header.add("packagePath",packagePath);

        ST attribute = stGroup.getInstanceOf("attribute");
        attribute.add("columns",columnArrayList );

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
            compiler.run(null, null, null, "Java.SqlGenerated.TableClasses" + "." + className);
            cls = Class.forName("Java.SqlGenerated.TableClasses." + className);



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

    public static String returnTableName(Type typeclass){
        String result =  typeclass.getName();
        return  result;
    }

    public static ArrayList<Column> returnTableColumn(Type typeclass){
        ArrayList<Column> columnList = new ArrayList<>();
        for (Object col:typeclass.getColumns().keySet().toArray() ) {

            Column column = new Column();
            column.setColumn_name(col.toString());
            column.setColumn_type(typeclass.getColumns().get(col.toString()));
            columnList.add(column);
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





