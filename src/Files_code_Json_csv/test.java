package Files_code_Json_csv;
import Java.Main;
import Java.SymbolTable.AggregationFunction;
import Java.SymbolTable.Scope;
import Java.SymbolTable.*;
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
import Java.AST.creating.gneralcreating;

import java.util.HashSet;
import java.util.Set;

class CodeGenerationCopy {
    Parse parse ;
    //        private volatile boolean compiled = false;
    public  void run(Parse p ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, URISyntaxException, NoSuchMethodException, InvocationTargetException {
        for (Type typ : Main.symbolTable.getDeclaredTypes()) {
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
//                            int sizeColumnsSizeTable = returnSizeOfColumnsFlatTable(columnList);
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
            if(type.getName().contains("_") || type.getName().contains("_AGG"))
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

    public int returnSizeOfColumnsFlatTable(ArrayList<Column> columnArrayList){
        ArrayList<Column> columns = new ArrayList<>();
        columns = columnArrayList;
        int result =0;
        for (Column col:columnArrayList){
            if(col.getColumn_type().getName().equals(Type.NUMBER_CONST)){
                col.setTypeNumber("true");
                columns.add(col);
                result++;
            }else if(col.getColumn_type().getName().equals(Type.STRING_CONST)){
                col.setTypeString("true");
                columns.add(col);
                result++;
            }else if(col.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
                col.setTypeboolean("true");
                columns.add(col);
                result++;
            }
            else if(col.getTypeNumber()==null&&col.getTypeboolean()==null&&col.getTypeString()==null){
                col.setTypeObject("true");
                Type  nestypeclass = returnSpecificType(col.getColumn_type().getName());
                columns.add(col);
                ArrayList<Column> columnArrayList1  = returnTableColumn(nestypeclass);
                for(Column nescol : columnArrayList1)
                {
                    columns.add(nescol);
                    result++;
                }
            }
        }
        System.out.println("result is : ***********************************"+result);
        return result;
    }

    private  void createClassType(String className ,
                                  List<Column> columnArrayList, String tablePath
            , String tableType,gneralcreating generalcreating ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {

        ArrayList<AggregationFunction> aggregationFunctionArrayList = new ArrayList<>();
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Table> tables = new ArrayList<>();
        for (Column col:columnArrayList)
        {
//            System.err.println("sssssssssssssssssssssssssssss "+col.getColumn_name());
            if(col.getColumn_type() instanceof AggregationFunction)
            {
                aggregationFunctionArrayList.add((AggregationFunction) col.getColumn_type());
//                System.err.println("fffffffffffffffffffffffffff  "+(((AggregationFunction) col.getColumn_type()).getAggregationFunctionName()));

//                System.err.println("ggggggggggggggggggggggggg "+Main.symbolTable.getAgg().size());

            }
            else {

                if(col.getColumn_type().getName().equals(Type.NUMBER_CONST)){
                    col.setTypeNumber("true");
                    columns.add(col);
                }else if(col.getColumn_type().getName().equals(Type.STRING_CONST)){
                    col.setTypeString("true");
                    columns.add(col);
                }else if(col.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
                    col.setTypeboolean("true");
                    columns.add(col);
                }
                else if(col.getTypeNumber()==null&&col.getTypeboolean()==null&&col.getTypeString()==null){
                    col.setTypeObject("true");
                    Type  nestypeclass = returnSpecificType(col.getColumn_type().getName());
                    columns.add(col);
                    ArrayList<Column> columnArrayList1  = returnTableColumn(nestypeclass);
                    for(Column nescol : columnArrayList1)
                    {
                        nescol.setParentTable(col.getColumn_name());
                        if(nescol.getColumn_type().getName().equals(Type.NUMBER_CONST)){
                            nescol.setTypeNumber("true");
                        }else if(nescol.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
                            nescol.setTypeboolean("true");
                        }
                        else if(nescol.getColumn_type().getName().equals(Type.STRING_CONST)){
                            nescol.setTypeString("true");
                        }
                        columns.add(nescol);
                    }
                }

            }
        }

        System.out.println("***************************************************************************************");
        for(Column coll:columns){
            System.out.println(coll.getColumn_name()+"            "+coll.getParentTable()+"            "+coll.getColumn_type().getName()+"            "+coll.getTypeString()+"            "+coll.getTypeboolean()+"            "+coll.getTypeNumber());
        }
        System.out.println("***************************************************************************************");


        String packagePath = "Java.SqlGenerated.TableClasses";
        String stringTemplate = StringForCreateCurrentType(generalcreating);

        STGroup stGroup = new STGroupString(stringTemplate);
        ST header = stGroup.getInstanceOf("header");
        header.add("name",className);
        header.add("packagePath",packagePath);

        ST attribute = stGroup.getInstanceOf("attribute");
        attribute.add("columns",columns );

        ST aggFunc = stGroup.getInstanceOf("aggFunctions");
        aggFunc.add("aggList",aggregationFunctionArrayList);

        ST tableAttribute = stGroup.getInstanceOf("tableAttribute");
        tableAttribute.add("tablePath",tablePath);
        tableAttribute.add("tableType",tableType);

        ST staticList = stGroup.getInstanceOf("staticList");
        staticList.add("className",className);
        staticList.add("tablePath",tablePath);

        ST loadFunction = stGroup.getInstanceOf("loadFunction");
        loadFunction.add("aggList",aggregationFunctionArrayList);
        loadFunction.add("tablePath",tablePath);
        loadFunction.add("className",className);
        ArrayList<TableAndColumn> tableAndColumnArrayList = splitColomNames(columns);
        loadFunction.add("columns",tableAndColumnArrayList);
//        loadFunction.add("tables",splitColomNames(columns));



        ST readJsonFile = stGroup.getInstanceOf("readJsonFile");
        readJsonFile.add("className",className);
        readJsonFile.add("columns",columns);
        readJsonFile.add("tablePath",tablePath);

        ST readCsvFile =stGroup.getInstanceOf("readCsvFile");
        readCsvFile.add("className",className);
        readCsvFile.add("tablePath",tablePath);
        readCsvFile.add("columns",columns);
//        ST returnSpecificType = stGroup.getInstanceOf("returnSpecificType");
//
//        ST returnListOfColumn = stGroup.getInstanceOf("returnListOfColumn");

        ST setterAttribute = stGroup.getInstanceOf("setterAttribute");
        setterAttribute.add("columns",columns);
        setterAttribute.add("aggList",aggregationFunctionArrayList);

        ST getterAttribute = stGroup.getInstanceOf("getterAttribute");
        getterAttribute.add("columns",columns);
        getterAttribute.add("aggList",aggregationFunctionArrayList);

        ST printContent = stGroup.getInstanceOf("printContent");
        printContent.add("className",className);
        printContent.add("columns",columns);

        ST getTypeFunction = stGroup.getInstanceOf("getTypeFunction");
        getTypeFunction.add("columns",columns);
//        ST getTableFunction = stGroup.getInstanceOf("getTableFunction");
//        getTableFunction.add("columns",columns);
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
            bufferedWriter.write(printContent.render());
            bufferedWriter.write(getTypeFunction.render());
//            bufferedWriter.write(getTableFunction.render());
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

        File f = new File("C:\\Users\\Jehad\\IdeaProjects\\Compailer_S2\\src\\Java\\SqlGenerated\\TableClasses\\Java\\SqlGenerated\\TableClasses\\"+className);
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
        int l =  typeclass.getColumns().keySet().toArray().length;
        for (Object col:typeclass.getColumns().keySet().toArray() ) {
            Column column = new Column();
            column.setColumn_name(col.toString());
            column.setColumn_type(typeclass.getColumns().get(col.toString()));
            if(l == 1){
                column.setLastColumn("true");
            }
            if(l==typeclass.getColumns().keySet().toArray().length){
                column.setFirstColumn("true");
            }
            columnList.add(column);
            l--;
        }
//        System.out.println(typeclass.getName()+"  (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
//        System.out.println("the columnList size is : "+ columnList.size());
//        for(Column column:columnList){
//            System.out.println(column.getColumn_name());
//        }
//        System.out.println(typeclass.getName()+"  (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
        return columnList;
    }

//    public ArrayList<Column> returnTableFlatColumn(Type typeclass){
//        ArrayList<Column> columnList = new ArrayList<>();
//        int l =  typeclass.getColumns().keySet().toArray().length;
//        for (Object col:typeclass.getColumns().keySet().toArray() ) {
//            Column column = new Column();
//            column.setColumn_name(col.toString());
//            column.setColumn_type(typeclass.getColumns().get(col.toString()));
//            if(l == 1){
//                column.setLastColumn("true");
//            }
//            if(l==typeclass.getColumns().keySet().toArray().length){
//                column.setFirstColumn("true");
//            }
//            if(column.getColumn_type().getName().equals(Type.NUMBER_CONST)){
//                column.setTypeNumber("true");
//                columnList.add(column);
//            }else if(column.getColumn_type().getName().equals(Type.STRING_CONST)){
//                column.setTypeString("true");
//                columnList.add(column);
//            }else if(column.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
//                column.setTypeboolean("true");
//                columnList.add(column);
//            }
//            else if (column.getTypeNumber()==null&&column.getTypeboolean()==null&&column.getTypeString()==null){
//                column.setTypeObject("true");
//                Type  nestypeclass = returnSpecificType(column.getColumn_name());
//                for(Object nescol : nestypeclass.getColumns().keySet().toArray())
//                {
//                    Column column2 = new Column();
//                    column2.setColumn_name(nescol.toString());
//                    column2.setColumn_type(nestypeclass.getColumns().get(nescol.toString()));
//                    if(column2.getColumn_type().getName().equals(Type.NUMBER_CONST)){
//                        column2.setTypeNumber("true");
//                    }else if(column2.getColumn_type().getName().equals(Type.STRING_CONST)){
//                        column2.setTypeString("true");
//                    }else if(column2.getColumn_type().getName().equals(Type.BOOLEAN_CONST)){
//                        column2.setTypeboolean("true");
//                    }
//                    column2.setParentTable(column2.getColumn_name());
//                    columnList.add(column2);
//                }
//                columnList.add(column);
//            }
//
//            l--;
//        }
//        return null;
//    }

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
    private String loadAggClassToSelect(){
        String stringTEmplate =(
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
    private String importJarLoader()
    {
        String stringTEmplate = (
                "<\\n>import java.util.List;<\\n>" +
                        "import org.apache.commons.csv.CSVFormat;<\\n>" +
                        "import org.apache.commons.csv.CSVParser;<\\n>" +
                        "import org.apache.commons.csv.CSVRecord;<\\n>" +
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
    private String throwException(){
        String stringTemplate = ("throws ClassNotFoundException, NoSuchMethodException" +
                ", InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException");
        return stringTemplate;
    }

    public  String StringForCreateCurrentType(gneralcreating gneralcreating){
        String  stringTemplate =
                (
                        "header(name,packagePath)  ::=<< package <packagePath>; <\\n>" +
                                importJarLoader()+
                                " public class <name> implements Cloneable {>>" +
                                "attribute(columns) ::=<<  <columns:{col |<\\n><\\t>public <col.column_type.name>    <col.column_name> ;}> >>" +
                                "aggFunctions(aggList) ::=<< <aggList:{ aggFun |<\\n><\\t> <aggFun.returnType> _AGG<aggFun.AggregationFunctionName> ; }>  >>" +
                                "tableAttribute(tablePath,tableType) ::=<< <if(tablePath)> <\\n><\\t>String tablePath = <tablePath>;<\\n><endif>" +
                                "<if(tableType)><\\t>String tableType = <tableType>;<endif> >>" +
                                "staticList(className,tablePath)::=<< <\\n><\\t>static List\\<<className>\\> entityObject  = new ArrayList\\<>();<\\n> >>" +
                                "loadFunction(aggList,tablePath,isType,className,columns)::= <<<\\t>public void load() " +throwException()+
                                "{ <\\n><\\t>" +
                                "<if(tablePath)>" +
                                "if(tableType == \"json\")<\\n><\\t>" +
                                "{<\\n><\\t>" +
                                "entityObject = readJsonFile();" +
                                "<\\n><\\t>" +
                                "}<\\n><\\t>" +
                                "else <\\n><\\t>" +
                                "{<\\n><\\t>" +
                                "entityObject = readCsvFile();<\\n><\\t>" +
                                "}<\\n><\\t>" +
                                "<else>" +
                                "<loadContent(className,columns)>" +
                                "<endif>" +
                                "}<\\n> <if(aggList)> <loadAggFuncs(aggList)> <endif> >>  " +
                                "loadAggFuncs(aggList) ::= << " +
                                "<aggList :{ agg|<\\n><\\t> public static void <agg.AggregationFunctionName>() "+throwException()+
                                "{<\\n><\\t>"+
                                loadAggClassToSelect()+
                                "<\\n><\\t> \\}" +
                                "}> >>" +
                                readFileJsonFunction()+
                                setterAndGetterFunction()+
//                        "<if(!tablePath)>"+
                                printContentFunction()+
//                        "<endif>"+
                                readFileCsvFunction()+
                                loadContent(gneralcreating)+
//                        "<if(tablePath)>"+
//                        getTableElementFunction()+
                                getTypeElementFunction()+
//                        "<endif>"+
                                "EOF()::=<< <\\n> " +
                                "}>>"
                );

        return  stringTemplate;
    }

    public  String setterAndGetterFunction() {
        String str =  "setterAttribute(columns,aggList) ::=<< " +
                "<columns:{col |<\\n><\\t> public void set<col.column_name>(<col.column_type.name> value){<\\n><\\t>" +
                "this.<col.column_name>  = value ; <\\n><\\t>" +
                "\\}" +
                " } > " +
                "<aggList:{ agg|<\\n><\\t> public void set_AGG<agg.AggregationFunctionName> (<agg.returnType> value){<\\n><\\t>" +
                "this._AGG<agg.AggregationFunctionName> = value; <\\n><\\t> " +
                "\\} }> " +
                ">>" +


                "getterAttribute(columns,aggList) ::=<< " +
                "<columns:{col |<\\n><\\t> public <col.column_type.name> get<col.column_name>(){<\\n><\\t>" +
                "return <col.column_name> ;   <\\n><\\t>" +
                "\\}" +
                " } >" +
                "<aggList:{ agg|<\\n><\\t> public <agg.returnType> get_AGG<agg.AggregationFunctionName>(){<\\n><\\t>" +
                "return this._AGG<agg.AggregationFunctionName>; <\\n><\\t>  \\}" +
                "}>"+
                " >>";
        return str;
    }

    public String readFileCsvFunction(){
        String str = "readCsvFile(className,columns,tablePath)::=<<" +
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
                "<className> classname = new <className>();<\\n><\\t>" +
                "CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader(" +
                "<columns:{col | <if(col.LastColumn)> \"<col.column_name>\" <else> \"<col.column_name>\", <endif>  }>).withIgnoreHeaderCase().withTrim());<\\n><\\t>" +
                " for (CSVRecord csvRecord: csvParser)<\\n><\\t>" +
                "{<\\n><\\t>" +
                "<columns:{col | " +
                "if(csvRecord.get(\"<col.column_name>\") != null)" +
                "{<\\n><\\t>" +
                "classname.set<col.column_name>(" +
                "<if(col.TypeNumber)>Double.parseDouble(" +
                "csvRecord.get(\"<col.column_name>\")));" +
                "<endif>" +
                "<if(col.Typeboolean)>" +
                "Boolean.parseBoolean(" +
                "csvRecord.get(\"<col.column_name>\")));" +
                "<endif>" +
                "<if(col.TypeString)>" +
                "csvRecord.get(\"<col.column_name>\"));" +
                "<endif>" +
                "<if(col.TypeObject)>new Object());<endif>" +
                "<\\n><\\t>" +
                "\\}<\\n><\\t>" +
                "}>" +
                "}<\\n><\\t>" +
                " result.add(classname);<\\n><\\t>" +
                "}<\\n><\\t>" +
                "return result;" +
                "<else>"+
                "return null;<\\n><\\t>"+
                "<endif>"+
                "}" +
                ">>";
        return str;
    }

    public String printContentFunction(){
        String str = "printContent(className,columns)::=<<" +
                "<\\n><\\t> public void printContentFunction()<\\n><\\t>" +
                "{<\\n><\\t>" +
                "System.out.println(\"-----------------------------------------------------------------------------\");<\\n><\\t>" +
                "System.out.printf(\"<columns:{col | <if(col.FirstColumn)> %10s <else> %20s<endif>  }>\" , <columns:{col |<if(col.LastColumn)> \"<col.column_name>\" <else> \"<col.column_name>\" , <endif>}>) ;" +
                "<\\t>System.out.println();<\\n>"+
                "<\\n><\\t>System.out.println(\"-----------------------------------------------------------------------------\");" +
                "<\\n><\\t>"+
                "<\\n><\\t>for(<className> obj:entityObject)<\\n><\\t>" +
                "{<\\n><\\t>" +
                "System.out.format(\"<columns:{col |  <if(col.FirstColumn)> %5s<else> %20s<endif>  }>\" , " +
                "<columns:{col | <if(col.LastColumn)>obj.get<col.column_name>() <else> obj.get<col.column_name>(),<endif>}>" +
                ");<\\n><\\t>" +
                "System.out.println();<\\n><\\t>" +
                "}<\\n><\\t>" +
                "System.out.println(\"-----------------------------------------------------------------------------\");<\\n><\\t>" +
                "}" +
                ">>";
        return str;
    }

    public String getTypeElementFunction(){
        String str = "getTypeFunction(columns)::=<<" +
                "public \\<T> T get_types(JsonElement object)<\\n><\\t>" +
                "{<\\n>" +
                "<columns:{col |" +
                "<if(col.TypeObject)>" +
                "<col.column_type.name> t_<col.column_name> = new <col.column_type.name>();<\\n>" +
                "<endif>" +
                "<columns:{col |" +
                "<if(col.ParentTable)>" +
                "if (object.getAsJsonObject().get(\"<col.column_name>\") != null)" +
                "{<\\n>" +
                "<if(col.TypeNumber)>" +
                "t_<col.ParentTable>.set<col.column_name>(object.getAsJsonObject().get(\"<col.column_name>\").getAsDouble());<\\n>" +
                "<endif>" +
                "<if(col.TypeString)>" +
                "t_<col.ParentTable>.set<col.column_name>(object.getAsJsonObject().get(\"<col.column_name>\").getAsString());<\\n>" +
                "<endif>" +
                "<if(col.Typeboolean)>" +
                "t_<col.ParentTable>.set<col.column_name>(object.getAsJsonObject().get(\"<col.column_name>\").getAsBoolean());<\\n>" +
                "<endif>" +
                "\\}" +
                "<endif>" +
                "}>" +
                "return (T) " +
                "<columns:{col |" +
                "<if(col.TypeObject)>" +
                "t_<col.column_name>;<\\n>" +
                "<endif>" +
                "}>" +
                "}" +
                ">>";
        return str;
    }

//    public String getTableElementFunction(){
//        String str = "getTableFunction(columns)::=<<" +
//                "public \\<T> T get_table(JsonArray array)<\\n><\\t>" +
//                "{<\\n>" +
//                "<columns:{col | " +
//                "<if(col.TypeObject)>" +
//                "<col.column_type.name> t_<col.column_name> = new <col.column_type.name>();<\\n>" +
//                "<endif>"+
//                "}>" +
//                "for (int ii = 0; ii \\< array.size(); ii++)<\\n><\\t>" +
//                "{<\\n><\\t>" +
//                "<columns:{col |" +
//                "<if(col.ParentTable)>" +
//                "if (array.get(ii).getAsJsonObject().get(\"<col.column_name>\") != null)<\\n>" +
//                "{<\\n><\\t>" +
//                "<if(col.TypeNumber)>" +
//                "t_<col.ParentTable>.set<col.column_name>(array.get(ii).getAsJsonObject().get(\"<col.column_name>\").getAsDouble());<\\n><\\t>" +
//                "<endif>" +
//                "<if(col.TypeString)>" +
//                "t_<col.ParentTable>.set<col.column_name>(array.get(ii).getAsJsonObject().get(\"<col.column_name>\").getAsString());<\\n><\\t>" +
//                "<endif>" +
//                "<if(col.Typeboolean)>" +
//                "t_<col.ParentTable>.set<col.column_name>(array.get(ii).getAsJsonObject().get(\"<col.column_name>\").getAsBoolean());<\\n><\\t>" +
//                "<endif>" +
//                "\\}<\\n><\\t>" +
//                "<endif>" +
//                "}>" +
//                "}<\\n><\\t>" +
//                "return (T)" +
//                "<columns:{col | " +
//                "<if(col.TypeObject)>" +
//                "t_<col.column_name>;<\\n>" +
//                "<endif>" +
//                "}>" +
//                "}" +
//                ">>";
//        return str;
//    }

    public  String readFileJsonFunction() {
        String str  = "readJsonFile(className,columns,tablePath)::=<<" +
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
                "for (int i = 0 ; i \\< j.size() ; i++ )" +
                " {<\\n><\\t>" +
                "<className> tableName = new <className>();<\\n><\\t>" +
                "<columns:{col | if(j.get(i).getAsJsonObject().get(\"<col.column_name>\") != null);<\\n><\\t>" +
                "{<\\n><\\t>" +
                "<if(col.TypeNumber)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsDouble());<\\n><\\t>" +
                "<endif>"+
                "<if(col.TypeString)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsString());<\\n><\\t>" +
                "<endif>"+
                "<if(col.Typeboolean)>" +
                "tableName.set<col.column_name>(j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsBoolean());<\\n><\\t>" +
                "<endif>" +

                "<if(col.TypeObject)>" +
                "if (j.get(i).getAsJsonObject().get(\"<col.column_name>\").isJsonObject() == true)<\\n><\\t>" +
                "{<\\n><\\t>" +

                "\\}<\\n><\\t>"+
                "<endif>"+
                "\\}<\\n><\\t>" +
                " }>" +
                "result.add(tableName);<\\n><\\t>" +
                "}<\\n><\\t>" +
                "return result;" +
                "<else>"+
                "return null;<\\n><\\t>"+
                "<endif>"+
                "<\\n><\\t> }>>" ;
        return str;
    }
    private String loadContent(gneralcreating generalcreate) {


//       Set<Field> fields = new HashSet<>(Arrays.asList(field)) ;
//       System.out.println("set "+((Field) fields.iterator().next()).getName());
//       ArrayList<String> s = new ArrayList<>();

        String loadContent= ("loadContent(className , columns )::=<< " +
                "<\\n><\\t> <className> obj<className> = new <className>();" +
                "<columns:{ col| " +
                "<\\n><\\t>for(int <col.tableName>counter = 0 ; <col.tableName>counter \\< <col.tableName>.entityObject.size(); <col.tableName>counter++){" +
                "<col.columns:{innerCol| <\\n><\\t><\\t> obj<className>.<innerCol.thisColumn.column_name> = <innerCol.tableName>.entityObject.get(<col.tableName>counter).<innerCol.columnName>; }>" +
                "<\\n><\\t><\\t>"+
                "}>" +
                "<\\n><\\t> try{" +
                "<\\n><\\t><\\t>entityObject.add((<className>)obj<className>.clone()); <\\n> \\} <\\n>" +
                "catch (CloneNotSupportedException c){<\\n>" +
                "<\\t><\\t><\\t> c.printStackTrace();<\\n>" +
                "<\\t> }<\\n>" +
                " <columns:{ col| \\} }>"  +
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

        return loadContent ;
    }
    private String joinTemplate(){
        String joinString = ("joinTemplate()::=<< " +
                "" +
                ">>");

        return joinString;
    }

    public  String jsonObjectPart(){
        String str ="if (j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray() != null) <\\n><\\t> " +
                "{<\\n><\\t>" +
                "JsonArray nested_one = j.get(i).getAsJsonObject().get(\"<col.column_name>\").getAsJsonArray()<\\n><\\t>" +
                "set<col.column_name>(get_table(nested_one));<\\n><\\t>" +
                "\\}<\\n><\\t>" ;
        return str;
    }
    public Type returnSpecificType(String typeName){
        Type type =  new Type();
        for(Type typ :Main.symbolTable.getDeclaredTypes()){
            if( typeName.equals(typ.getName())){
                type = typ;
                return type;
            }
        }
        return type;
    }
    class Columns{
        public String columnName;
        public Column thisColumn ;
        public String tableName;
    }
    class TableAndColumn {
        public String tableName;
        public ArrayList<Columns> columns = new ArrayList<>();

    }
    private  ArrayList<TableAndColumn> splitColomNames (ArrayList<Column> columns){


        ArrayList<TableAndColumn> tableAndColumnArrayList = new ArrayList<>();

        for (Column col:columns
        ) {
//                 System.err.println(col.getColumn_name());
            String[] names ;
            TableAndColumn tableAndColumn = new TableAndColumn();
            Columns columnsInTable = new Columns();

            if(col.getColumn_name().contains("$")){
                names = col.getColumn_name().substring(1).split("_");
                columnsInTable.tableName = names[0];
                boolean found = false;
                columnsInTable.thisColumn = col;
                columnsInTable.columnName = names[1];
                tableAndColumn.columns.add(columnsInTable);

                if(!tableAndColumnArrayList.isEmpty()){

                    for (TableAndColumn table:tableAndColumnArrayList
                    ) {
                        if(table.tableName != null)
                        {
                            if(table.tableName.equals(names[0]))
                            {
                                found = false;
                                tableAndColumnArrayList.get(tableAndColumnArrayList.indexOf(table)).columns.add(columnsInTable);

                                break;
                            }else {
                                found = true;
                            }
                        }
                    }
                }else found =true;

                if(found){
                    tableAndColumn.tableName = names[0];
                    tableAndColumnArrayList.add(tableAndColumn);
                }
            }
        }
//         tableAndColumnArrayList.forEach(tableAndColumn1 -> System.err.println(tableAndColumn1.Columns));
        return tableAndColumnArrayList;
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
