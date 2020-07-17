package Java;

import Files_code_Json_csv.employess;
import Java.SymbolTable.Scope;
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
import java.lang.ClassLoader;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import java.util.Arrays;
import Java.AST.Parse;
import Java.AST.creating.gneralcreating;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystem;





/**
 * Created by Jehad on 7/13/2020.
 */

public class CodeGeneration {

        private volatile boolean compiled = false;
    public  void run(Parse p ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, URISyntaxException, NoSuchMethodException, InvocationTargetException {
        for (Type typ :Main.symbolTable.getDeclaredTypes()) {
//           the type is table and have path and typeExtension
//            String typeName = typ.getName();
//            if(typeName.contains("_")){
//                if(typeName.contains("+")){
//
//                }else
//                {
//                    String spilts[]= typeName.split("_");
//                    Type tableName = new Type();
//                    typ.setName(spilts[0]);
//                    spilts = Arrays.copyOfRange(spilts,1, spilts.length);
//                    String className  = returnTableName(typ);
//
//                    for (String split:spilts
//                         ) {
//                    }
//                    ArrayList<Column> columnList = returnTableColumn(typ);
//                    String classPath = returnTablePath(typ);
//                    String classExtension = returnTableExtension(typ);
//                }
//            }

                String className  = returnTableName(typ);
                ArrayList<Column> columnList = returnTableColumn(typ);
                String classPath = returnTablePath(typ);
                String classExtension = returnTableExtension(typ);
                createClassType(className,columnList,classPath,classExtension);
                compiled = compileClasses(className,"src/Java/SqlGenerated/TableClasses/");
                //loadClasses(className,"SqlGenerated/TableClasses/","Java.SqlGenerated.TableClasses");



//            if( == 0){
////                runIt(className);
//            }A
        }
        if(compiled){
            compiled= false;
            createMainClass(p.getFunctions());
            compiled = compileClasses("SqlMain","src/Java/SqlGenerated/TableClasses/");
            if(compiled){
                Class compiledClass= loadClasses("SqlMain","src/Java/SqlGenerated/TableClasses/","src.Java.SqlGenerated.TableClasses");
                invokeMethod("Main",compiledClass);
            }
        }


    }
    private  void createMainClass(List<FunctionDeclaration> functionDeclaration){

        String packagePath =  "Java.SqlGenerated.TableClasses";
        String className = "SqlMain";
//        System.out.println(functionDeclaration.get(0).getHeader().getName().hashCode());
//        String typeName = ((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0))
//                .getWithassign()
//                .getVar_wiht_assign()
//                .getVar()
//                .getFactored()
//                .getSelect_core()
//                .getTableOrSubQueryList().get(0).getTableName().getName()+
//        "_"+((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0))
//                .getWithassign()
//                .getVar_wiht_assign()
//                .getVar()
//                .getFactored()
//                .getSelect_core()
//                .getReslult_cloumnList()
//                .get(0)
//                .getExpr()
//                .getColumnName()
//                .getName();

        class NameAndType {
            public String varName;
            public String typeName ;
        }
        ArrayList<NameAndType> nameAndTypes = new ArrayList<>();

        if(functionDeclaration.get(0) != null ){//make sure there is function
            if(functionDeclaration.get(0).getBody().getInstructions() != null){//make sure there is instructions
                for (Object obj:functionDeclaration.get(0).getBody().getInstructions()
                     ) {
                    if(obj instanceof gneralcreating)//what is to cast
                    {
                        gneralcreating generalcreate =(gneralcreating) obj;
                        if(generalcreate.getWithassign() !=null)//create with assign
                        {
                            if(generalcreate.getWithassign().getVar_wiht_assign().getVar() != null)//assign var
                            {
                                NameAndType nameAndTypeobj = new NameAndType();
                                nameAndTypeobj.varName = generalcreate.getWithassign().getVar_wiht_assign().getVar().getVariable_with_opretor().get(0).getVariable_name();
                                if(generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored()!= null){//is it factored select ?
                                    Type type = getVariableType(nameAndTypeobj.varName,functionDeclaration.get(0).getHeader().getName()+"_0");
                                    nameAndTypeobj.typeName =  type.getName();
                                    nameAndTypes.add(nameAndTypeobj);
                                }
                            }
                        }
                    }
                }

            }
        }




//           ((gneralcreating)functionDeclaration.get(0).getBody().getInstructions().get(0))
//                .getWithassign()
//                .getVar_wiht_assign()
//                .getVar()
//                .getVariable_with_opretor()
//                .get(0)
//                .getVariable_name();


        String stringTemplate = (
                "header(className,packagePath,imports)::=<<package <packagePath>;<addImports(imports)> <\\n> public class <className> { >>" +
                "addImports(imports)::=<< <imports:{import |<\\n>import Java.SqlGenerated.TableClasses.<import.typeName>; }> >>" +
                "mainFunction(functionCall)::= << <\\n><\\t>public  void Main(){ <functionCall.header.name>(); }<\\n> >>" +
                "addFunctions(functions , nameAndType)::=<< <functions:{ function|private void <function.header.name>(){<\\n>" +
                        "<bodyCodeSorce(nameAndType)>" +
                        "   }> >>" +
                        "bodyCodeSorce(nameAndType) ::=<< <nameAndType:{namesTypes | <namesTypes.typeName> <namesTypes.varName> = new <namesTypes.typeName>();<\\n>" +
                        "<namesTypes.varName>.load();<\\n> }> >>" +
                "EOF()::=<< <\\n> <\\t>}<\\n> }>>"
                );
        STGroup stGroup = new STGroupString(stringTemplate);

        ST header = stGroup.getInstanceOf("header");
        header.add("className" ,className);
        header.add("packagePath" ,packagePath);
        Set<NameAndType> set = new HashSet<>(nameAndTypes);
        header.add("imports" ,set);

        ST mainFunction = stGroup.getInstanceOf("mainFunction");
        mainFunction.add("functionCall",functionDeclaration.get(0));


        ST addFunctions = stGroup.getInstanceOf("addFunctions");
        addFunctions.add("functions", functionDeclaration);
        addFunctions.add("nameAndType",nameAndTypes);

        ST eof = stGroup.getInstanceOf("EOF");


        try {

            File   file = new File("src/Java/SqlGenerated/TableClasses/" + className + ".java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(header.render());
            fileWriter.write(mainFunction.render());
            fileWriter.write(addFunctions.render());
            fileWriter.write(eof.render());
            fileWriter.flush();
            fileWriter.close();

//            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//            Class c = classLoader.loadClass(relativePath+"."+className);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private  void createClassType(String className ,
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
            File classFile = new File("src/Java/SqlGenerated/TableClasses/"+className+".java");
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

    private  boolean  compileClasses(String className,String classPath) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        boolean compiled = false;
        File sourceFile = new File(classPath+className+".java");
        String destenation = "";
        //File out = new File("out/production/Sql_Compiler/Java/SqlGenerated/TableClasses");
//        System.out.println(out.getAbsolutePath());



//        System.out.println(path.getParent().getParent());
//        System.out.println(StandardLocation.CLASS_OUTPUT.getName());

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//        System.out.println(StandardLocation.CLASS_OUTPUT);
//        compiler.run(null,null,null,sourceFile.getPath());
        File parentDirectory = sourceFile.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();
        compiled = true;
        return compiled;
    }

    private Class loadClasses(String className ,String classPath ,String relativePath) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

            File f = new File("C://Users//Dell//Desktop//Sql_compiler//src//Java//SqlGenerated//TableClasses"+className);
            File sourceFile = new File(classPath+className+".java");
            File parentDirectory = f.getParentFile();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{sourceFile.toURI().toURL()});
//            ClassLoader.getSystemClassLoader().loadClass(relativePath+"."+className);
//            Class<?> compiledClass1 =ClassLoader.getSystemClassLoader().loadClass(className+className);
//            URLClassLoader classLoader =new URLClassLoader(new URL[] {parentDirectory.toURI().toURL() });
            Class<?> compiledClass = Class.forName("Java.SqlGenerated.TableClasses.SqlMain",true,urlClassLoader);
            compiledClass.newInstance();
            return compiledClass;
//            Class<?> compiledClass = Class.forName(relativePath+"."+className,true , classLoader);



    }
    private  void invokeMethod(String methodName,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Method method = cls.getDeclaredMethod(methodName);
        method.invoke(cls.newInstance());
    }

    private  String returnTableName(Type typeclass){
        String result =  typeclass.getName();
        return  result;
    }

    private  ArrayList<Column> returnTableColumn(Type typeclass){
        ArrayList<Column> columnList = new ArrayList<>();
        for (Object col:typeclass.getColumns().keySet().toArray() ) {

            Column column = new Column();
            column.setColumn_name(col.toString());
            column.setColumn_type(typeclass.getColumns().get(col.toString()));
            columnList.add(column);
        }


        return columnList;
    }

    private   String returnTablePath(Type typeclass){
        String result =  typeclass.getPath_of_table();
        return  result;
    }

    private  String returnTableExtension(Type typeclass){
        String result =  typeclass.getExtension_of_table();
        return  result;
    }
    private  Type getVariableType(String variableName,String scopeName) {
        Scope currentScope ;
        for (Scope scope:Main.symbolTable.getScopes()
             ) {
            if(scope.getId().equals(scopeName))
            {
                currentScope = scope;
                Type variableType = new Type();
                variableType.setName("**type conflict**");
                while (currentScope.getParent() != null) {
                    if (currentScope.getSymbolMap().containsKey(variableName)) {
                        return currentScope.getSymbolMap().get(variableName).getType();
                    } else {
                        currentScope = currentScope.getParent();
                    }
                }
                return variableType;

            }

        }
        return null;

    }
public void where_function (Parse p){
   // System.out.println(" we are here........................... ");
        String left_side="";
        String righ_side="";
        String operator ="";
        String select_value_we_have="";
    ArrayList<employess> e =  new ArrayList<employess>();
    for(int i=0;i<4;i++)
    {
        employess temp = new employess();
        temp.setId(i);
        temp.setName("testing  "+i);
        //temp.setAge(i);
        e.add(temp);
    }
    System.out.println(" the data we have ");
    for(int i=0;i<4;i++)
    {
        System.out.println(" the id will be"+e.get(i).getId());
        System.out.println(" the name will be"+e.get(i).getName());
    }
   // System.out.println(" the size of list after creatign it "+e.size());
    if(p.getFunctions().get(0).getBody().getInstructions() != null) {//make sure there is instructions
        for (Object obj : p.getFunctions().get(0).getBody().getInstructions()
        ) {
            if (obj instanceof gneralcreating)//what is to cast
            {
                gneralcreating generalcreate = (gneralcreating) obj;
                if (generalcreate.getWithassign() != null)//create with assign
                {
                    if (generalcreate.getWithassign().getVar_wiht_assign().getVar() != null)//assign var
                    {

                        if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored() != null) {//is it factored select ?
                            if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core() != null) {
                                 select_value_we_have=generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getReslult_cloumnList().get(0).getExpr().getColumnName().getName();
                                if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr() != null) {
                                    if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getLeft() != null) {
                                        if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getLeft().getColumnName() != null) {
                                            left_side = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getLeft().getColumnName().getName();
                                          //  System.out.println(" the left side will -----"+left_side);
                                        }
                                    }
                                    if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getOp() != null) {
                                        operator = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getOp();
                                        //System.out.println("the operatore is ---"+operator);
                                    }
                                    if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight() != null) {
                                        if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getColumnName() != null) {
                                            righ_side = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getColumnName().getName();

                                        }
                                        if(generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getLiteral_value()!=null){
                                            righ_side=generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getLiteral_value().getReturnType().toString();

                                        }
                                    }

                                    get_where_final_result(left_side,righ_side,operator,select_value_we_have,e);
                                }

                            }
                        }
                    }
                }
            }
        }
    }
    }
    public void get_where_final_result (String left_side ,  String right_side, String operator,String select_column, ArrayList<employess> datalist){
        ArrayList<employess> temp_list = get_where_result(right_side,left_side,operator,datalist);
        ArrayList<String> result_list= new  ArrayList<String>();
        System.out.println("the select column "+select_column);
        if(select_column.equals("name"))
        {
            if(temp_list.size()!=0)
            for(int i=0;i<temp_list.size();i++)
            {
                result_list.add(temp_list.get(i).getName());
            }
            else System.out.println(" no result value we a have ");
        }
       for(int i=0;i<result_list.size();i++)
       {
           System.out.println(" the query result will be "+result_list.get(i));
       }
    }
    public ArrayList<employess> get_where_result( String right_side, String left_side, String operator,ArrayList<employess> datalist){
        //know what the right real type
        //symbole map column name --> type
        //if type double // convert string ->double
        // if type boolean ///convert strign -> boolean
        // print_the_list(datalist);
        int temp_righ_value=Integer.valueOf(right_side);
        ArrayList<employess> temp_list = new ArrayList<employess>();
     if(operator.equals("="))
     {

      if(left_side.equals("id"))
      {
          for(int i=0;i<datalist.size();i++)
          {
             // System.out.println("int he arraylist we have "+datalist.get(i).getId());
            if(datalist.get(i).getId()==temp_righ_value)
            {
                //System.out.println("int he arraylist we have "+datalist.get(i).getId());
              temp_list.add(datalist.get(i));

            }

          }

      }

return temp_list;
     }
     if(operator.equals("!="))
     {
         for(int i=0;i<datalist.size();i++)
         {
             // System.out.println("int he arraylist we have "+datalist.get(i).getId());
             if(datalist.get(i).getId()!=temp_righ_value)
             {
                 //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                 temp_list.add(datalist.get(i));

             }

         }
         return temp_list;
     }
     if(operator.equals("<"))
     {
         for(int i=0;i<datalist.size();i++)
         {
             // System.out.println("int he arraylist we have "+datalist.get(i).getId());
             if(datalist.get(i).getId()<temp_righ_value)
             {
                 //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                 temp_list.add(datalist.get(i));

             }

         }
        // System.out.println(" size of temp_list "+temp_list.size());
         return temp_list;
     }
        if(operator.equals(">"))
        {

            for(int i=0;i<datalist.size();i++)
            {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(datalist.get(i).getId()>temp_righ_value)
                {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
           // System.out.println(" size of temp_list "+temp_list.size());
            return temp_list;
        }
        if(operator.equals("<="))
        {
            for(int i=0;i<datalist.size();i++)
            {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(datalist.get(i).getId()<=temp_righ_value)
                {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            return temp_list;
        }
        if(operator.equals(">="))
        {
            for(int i=0;i<datalist.size();i++)
            {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(datalist.get(i).getId()>=temp_righ_value)
                {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            return temp_list;
        }

return null;
    }
    public void print_the_list(ArrayList<employess> t){
        for(int i=0;i<t.size();i++)
        {
            System.out.println(t.get(i).getId());
        }
    }
}











