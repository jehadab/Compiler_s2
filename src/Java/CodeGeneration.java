package Java;

import Java.AST.FunctionDeclaration;
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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import java.util.Arrays;
import Java.AST.Parse;
import Java.AST.instruction.instructions;
import Java.AST.creating.createvariablewithassign;
import Java.AST.creating.gneralcreating;
import org.stringtemplate.v4.STGroupString;
import javax.tools.ToolProvider;
import org.stringtemplate.v4.STGroup;

/**
 * Created by Jehad on 7/13/2020.
 */
public class CodeGeneration {
    public static void run(Parse p ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, URISyntaxException, NoSuchMethodException, InvocationTargetException {
        for (Type typ :Main.symbolTable.getDeclaredTypes()) {
//           the type is table and have path and typeExtension
            String typeName = typ.getName();
            if(typeName.indexOf("_") != -1){
                typeName.split("_");
            }
            String className  = returnTableName(typ);
            ArrayList<Column> columnList = returnTableColumn(typ);
            String classPath = returnTablePath(typ);
            String classExtension = returnTableExtension(typ);
            createClassType(className,columnList,classPath,classExtension);
            compileClasses(className,"C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated//TableClasses//");
            loadClasses(className,"C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated//TableClasses//","Java.SqlGenerated.TableClasses");


//            if( == 0){
////                runIt(className);
//            }
        }
//        createMainClass(p.getFunctions());
//        compileClasses("SqlMain","C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated");
//        Class compiledClass= loadClasses("SqlMain","C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated","Java.SqlGenerated");
//        invokeMethod("SqlMain",compiledClass);
    }
    private static void createMainClass(List<FunctionDeclaration> functionDeclaration){
        String packagePath =  "Java.SqlGenerated";
        String className = "SqlMain";
        String typeName = ((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0))
                .getWithassign()
                .getVar_wiht_assign()
                .getVar()
                .getFactored()
                .getSelect_core()
                .getTableOrSubQueryList().get(0).getTableName().getName()+
        "_"+((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0))
                .getWithassign()
                .getVar_wiht_assign()
                .getVar()
                .getFactored()
                .getSelect_core()
                .getReslult_cloumnList()
                .get(0)
                .getExpr()
                .getColumnName()
                .getName();
        String varName =  ((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0)).getWithassign().getVar_wiht_assign().getVar().getVariable_with_opretor().get(0).getVariable_name();
        String stringTemplate = (
                "header(className,packagePath)::=<<package <packagePath> ;<\\n>import Java.SqlGenerated.TableClasses.s_id;<\\n>public class <className> { >>" +
                "mainFunction(functionCall)::= << <\\n><\\t>public  void Main(){ <functionCall.header.name>(); }<\\n> >>" +
                "addFunctions(functions ,varName , typeName)::=<< <functions:{ function|private void <function.header.name>(){<\\n>" +
                        "<typeName> <varName> = new <typeName>();<\\n>" +
                        "<varName>.load();<\\n> " +
                        "   }> >>" +
                        "bodyCodeSorce(typeName,varName) ::=<< <typeName> <varName> ;>>" +
                "EOF()::=<< <\\n> <\\t>}<\\n> }>>"
                );
        STGroup stGroup = new STGroupString(stringTemplate);

        ST header = stGroup.getInstanceOf("header");
        header.add("className" ,className);
        header.add("packagePath" ,packagePath);

        ST mainFunction = stGroup.getInstanceOf("mainFunction");
        mainFunction.add("functionCall",functionDeclaration.get(0));

        ST addFunctions = stGroup.getInstanceOf("addFunctions");
        addFunctions.add("functions", functionDeclaration);
        addFunctions.add("varName",varName);
        addFunctions.add("typeName",typeName);

        ST eof = stGroup.getInstanceOf("EOF");


        try {

            File file = new File("C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated//" + className + ".java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(header.render());
            fileWriter.write(mainFunction.render());
            fileWriter.write(addFunctions.render());
            fileWriter.write(eof.render());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private static void createClassType(String className ,
                                           List<Column> columnArrayList, String tablePath
            , String tableType) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {

        String packagePath = "Java.SqlGenerated.TableClasses";

        String  stringTemplate =  (
                "header(name,packagePath)  ::=<< package <packagePath>; <\\n>" +
                        "import java.util.List; <\\n>" +
                        "import com.google.gson.Gson; <\\n>" +
                        "import com.google.gson.JsonArray; <\\n>" +
                        "import com.google.gson.JsonElement; <\\n>" +
                        "import com.google.gson.JsonObject; <\\n>" +
                        "import com.google.gson.stream.JsonReader; <\\n>" +
                        "import java.io.FileNotFoundException; <\\n>" +
                        "import java.io.FileReader; <\\n>" +
                        " public class <name> {>>" +
                        "attribute(columns) ::=<<  <columns:{col |<\\n><\\t><col.column_type.name>    <col.column_name> ;}> >>" +
                        "tableAttribute(tablePath,tableType) ::=<< <if(tablePath)> <\\n><\\t>String tablePath = <tablePath>;<\\n><endif>" +
                        "<if(tableType)><\\t>String tableType = <tableType>;<endif> >>" +
                        "staticList(className,tablePath)::=<< <if(tablePath)><\\n><\\t>static List\\<<className>\\> entityObject  ;<endif><\\n> >>" +
                        "loadFunction()::= <<<\\t>public void load(){ System.out.println(\"hiiiii\");<\\n><\\t>}>>  " +
                        "EOF()::=<<<\\n> }>>");

        STGroup    stGroup = new STGroupString(stringTemplate);

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
        staticList.add("tablePath",tablePath);

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
            File classFile = new File("C://Users//Dell//IdeaProjects//LOLO//src//Java//SqlGenerated//TableClasses//"+className+".java");
            FileWriter fileWriter = new FileWriter(classFile  );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);



            bufferedWriter.write(header.render());
            bufferedWriter.write(attribute.render());
            bufferedWriter.write(tableAttribute.render());
            bufferedWriter.write(staticList.render());
            bufferedWriter.write(loadFunction.render());
            bufferedWriter.write(EOF.render());
            bufferedWriter.close();
            fileWriter.close();

//                Process p = Runtime.getRuntime().;
//                p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void  compileClasses(String className,String classPath) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        File sourceFile = new File(classPath+className+".java");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();


    }

    private static Class loadClasses(String className ,String classPath ,String relativePath) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        try {

            File sourceFile = new File(classPath+className+".java");
            File parentDirectory = sourceFile.getParentFile();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
            Class<?> compiledClass = classLoader.loadClass(relativePath+"."+className);
            return compiledClass;
        }
        catch (IOException io) {
            System.out.println(io);
        }
        return null;

    }
    private static void invokeMethod(String methodName,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Method method = cls.getDeclaredMethod("Main");
        method.invoke(cls.newInstance());
    }

    private static String returnTableName(Type typeclass){
        String result =  typeclass.getName();
        return  result;
    }

    private static ArrayList<Column> returnTableColumn(Type typeclass){
        ArrayList<Column> columnList = new ArrayList<>();
        for (Object col:typeclass.getColumns().keySet().toArray() ) {

            Column column = new Column();
            column.setColumn_name(col.toString());
            column.setColumn_type(typeclass.getColumns().get(col.toString()));
            columnList.add(column);
        }


        return columnList;
    }

    private static  String returnTablePath(Type typeclass){
        String result =  typeclass.getPath_of_table();
        return  result;
    }

    private static String returnTableExtension(Type typeclass){
        String result =  typeclass.getExtension_of_table();
        return  result;
    }


}





