package Java;

import Files_code_Json_csv.employess;
import Java.SymbolTable.AggregationFunction;
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
import java.lang.reflect.Field;
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
    Parse parse ;
//        private volatile boolean compiled = false;
    public  void run(Parse p ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, URISyntaxException, NoSuchMethodException, InvocationTargetException {
        for (Type typ :Main.symbolTable.getDeclaredTypes()) {
//           the type is table and have path and typeExtension
            String typeName = typ.getName();
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
//
//                    createClassType(className,columnList,classPath,classExtension);
//                    compiled = compileClasses(className,"src/Java/SqlGenerated/TableClasses/");
//                }
//            }
            this.parse = p ;

            if(p.getFunctions().get(0) != null ){//make sure there is function
                if(p.getFunctions().get(0).getBody().getInstructions() != null){//make sure there is instructions
                    for (Object obj:p.getFunctions().get(0).getBody().getInstructions()
                            ) {
                        if(obj instanceof gneralcreating)//what is to cast
                        {
                            gneralcreating generalcreate =(gneralcreating) obj;
                            String className  = returnTableName(typ);
                            ArrayList<Column> columnList = returnTableColumn(typ);
                            String classPath = returnTablePath(typ);
                            String classExtension = returnTableExtension(typ);
                            createClassType(className,columnList,classPath,classExtension,generalcreate);
                        }
//                        if(obj instanceof Print){
//                            Print print = (Print) obj;
//                            for(Object obj2 : print.getPrints()){
//                                Inside_the_print inside_the_print = (Inside_the_print)obj2;
//                                if(inside_the_print.getExpression().getExpression_list().getIntral_expression_value().getVariable_name()!=null){
//
//
//                                    }
//                                }
//                            }
                        }
                    }
                }
                //compiled = compileClasses(className,"src/Java/SqlGenerated/TableClasses/");
                //loadClasses(className,"SqlGenerated/TableClasses/","Java.SqlGenerated.TableClasses");






//            if( == 0){
////                runIt(className);
//            }A
        }
            createMainClass(p.getFunctions());
            // compileClasses("SqlMain","src/Java/SqlGenerated/TableClasses/");
//            if(compiled){
//                Class compiledClass= loadClasses("SqlMain","src/Java/SqlGenerated/TableClasses/","src.Java.SqlGenerated.TableClasses");
//                invokeMethod("Main",compiledClass);
//            }
//        }


    }
    private  void createMainClass(List<FunctionDeclaration> functionDeclaration){

        String packagePath =  "Java";
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
        ArrayList<String> tablesCaller = new ArrayList<>();

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
        for (Type type :Main.symbolTable.getDeclaredTypes()
             ) {
            if(type.getName().contains("_") || type.getName().contains("$"))
                continue;
            else  {
                if(type.isTable())
                    tablesCaller.add(type.getName());
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
                "header(className,packagePath,imports,createTables)::=<<package <packagePath>;<addImports(imports,createTables)> <\\n> "+importJarLoader()+" public class <className> { >>" +
                "addImports(imports,createTables)::=<< <imports:{import |<\\n>import Java.SqlGenerated.TableClasses.<import.typeName>; }>" +
                        "<createTables:{  createTable| import Java.SqlGenerated.TableClasses.<createTable>; }> >>" +
                "mainFunction(functionCall,createTables)::= << <\\n><\\t>public static void main(String[] args) "+throwException()+" { <createTables:{createTable |<\\n> <\\t> <createTable> table<createTable> = new <createTable>(); <\\n><\\t> table<createTable>.load(); }> " +
                        "<functionCall.header.name>(); }<\\n> >>" +
                "addFunctions(functions , nameAndType)::=<< <functions:{ function|private static void <function.header.name>()"+throwException()+"{<\\n>" +
                        "<bodyCodeSorce(nameAndType)>" +
                        "   }> >>" +
                        "bodyCodeSorce(nameAndType ) ::=<< <nameAndType:{namesTypes | <namesTypes.typeName> <namesTypes.varName> = new <namesTypes.typeName>();<\\n>" +
                        "<namesTypes.varName>.load();<\\n> }> >>" +
                "EOF()::=<< <\\n> <\\t>}<\\n> }>>"
                );
        STGroup stGroup = new STGroupString(stringTemplate);

        ST header = stGroup.getInstanceOf("header");
        header.add("className" ,className);
        header.add("packagePath" ,packagePath);
        Set<String> settablesCaller = new HashSet<>(tablesCaller);
        header.add("createTables" ,settablesCaller);
        Set<NameAndType> set = new HashSet<>(nameAndTypes);
        header.add("imports" ,set);

        ST mainFunction = stGroup.getInstanceOf("mainFunction");
        mainFunction.add("functionCall",functionDeclaration.get(0));
        mainFunction.add("createTables",settablesCaller);


        ST addFunctions = stGroup.getInstanceOf("addFunctions");
        addFunctions.add("functions", functionDeclaration);
        addFunctions.add("nameAndType",nameAndTypes);

        ST eof = stGroup.getInstanceOf("EOF");


        try {

            File   file = new File("src/Java/" + className + ".java");
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
            , String tableType,gneralcreating gneralcreating) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {

        ArrayList<AggregationFunction> aggregationFunctionArrayList = new ArrayList<>();
        ArrayList<Column> columns = new ArrayList<>();
        for (Column col:columnArrayList
             ) {
//            System.err.println("sssssssssssssssssssssssssssss "+col.getColumn_name());
            if(col.getColumn_type() instanceof AggregationFunction)
            {
                aggregationFunctionArrayList.add((AggregationFunction) col.getColumn_type());
                System.err.println("fffffffffffffffffffffffffff  "+(((AggregationFunction) col.getColumn_type()).getAggregationFunctionName()));

                System.err.println("ggggggggggggggggggggggggg "+Main.symbolTable.getAgg().size());

            }
            else {
                if(col.getColumn_type().getName().equals(Type.NUMBER_CONST)){
                    col.setTypeNumber("true");
                }else if(col.getColumn_type().getName().equals(Type.STRING_CONST)){
                    col.setTypeString("true");
                }else if(col.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
                    col.setTypeboolean("true");
                }
                columns.add(col);
            }
        }

        String packagePath = "Java.SqlGenerated.TableClasses";
        String stringTemplate = StringForCreateCurrentType(gneralcreating);

        STGroup stGroup = new STGroupString(stringTemplate);
        ST header = stGroup.getInstanceOf("header");
        header.add("name",className);
        header.add("packagePath",packagePath);

        ST attribute = stGroup.getInstanceOf("attribute");
        attribute.add("columns",columns );

        ST aggFunc = stGroup.getInstanceOf("aggFunctions");
        aggFunc.add("aggList", aggregationFunctionArrayList);

        ST tableAttribute = stGroup.getInstanceOf("tableAttribute");
        tableAttribute.add("tablePath", tablePath);
        tableAttribute.add("tableType", tableType);

        ST staticList = stGroup.getInstanceOf("staticList");
        staticList.add("className", className);
        staticList.add("tablePath", tablePath);

        ST loadFunction = stGroup.getInstanceOf("loadFunction");
        loadFunction.add("aggList", aggregationFunctionArrayList);
        loadFunction.add("tablePath", tablePath);
        loadFunction.add("className", className);
        loadFunction.add("columns", columns);

        ST readJsonFile = stGroup.getInstanceOf("readJsonFile");
        readJsonFile.add("className", className);
        readJsonFile.add("columns", columns);
        readJsonFile.add("tablePath", tablePath);

        ST readCsvFile = stGroup.getInstanceOf("readCsvFile");
        readCsvFile.add("className", className);
        readCsvFile.add("tablePath", tablePath);
//        readCsvFile.add("columns",columns);
//        ST returnSpecificType = stGroup.getInstanceOf("returnSpecificType");
//
//        ST returnListOfColumn = stGroup.getInstanceOf("returnListOfColumn");

        ST setterAttribute = stGroup.getInstanceOf("setterAttribute");
        setterAttribute.add("columns", columns);
        setterAttribute.add("aggList", aggregationFunctionArrayList);

        ST getterAttribute = stGroup.getInstanceOf("getterAttribute");
        getterAttribute.add("columns", columns);
        getterAttribute.add("aggList", aggregationFunctionArrayList);


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
        Class cls = null;

        try {//create class and write on it with ST
            File classFile = new File("src/Java/SqlGenerated/TableClasses/"+className+".java");
            FileWriter fileWriter = new FileWriter(classFile  );
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(header.render());
            bufferedWriter.write(attribute.render());
            bufferedWriter.write(aggFunc.render());
            bufferedWriter.write(setterAttribute.render());
            bufferedWriter.write(getterAttribute.render());
            bufferedWriter.write(tableAttribute.render());
            bufferedWriter.write(staticList.render());
            bufferedWriter.write(loadFunction.render());
            bufferedWriter.write(readJsonFile.render());
            bufferedWriter.write(readCsvFile.render());
//            bufferedWriter.write(getTypeFunction.render());
//            bufferedWriter.write(returnSpecificType.render());
//            bufferedWriter.write(returnListOfColumn.render());
            bufferedWriter.write(EOF.render());
            bufferedWriter.close();
            fileWriter.close();

//                Process p = Runtime.getRuntime().;
//                p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean compileClasses(String className, String classPath) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        boolean compiled = false;
        File sourceFile = new File(classPath + className + ".java");
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

    private Class loadClasses(String className, String classPath, String relativePath) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        File f = new File("C:\\Users\\Jehad\\IdeaProjects\\Compailer_S2\\src\\Java\\SqlGenerated\\TableClasses\\Java\\SqlGenerated\\TableClasses\\" + className);
        File sourceFile = new File(classPath + className + ".java");
        File parentDirectory = f.getParentFile();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{sourceFile.toURI().toURL()});
//            ClassLoader.getSystemClassLoader().loadClass(relativePath+"."+className);
//            Class<?> compiledClass1 =ClassLoader.getSystemClassLoader().loadClass(className+className);
//            URLClassLoader classLoader =new URLClassLoader(new URL[] {parentDirectory.toURI().toURL() });
        Class<?> compiledClass = Class.forName("Java.SqlGenerated.TableClasses.SqlMain", true, urlClassLoader);
        compiledClass.newInstance();
        return compiledClass;
//            Class<?> compiledClass = Class.forName(relativePath+"."+className,true , classLoader);


    }

    private void invokeMethod(String methodName, Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Method method = cls.getDeclaredMethod(methodName);
        method.invoke(cls.newInstance());
    }

    private String returnTableName(Type typeclass) {
        String result = typeclass.getName();
        return result;
    }

    private ArrayList<Column> returnTableColumn(Type typeclass) {
        ArrayList<Column> columnList = new ArrayList<>();
        for (Object col : typeclass.getColumns().keySet().toArray()) {
            Column column = new Column();
            column.setColumn_name(col.toString());
            column.setColumn_type(typeclass.getColumns().get(col.toString()));
            columnList.add(column);
        }


        return columnList;
    }

    private String returnTablePath(Type typeclass) {
        String result = typeclass.getPath_of_table();
        return result;
    }

    private String returnTableExtension(Type typeclass) {
        String result = typeclass.getExtension_of_table();
        return result;
    }

    private Type getVariableType(String variableName, String scopeName) {
        Scope currentScope;
        for (Scope scope : Main.symbolTable.getScopes()
        ) {
            if (scope.getId().equals(scopeName)) {
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

    private String loadAggClassToSelect() {
        String stringTEmplate = (
                " <\\t><\\n>String JarPath = <agg.jar_path>;<\\n>" +
                        "<\\t>String JarName = \"<agg.AggregationFunctionName>.jar\";<\\n>" +
                        "<\\t>String ClassName = \"<agg.ClassName>\";<\\n>" +
                        "<\\t>String MethodName = \"<agg.MethodName>\";<\\n>" +
                        "<\\t>ArrayList\\<Double> myNumbers = new ArrayList\\<>(Arrays.asList(1.0, 2.0, 3.0, 12.0));<\\n>" +
                        "<\\t>URLClassLoader myClassLoader = new URLClassLoader(" +
                        "<\\t>new URL[]{new File(JarPath ).toURI().toURL()\\},Main.class.getClassLoader()); <\\n>" +
                        "<\\t>Class\\<?> myClass = Class.forName(ClassName, true, myClassLoader);<\\n>" +
                        "<\\t>Method mySingeltonGetterMethod = myClass.getMethod(\"get\" + ClassName,null);<\\n>" +
                        "<\\t>Object myObject = mySingeltonGetterMethod.invoke(null);<\\n>" +
                        "<\\t>Object myValue = myObject.getClass().getDeclaredMethod(MethodName, List.class).invoke(myObject, myNumbers);<\\n>" +
                        "<\\t>System.out.println(myValue);<\\n>"

        );


        return stringTEmplate;
    }

    public void where_function(Parse p) {
        // System.out.println(" we are here........................... ");
        String left_side = "";
        String righ_side = "";
        String operator = "";
        String select_value_we_have = "";
        ArrayList<employess> e = new ArrayList<employess>();
        for (int i = 0; i < 4; i++) {
            employess temp = new employess();
            temp.setId(i);
            temp.setName("testing  " + i);
            //temp.setAge(i);
            e.add(temp);
        }
        System.out.println(" the data we have ");
        for (int i = 0; i < 4; i++) {
            System.out.println(" the id will be" + e.get(i).getId());
            System.out.println(" the name will be" + e.get(i).getName());
        }
        // System.out.println(" the size of list after creatign it "+e.size());
        if (p.getFunctions().get(0).getBody().getInstructions() != null) {//make sure there is instructions
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
                                    select_value_we_have = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getReslult_cloumnList().get(0).getExpr().getColumnName().getName();
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
                                        if(generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getArray_list_od_right_side()!=null) {
                                            get_where_result_for_complixity_right_side(left_side,generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getArray_list_od_right_side(),operator,e,select_value_we_have);
                                        }
                                        if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight() != null) {
                                            if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getColumnName() != null) {
                                                righ_side = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getColumnName().getName();
                                                get_where_final_result(left_side, righ_side, operator, select_value_we_have, e);

                                            }
                                            if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getLiteral_value() != null) {
                                                righ_side = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getWhereExpr().getExpr().getRight().getLiteral_value().getReturnType().toString();
                                                get_where_final_result(left_side, righ_side, operator, select_value_we_have, e);

                                            }

                                        }
                                       // System.out.println("the right side here will be "+righ_side);

                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void get_where_final_result(String left_side, String right_side, String operator, String select_column, ArrayList<employess> datalist) {
        ArrayList<employess> temp_list = get_where_result(right_side, left_side, operator, datalist);
        ArrayList<String> result_list = new ArrayList<String>();
        System.out.println("the select column " + select_column);
        if (select_column.equals("name")) {
            if (temp_list.size() != 0)
                for (int i = 0; i < temp_list.size(); i++) {
                    result_list.add(temp_list.get(i).getName());
                }
            else System.out.println(" no result value we a have ");
        }
        for (int i = 0; i < result_list.size(); i++) {
            System.out.println(" the query result will be " + result_list.get(i));
        }
    }

    public ArrayList<employess> get_where_result(String right_side, String left_side, String operator, ArrayList<employess> datalist) {
        //know what the right real type
        //symbole map column name --> type
        //if type double // convert string ->double
        // if type boolean ///convert strign -> boolean
        // print_the_list(datalist);
       // System.out.println("the right side here will be "+right_side);
        ArrayList<employess> temp_list = new ArrayList<employess>();
        if (operator.equals("=")) {
            int temp_righ_value = Integer.valueOf(right_side);
            if (left_side.equals("id")) {
                for (int i = 0; i < datalist.size(); i++) {
                    // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                   // righ_side.contains()
                    //datalist.get(i) == "1";
                    if (datalist.get(i).id == temp_righ_value) {
                        //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        temp_list.add(datalist.get(i));

                    }

                }

            }

            return temp_list;
        }
        if (operator.equals(("!=")) || operator.equals("<>")) {
            int temp_righ_value = Integer.valueOf(right_side);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if (datalist.get(i).getId() != temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            return temp_list;
        }
        if (operator.equals("<")) {
            for (int i = 0; i < datalist.size(); i++) {
                int temp_righ_value = Integer.valueOf(right_side);
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if (datalist.get(i).getId() < temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            // System.out.println(" size of temp_list "+temp_list.size());
            return temp_list;
        }
        if (operator.equals(">")) {
            int temp_righ_value = Integer.valueOf(right_side);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if (datalist.get(i).getId() > temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            // System.out.println(" size of temp_list "+temp_list.size());
            return temp_list;
        }
        if (operator.equals("<=")) {
            int temp_righ_value = Integer.valueOf(right_side);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if (datalist.get(i).getId() <= temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            return temp_list;
        }
        if (operator.equals(">=")) {
            int temp_righ_value = Integer.valueOf(right_side);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if (datalist.get(i).getId() >= temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }

            }
            return temp_list;
        }
        if(operator.equals("like")||operator.equals("LIKE"))
        {
            int temp_righ_value = Integer.valueOf(right_side);
            if(!right_side.contains("%"))
            {
                if (left_side.equals("id")) {
                    for (int i = 0; i < datalist.size(); i++) {
                        // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        // righ_side.contains()
                        //datalist.get(i) == "1";
                        if (datalist.get(i).id == temp_righ_value) {
                            //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                            temp_list.add(datalist.get(i));

                        }

                    }

                }
        }
            else {
                System.out.println("the value here will be "+right_side.indexOf("%"));
            }
            return temp_list;
        }
        if(operator.equals("IN ") ||operator.equals("in"))
        {

        }

        return null;
    }
    public void  get_where_result_for_complixity_right_side(String left_side , ArrayList<String > right_side, String operator , ArrayList<employess> data_list,String  select_valu  ) {
        //if left side is number conver list from string to integer ......
        ArrayList<employess> temp_list = new ArrayList<employess>();
        for (int j = 0; j < right_side.size(); j++) {
            int value = Integer.valueOf(right_side.get(j));
            for (int i = 0; i < data_list.size(); i++) {
                if (left_side.equals("id")) {
                    if (data_list.get(i).getId()==value)
                    {
                      temp_list.add(data_list.get(i));
                    }
                }
            }
        }
        /*for(int i=0;i<temp_list.size();i++)
        {
            System.out.println("the value is---------------- ");
            System.out.println(temp_list.get(i).getId());
        }*/
        if(temp_list.size()==0)
        {
            System.out.println(" we have no result ");
        }
        else {
            ArrayList<String> result_list = new ArrayList<String>();
            if(select_valu.equals("name"))
            {
                for(int i=0;i<temp_list.size();i++)
                {
                    result_list.add(temp_list.get(i).getName());
                }
                for(int i=0;i<result_list.size();i++)
                {
                    System.out.println(" the value in the result list "+result_list.get(i));
                }
            }
        }

    }

    public void print_the_list(ArrayList<employess> t) {
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t.get(i).getId());
        }
    }

    private String importJarLoader() {
        String stringTEmplate = ("import java.util.List; <\\n>" +
                " import Java.Main;<\\n>" +
                "import java.io.BufferedReader; <\\n>" +
                "import java.io.*; <\\n>" +
                "import com.google.gson.Gson; <\\n>" +
                "import com.google.gson.JsonArray; <\\n>" +
                "import com.google.gson.JsonElement; <\\n>" +
                "import com.google.gson.JsonObject; <\\n>" +
                "import com.google.gson.stream.JsonReader; <\\n>" +
                "import java.io.FileNotFoundException; <\\n>" +
                "import java.io.FileReader; <\\n>" +
                "import java.io.File;\n" +
                "import java.lang.reflect.InvocationTargetException;\n" +
                "import java.lang.reflect.Method;\n" +
                "import java.net.MalformedURLException;\n" +
                "import java.net.URL;\n" +
                "import java.net.URLClassLoader;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n" +
                "import Java.SymbolTable.Column;<\\n>" +
                "import Java.SymbolTable.Type; <\\n>" +
                "import java.util.HashSet;<\\n>" +
                "import java.util.Set;<\\n>"
        );

        return stringTEmplate;
    }

    private String throwException() {
        String stringTemplate = ("throws ClassNotFoundException, NoSuchMethodException" +
                ", InvocationTargetException, IllegalAccessException, MalformedURLException");
        return stringTemplate;
    }

    public String StringForCreateCurrentType(gneralcreating gneralcreating) {
        String stringTemplate =
                (
                        "header(name,packagePath)  ::=<< package <packagePath>; <\\n>" +
                                importJarLoader() +
                                " public class <name> {>>" +
                                "attribute(columns) ::=<<  <columns:{col |<\\n><\\t>public <col.column_type.name>    <col.column_name> ;}> >>" +
                                "aggFunctions(aggList) ::=<< <aggList:{ aggFun |<\\n><\\t> <aggFun.returnType> $<aggFun.AggregationFunctionName> ; }>  >>" +
                                "tableAttribute(tablePath,tableType) ::=<< <if(tablePath)> <\\n><\\t>String tablePath = <tablePath>;<\\n><endif>" +
                                "<if(tableType)><\\t>String tableType = <tableType>;<endif> >>" +
                                "staticList(className,tablePath)::=<< <\\n><\\t>public static List\\<<className>\\> entityObject = new ArrayList\\<>() ;<\\n> >>" +
                                "loadFunction(aggList,tablePath,className,columns)::= <<<\\t>public void load() <if(aggList)>" + throwException() + "<endif> " +
                                "{ <\\n><\\t>" +
                                "<if(tablePath)>" +
                                "if(tableType == \"json\")<\\n><\\t>" +
                                "{<\\n><\\t>" +
                                "entityObject = readJsonFile();" +
                                "<\\n><\\t>" +
                                "}<\\n><\\t>" +
                                "else <\\n><\\t>" +
                                "{<\\n><\\t>" +
                                "}<\\n><\\t>" +
                                "<else>" +
                                "<loadContent(className,columns)>" +
                                "<endif>" +
                                "}<\\n> <if(aggList)> <loadAggFuncs(aggList)> <endif> >>  " +
                                "loadAggFuncs(aggList) ::= << " +
                                "<aggList :{ agg|<\\n><\\t> public static void <agg.AggregationFunctionName>() " + throwException() +
                                "{<\\n><\\t>" +
                                loadAggClassToSelect() +
                                "\\}<\\n><\\t>" +
                                "}> >>" +
                                readFileJsonFunction() +
                                setterAndGetterFunction() +
                                readFileCsvFunction() +
                                loadContent(gneralcreating) +
                                "EOF()::=<< <\\n> " +
                                "}>>"
                );

        return stringTemplate;
    }

    public String setterAndGetterFunction() {
        String str = "setterAttribute(columns,aggList) ::=<< " +
                "<columns:{col |<\\n><\\t> public void set<col.column_name>(<col.column_type.name> value){<\\n><\\t>" +
                "this.<col.column_name>  = value ; <\\n><\\t>" +
                "\\}" +
                " } > " +
                "<aggList:{ agg|<\\n><\\t> public void set$<agg.AggregationFunctionName> (<agg.returnType> value){<\\n><\\t>" +
                "this.$<agg.AggregationFunctionName> = value; <\\n><\\t> " +
                "\\} }> " +
                ">>" +


                "getterAttribute(columns,aggList) ::=<< " +
                "<columns:{col |<\\n><\\t> public <col.column_type.name> get<col.column_name>(){<\\n><\\t>" +
                "return <col.column_name> ;   <\\n><\\t>" +
                "\\}" +
                " } >" +
                "<aggList:{ agg|<\\n><\\t> public <agg.returnType> get$<agg.AggregationFunctionName>(){<\\n><\\t>" +
                "return this.$<agg.AggregationFunctionName>; <\\n><\\t>  \\}" +
                "}>" +
                " >>";


//            "setterAttribute(columns) ::=<< " +
//            "<if(columns)> "+
//            "<columns:{col |<\\n><\\t> public void set<col.>(<col> value){<\\n><\\t>" +
//            "this.<col>  = value ; <\\n><\\t>" +
//            "\\}" +
//            " }>" +
//            "<endif>"+
////            "<aggList:{ agg|<\\n><\\t> public void set<agg.AggregationFunctionName> (<agg.returnType> value){<\\n><\\t>" +
////            "this.<agg.AggregationFunctionName> = value;<\\n><\\t> \\} }> " +
//            ">>" +
//
//            "getterAttribute(columns) ::=<< " +
//            "<if(columns)>"+
//            "<columns:{col |<\\n><\\t> public <col> get<col>(){<\\n><\\t>" +
//            "return this.<col> ;   <\\n><\\t>" +
//            "\\}" +
//            " }> " +
//            "<endif>"+
//
////            "<aggList:{ agg|<\\n><\\t> public <agg.returnType> get<agg.AggregationFunctionName>(){<\\n><\\t>" +
////            "return this.<agg.AggregationFunctionName>; <\\n><\\t>  \\}" +
////            "}>"+
//            ">>";
        return str;
    }

    public String readFileCsvFunction() {
        String str = "readCsvFile(className,tablePath)::=<<" +
                "public List\\<<className>\\> readCsvFile() throws IOException" +
                "{<\\n><\\t>" +
                "<if(tablePath)>" +
                "List\\<<className>\\> result = new ArrayList\\<>(); <\\n><\\t>" +
                "BufferedReader csvReader = null;<\\n><\\t>" +
                "String[] data = new String[0];<\\n><\\t>" +
                "File csvFile = new File(<tablePath>);<\\n><\\t>" +
                "csvReader = new BufferedReader(new FileReader(csvFile));<\\n><\\t>" +
                "if(csvFile.isFile())<\\n><\\t>" +
                "{<\\n><\\t>" +
                " String row; <\\n><\\t>" +
                "while(((row = csvReader.readLine()) != null))<\\n><\\t>" +
                "{<\\n><\\t>" +
                "data = row.split(\",\");<\\n><\\t>" +
                "<className> dd = new <className>();<\\n><\\t>" +
                "for (int i = 0; i \\< data.length; i++)" +
                "{<\\n><\\t>" +
                "<className> ddd = new <className>();<\\n><\\t>" +
                "}<\\n><\\t>" +
                "}<\\n><\\t>" +
                "}<\\n><\\t>" +
                "return result;" +
                "<else>" +
                "return null;<\\n><\\t>" +
                "<endif>" +
                "}" +
                ">>";
        return str;
    }

    public String readFileJsonFunction() {
        String str = "readJsonFile(className,columns,tablePath)::=<<" +
                "<\\n><\\t> public List\\<<className>\\> readJsonFile(){<\\n><\\t>" +
                "<if(tablePath)>" +
                "List\\<<className>\\> result = new ArrayList\\<>();<\\n><\\t>" +
                "FileReader fr = null;" +
                "Gson json = new Gson();<\\n><\\t>" +
                "try {<\\n><\\t>" +
                "fr=new FileReader(tablePath);" +
                "<\\n><\\t>}" +
                "catch(FileNotFoundException e) {<\\n><\\t>" +
                "e.printStackTrace();}<\\n><\\t>" +
                "JsonReader reader = new JsonReader(fr);<\\n><\\t>" +
                "JsonObject testing = json.fromJson(fr, JsonObject.class);<\\n><\\t>" +
                "JsonElement json_ele = testing.get(\"<className>\");<\\n><\\t>" +
                "JsonArray j = json_ele.getAsJsonArray();<\\n><\\t>" +
//                    "Type type = returnSpecificType(\"<className>\");<\\n><\\t>" +
//                    "List\\<Column> columnList = new ArrayList\\<>();<\\n><\\t>" +
//                    "columnList = returnListOfColumn(type);<\\n><\\t>" +
                "for (int i = 0 ; i \\< j.size() ; i++ )" +
                " {<\\n><\\t>" +
                "<className> tableName = new <className>();<\\n><\\t>" +
                "<columns:{col | if(j.get(i).getAsJsonObject().get(\"<col.column_name>\") != null);<\\n><\\t>" +
                "{<\\n><\\t>" +
                //             case the colunmn all is double
                "<if(col.TypeNumber)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsDouble());<\\n><\\t>" +
                "<endif>" +
//                                 case the colunmn all is String
                "<if(col.TypeString)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsString());<\\n><\\t>" +
                "<endif>" +
                //             case the colunmn all is boolean
                "<if(col.Typeboolean)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsBoolean());<\\n><\\t>" +
                "<endif>" +
                "\\}<\\n><\\t>" +
                " }>" +
                "result.add(tableName);<\\n><\\t>" +
                "}<\\n><\\t>" +
                "return result;" +
                "<else>" +
                "return null;<\\n><\\t>" +
                "<endif>" +
                "<\\n><\\t> }>>";
        return str;
    }

    private String loadContent(gneralcreating generalcreate) {
        String tableName = "";
        class NameAndType {
            public String varName;
            public String typeName;
        }
        NameAndType nameAndTypes = new NameAndType();

        if (generalcreate.getWithassign() != null)//create with assign
        {
            if (generalcreate.getWithassign().getVar_wiht_assign().getVar() != null)//assign var
            {
                NameAndType nameAndTypeobj = new NameAndType();
                nameAndTypeobj.varName = generalcreate.getWithassign().getVar_wiht_assign().getVar().getVariable_with_opretor().get(0).getVariable_name();
                if (generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored() != null) {//is it factored select ?

                    Type type = getVariableType(nameAndTypeobj.varName, parse.getFunctions().get(0).getHeader().getName() + "_0");
                    nameAndTypeobj.typeName = type.getName();
                    nameAndTypes = nameAndTypeobj;
                    tableName = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getTableOrSubQueryList().get(0).getTableName().getName();
                }
            }
        }

//       Set<Field> fields = new HashSet<>(Arrays.asList(field)) ;
//       System.out.println("set "+((Field) fields.iterator().next()).getName());
//       ArrayList<String> s = new ArrayList<>();

        String loadContent = ("loadContent(className , columns)::=<< " +
                "<columns:{ col| " +
                "<\\t><\\n>for(int i = 0 ; i \\< " + tableName + ".entityObject.size(); i++){" +
                "<\\n><className> <className> = new <className>();" +
                "<\\n><className>.<col.column_name> = " + tableName + ".entityObject.get(i).<col.column_name>;" +
                "<\\n>System.out.println(<className>.<col.column_name>);" +
                "\\}" +
                "}>" +
//                "<\\n><\\t>Field tableField[] ="+tableName+".getClass().getFields();" +
//                "<\\n><\\t>Set<Field> fields = new HashSet<>(Arrays.asList(field)) ;" +
//                "<\\n><\\t>Field selectField[] =<className>.getClass().getFields();" +
//                "<\\n><\\t>ArrayList<String> matchField = new ArrayList();" +
//                "<\\n><\\t>for(int i = 0 ; i <= selectField.size() ;i++ ){" +
//                "<\\n><\\t><\\t>if(fields.contain(selectField[i]){ matchedField.add(selectField[i].getName()) }" +
//                "<\\n><\\t>}" +
//                "<\\n><\\t>for(int i = 0 ; i <= this.entityObject.size() ; i++ ){" +
//                "<\\n><\\t><\\t><className> <className>i = "+tableName+".entityObject.get(i);" +
//                "<\\n><\\t><\\t>for(int j = 0 ; j <= matchField.size() ; j++ ){" +
//                "<\\n><\\t><\\t><className>i = " +
//                "<\\n><\\t><\\t>}" +
//                "<\\n><\\t>}" +
//                "<\\n><\\t>System.out.println( field[0].getAnnotatedType().getType().getTypeName());" +
//                "<\\n><\\t>System.out.println(field[0].getName());" +
                " >>"

        );
        return loadContent;
    }

    public String jsonObjectPart() {
        String str = "if (j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray() != null) <\\n><\\t> " +
                "{<\\n><\\t>" +
                "JsonArray nested_one = j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray()<\\n><\\t>" +
                "set<col.column_name>(get_table(nested_one));<\\n><\\t>" +
                "\\}<\\n><\\t>";
        return str;
    }
}


//             case the colunmn all is Table or Type
//                    case the object is type

//                    "if (j.get(i).getAsJsonObject().get(\"<col.column_name>\").isJsonObject() == true)<\\n><\\t>" +
//                    "{<\\n><\\t>" +
//                    "<col.column_type.name> t = new <col.column_type.name>();<\\n><\\t>" +
//                   around this statment array of flat columns ..........................
//                    "t.setHome_number(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonObject().get(\"home_number\").getAsInt());<\\n><\\t>" +
//                    end array .......................................
//                    "set<col.column_name>(t)<\\n><\\t>"+
//                    "\\}<\\n><\\t>"+

//                case the object is table
//                    "if (j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray() != null)<\\n><\\t>"+
//                    "{<\\n><\\t>" +
//                    "JsonArray nested_one = j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray();<\\n><\\t>"+
//                    "<col.column_type.name>.setHome_number(get_table(nested_one));<\\n><\\t>"+
//                    "\\}<\\n><\\t>"+
//                    "\\}<\\n><\\t>" +


//                    "getTypeFunction()::= << "+
//                    "public <T> T get_types(JsonElement object){<\\n><\\t>" +
//                    " <col.column_type.name> p = new <col.column_type.name> (); <\\n><\\t>" +
//                    " <\\n><\\t>" +
//                    " <\\n><\\t>" +
//                    "return null;" +
//                    "} <\\n><\\t>" +
//                    ">>" +




// ---------------------------------------------
//   "returnSpecificType()::= << " +
//           "<\\n><\\t> public Type returnSpecificType(String typeName)<\\n><\\t>" +
//           "{<\\n><\\t>" +
//           "for(Type typ :Main.symbolTable.getDeclaredTypes())<\\n><\\t>" +
//           "{<\\n><\\t>" +
//           "if(typ.getName() == typeName)<\\n><\\t>" +
//           "{<\\n><\\t>" +
//           "return typ;<\\n><\\t>" +
//           "}<\\n><\\t>" +
//           "}<\\n><\\t>" +
//           "return null;<\\n><\\t>" +
//           "}>>" +
//
//           "returnListOfColumn()::= << " +
//           "<\\n><\\t>public List\\<Column>  returnListOfColumn(Type type)<\\n><\\t>" +
//           "{<\\n><\\t>" +
//           "List\\<Column> columnList = new ArrayList\\<>();<\\n><\\t>" +
//           "for(Object col:type.getColumns().keySet().toArray())<\\n><\\t>" +
//           "{<\\n><\\t>" +
//           "Column column = new Column();<\\n><\\t>" +
//           "column.setColumn_name(col.toString());<\\n><\\t>" +
//           "column.setColumn_type(type.getColumns().get(col.toString()));<\\n><\\t>" +
//           "columnList.add(column);<\\n><\\t>" +
//           "}<\\n><\\t>" +
//           "return columnList;<\\n><\\t>" +
//           "}>>"+
