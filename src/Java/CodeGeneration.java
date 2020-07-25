package Java;
import Java.AST.QueryStmt.Join.Join_Constrain;
import Java.AST.QueryStmt.Join.Join_Opreator;
import Java.AST.QueryStmt.SelectStmt.Select_Core;
import Java.AST.commn_classes_Sql.name_rule.TableName;
import Java.AST.expr.Expr;
import Java.SymbolTable.AggregationFunction;
import Java.SymbolTable.Scope;
import Java.AST.commn_classes_Sql.name_rule.TableOrSubQuery;
import Java.AST.expr.Expression;
import Java.AST.expr.Expression_List;
import Java.AST.instruction.Print_rule.Inside_the_print;
import Java.AST.instruction.Print_rule.Print;
import Java.SymbolTable.*;
import Files_code_Json_csv.employess;
import Java.AST.expr.Expr;
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
import org.stringtemplate.v4.STWriter;
import sun.plugin.javascript.navig.Array;
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
    private  void createClassType(String className ,
                                           List<Column> columnArrayList, String tablePath
            , String tableType,gneralcreating generalcreating) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, URISyntaxException {

        ArrayList<AggregationFunction> aggregationFunctionArrayList = new ArrayList<>();
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<Table> tables = new ArrayList<>();
        ArrayList<AggrAndColums>aggrAndColumsArrayList = new ArrayList<>();
        ArrayList<TablesInQuery> tablesInQueryArrayList = new ArrayList<>();
        ArrayList<WhereFullExpr>whereFullExprArrayList = new ArrayList<>();
        for (Column col:columnArrayList
             ) {
//            System.err.println("sssssssssssssssssssssssssssss "+col.getColumn_name());
            if(col.getColumn_type() instanceof AggregationFunction)
            {
                AggrAndColums aggrAndColums = new AggrAndColums();
                aggregationFunctionArrayList.add((AggregationFunction) col.getColumn_type());
                aggrAndColums.aggregationFunction =(AggregationFunction) col.getColumn_type();
//                System.err.println("fffffffffffffffffffffffffff  "+(((AggregationFunction) col.getColumn_type()).getAggregationFunctionName()));

//                System.err.println("ggggggggggggggggggggggggg "+Main.symbolTable.getAgg().size());
                aggrAndColumsArrayList.add(aggrAndColums);

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
        Select_Core select_core =  generalcreating.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core();
        ArrayList<JoinClause> joinClauseArrayList = new ArrayList<>();
        if(select_core.getJoin_clause() != null ){
            for (int i = 0; i < select_core.getJoin_clause().getJoin_opreatorList().size(); i++) {
                JoinClause joinClause = new JoinClause();
                TablesInQuery tablesInQuery = new TablesInQuery();
                if(select_core.getJoin_clause().getJoin_constrain() != null){
                    if(select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getLeft().getTableName() !=null)
                    {

                        joinClause.leftTableName = select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getLeft().getTableName().getName();

                    }
                    if(select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getLeft().getColumnName() !=null)
                    {
                        joinClause.leftColumnName = select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getLeft().getColumnName().getName();


                    }
                    if(select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getRight().getTableName() !=null)
                    {


                        joinClause.rightTableName = select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getRight().getTableName().getName();
                    }
                    if(select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getRight().getColumnName() !=null)
                    {
                        joinClause.rightColumnName = select_core.getJoin_clause().getJoin_constrain().get(i).getExpr().getRight().getColumnName().getName();

                    }
                }
                joinClauseArrayList.add(joinClause);

            }
            for (int i = 0; i <select_core.getJoin_clause().getTableOrSubQueryList().size() ; i++) {
                tablesInQueryArrayList.add(getTableColumns(select_core.getJoin_clause().getTableOrSubQueryList().get(i).getTableName().getName()));
            }
//            System.out.println(tablesInQueryArrayList);
        }
        else if (select_core.getReslult_cloumnList()!= null){
            for (int i = 0; i < select_core.getReslult_cloumnList().size(); i++) {
                if(select_core.getReslult_cloumnList().get(i).getExpr() != null)
                if(select_core.getReslult_cloumnList().get(i).getExpr().getFunction_name() != null)
                {
                    if(!aggrAndColumsArrayList.isEmpty()){

                        AggrAndColums aggrAndColums = aggrAndColumsArrayList.get(i);
                        aggrAndColums.columnName =  select_core.getReslult_cloumnList().get(i).getExpr().getColumnName().getName();
                        if(select_core.getReslult_cloumnList().get(i).getExpr().getTableName() != null)
                        {
                            aggrAndColums.tableName =  select_core.getReslult_cloumnList().get(i).getExpr().getTableName().getName();
                        }else {
                            aggrAndColums.tableName=  select_core.getTableOrSubQueryList().get(0).getTableName().getName();
                        }
                    }

                }
            }

            if(select_core.getTableOrSubQueryList() != null){
                for (int i = 0; i <select_core.getTableOrSubQueryList().size() ; i++) {
                    tablesInQueryArrayList.add(getTableColumns(select_core.getTableOrSubQueryList().get(i).getTableName().getName()));
                }
            }
        }
        whereFullExprArrayList =  where_function(parse);


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
//        System.out.println(joinClauseArrayList);
        loadFunction.add("joinClause",joinClauseArrayList);
        loadFunction.add("aggrAndColums",aggrAndColumsArrayList);
        loadFunction.add("tablesInQuery",tablesInQueryArrayList);
        loadFunction.add("whereFullExpr",whereFullExprArrayList);
//        loadFunction.add("tables",splitColomNames(columns));



        ST readJsonFile = stGroup.getInstanceOf("readJsonFile");
        readJsonFile.add("className",className);
        readJsonFile.add("columns",columns);
        readJsonFile.add("tablePath",tablePath);

        ST readCsvFile =stGroup.getInstanceOf("readCsvFile");
        readCsvFile.add("className",className);
        readCsvFile.add("tablePath",tablePath);
//        readCsvFile.add("columns",columns);
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

    private TablesInQuery getTableColumns(String tableName){
        TablesInQuery tablesInQuery = new TablesInQuery();
        for (Type type:Main.symbolTable.getDeclaredTypes()
             ) {
            if(type.getName().equals(tableName)){
                tablesInQuery.tableName = type.getName();
                tablesInQuery.columns = returnTableColumn(type);
                break;
            }
        }
        return tablesInQuery;
    }
    private String loadAggClassToSelect(){
        String stringTEmplate =(
                " <\\t><\\n>String JarPath = <agg.jar_path>;<\\n>" +
                "<\\t>String JarName = \"<agg.AggregationFunctionName>.jar\";<\\n>" +
                "<\\t>String ClassName = \"<agg.ClassName>\";<\\n>" +
                "<\\t>String MethodName = \"<agg.MethodName>\";<\\n>" +
//                "<\\t>ArrayList\\<Double> myNumbers = new ArrayList\\<>(Arrays.asList(1.0, 2.0, 3.0, 12.0));<\\n>" +
                "<\\t>URLClassLoader myClassLoader = new URLClassLoader(" +
                "<\\t>new URL[]{new File(JarPath ).toURI().toURL()\\},Main.class.getClassLoader()); <\\n>" +
                "<\\t>Class\\<?> myClass = Class.forName(ClassName, true, myClassLoader);<\\n>" +
                "<\\t>Method mySingeltonGetterMethod = myClass.getMethod(\"get\" + ClassName,null);<\\n>" +
                "<\\t>Object myObject = mySingeltonGetterMethod.invoke(null);<\\n>" +
                "<\\t>Object myValue = myObject.getClass().getDeclaredMethod(MethodName, List.class).invoke(myObject, myNumbers);<\\n>" +
                "<\\t>return (<agg.returnType>)myValue;<\\n>"

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
                        "loadFunction(aggList,tablePath,isType,className,columns,joinClause,aggrAndColums,tablesInQuery,whereFullExpr)::= <<<\\t>public void load() " +throwException()+
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
                        "<loadContent(className,columns,joinClause,aggrAndColums,tablesInQuery,whereFullExpr)>" +
                        "<endif>" +
                        "}<\\n> <if(aggList)> <loadAggFuncs(aggList)> <endif> >>  " +
                        "loadAggFuncs(aggList) ::= << " +
                        "<aggList :{ agg|<\\n><\\t> public static <agg.returnType> <agg.AggregationFunctionName>(List\\<?> myNumbers) "+throwException()+
                        "{<\\n><\\t>"+
                        loadAggClassToSelect()+
                        "<\\n><\\t> \\}" +
                        "}> >>" +
                        readFileJsonFunction()+
                        setterAndGetterFunction()+
                        printContentFunction() +
                        readFileCsvFunction()+
                        loadContent(gneralcreating)+
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
                "System.out.printf(\"<columns:{col | %30s }>\" , <columns:{col |<if(col.LastColumn)> \"<col.column_name>\" <else> \"<col.column_name>\" , <endif>}>) ;" +
                "<\\t>System.out.println();<\\n>"+
                "<\\n><\\t>for(<className> obj:entityObject)<\\n><\\t>" +
                "{<\\n><\\t>" +
                "System.out.format(\"<columns:{col | %30s }>\" , " +
                "<columns:{col | <if(col.LastColumn)>obj.get<col.column_name>()<else>obj.get<col.column_name>(),<endif>}>" +
                ");<\\n><\\t>" +
                "System.out.println();<\\n><\\t>" +
                "}<\\n><\\t>" +
                "System.out.println(\"-----------------------------------------------------------------------------\");<\\n><\\t>" +
                "}" +
                ">>";
        return str;
    }

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

//       if(generalcreate.getWithassign() !=null)//create with assign
//       {
//           if(generalcreate.getWithassign().getVar_wiht_assign().getVar() != null)//assign var
//           {
//               NameAndType nameAndTypeobj = new NameAndType();
//               nameAndTypeobj.varName = generalcreate.getWithassign().getVar_wiht_assign().getVar().getVariable_with_opretor().get(0).getVariable_name();
//               if(generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored()!= null){//is it factored select ?
//                   Type type = getVariableType(nameAndTypeobj.varName,parse.getFunctions().get(0).getHeader().getName()+"_0");
//                   nameAndTypeobj.typeName =  type.getName();
//                   nameAndTypes = nameAndTypeobj;
//                   tableName = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getTableOrSubQueryList().get(0).getTableName().getName();
//               }
//           }
//       }


       class WhereFullExpr{
           public WhereExpr leftWhereExpr ;
           public WhereExpr rightWhereExpr ;
           public String operator;
       }
       class WhereExpr{
           public String rightExpr;
           public String operator;
           public String tableName ;
           public String columnName;
           public InExpr inExpr;
       }
       class InExpr {
            ArrayList<String> strings;
       }



       String loadContent= ("statics ::= [\n" +
               "  \"\": false,\n" +
               "  \"0\": false,\n" +
               "  \".equals(\": true,\n" +
               "  default: false\n" +
               "]" +
               "loadContent(className , columns ,joinClause ,aggrAndColums, tablesInQuery ,whereFullExpr)::=<< " +
               "<\\n><\\t> <className> obj<className> = new <className>();" +
                "<\\n><\\t> <tablesInQuery:{ originalTable|  List\\<<originalTable.tableName>\\> <originalTable.tableName>List = <originalTable.tableName>.entityObject ;<\\n>  }>" +
                "<\\n><\\t> <whereFullExpr :{ whereExpr |  " +
                "<\\n><\\t> <if(whereExpr.rightWhereExpr)>" +
               " <whereExpr.leftWhereExpr.tableName>List.removeIf(<whereExpr.leftWhereExpr.tableName>s ->   " +
               " !(<whereExpr.leftWhereExpr.tableName>s.<whereExpr.leftWhereExpr.columnName> <whereExpr.leftWhereExpr.operator> <whereExpr.leftWhereExpr.rightExpr>) <if(statics.(whereExpr.leftWhereExpr.operator))> ) <endif>" +
               "<whereExpr.operator>  !(<whereExpr.leftWhereExpr.tableName>s.<whereExpr.rightWhereExpr.columnName> <whereExpr.rightWhereExpr.operator> <whereExpr.rightWhereExpr.rightExpr>) <if(statics.(whereExpr.rightWhereExpr.operator))> ) <endif>) ;" +
               "<else> " +
                " <\\t>  <whereExpr.leftWhereExpr.tableName>List.removeIf(<whereExpr.leftWhereExpr.tableName>s -> !(<whereExpr.leftWhereExpr.tableName>s.<whereExpr.leftWhereExpr.columnName> <whereExpr.leftWhereExpr.operator> <whereExpr.leftWhereExpr.rightExpr>)) <if(statics.(whereExpr.leftWhereExpr.operator))> ) <endif> ; <\\n>" +
               "<endif>  <\\n> " +
                "<\\t><\\t> <\\n>" +
                " }>" +
                "<columns:{ col| " +
                "<\\n><\\t>for(int <col.tableName>counter = 0 ; <col.tableName>counter \\< <col.tableName>List.size(); <col.tableName>counter++){" +
                "<col.columns:{innerCol| <\\n><\\t><\\t> obj<className>.<innerCol.thisColumn.column_name> = <innerCol.tableName>List.get(<col.tableName>counter).<innerCol.columnName>; }>" +
                "<\\n><\\t><\\t>"+
                "}>" +
                "<\\n><\\t> try{" +
                "<\\n><\\t><\\t><joinClause:{ joinCondition | if(  <joinCondition.leftTableName>List.get(<joinCondition.leftTableName>counter).<joinCondition.leftColumnName>" +
                "== <joinCondition.rightTableName>List.get(<joinCondition.rightTableName>counter).<joinCondition.rightColumnName>  ) }> <\\n>" +
                "<\\n><\\t><\\t><\\t>entityObject.add((<className>)obj<className>.clone()); <\\n> \\} <\\n>" +
                "catch (CloneNotSupportedException c){<\\n>" +
                "<\\t><\\t><\\t> c.printStackTrace();<\\n>" +
                "<\\t> }<\\n>"+
                "<\\n>"+
                "<\\t><\\t> <aggrAndColums :{ agg |  List\\<<agg.aggregationFunction.returnType>\\> <agg.aggregationFunction.returnType>s = new ArrayList\\<>() ;<\\n>" +
                "<\\t><\\t> <agg.tableName>.entityObject.forEach(fafa -> <agg.aggregationFunction.returnType>s.add(fafa.<agg.columnName>));<\\n>" +
                "<\\t><\\t> _AGG<agg.aggregationFunction.MethodName> = <agg.aggregationFunction.MethodName>(<agg.aggregationFunction.returnType>s);<\\n>" +
                "<\\t><\\t> System.out.println(_AGG<agg.aggregationFunction.MethodName>); }>" +
                " <columns:{ col| \\} }>"  +
//                "<\\n><\\t>Field tableField[] ="+tableName+".getClass().getFields();" +
//                String  leftTableName;
//       String LeftColumnName;
//       String rightTableName ;
//       String rightColumnName;
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
        for(Type typ :Main.symbolTable.getDeclaredTypes()){
            if(typ.getName() == typeName){
                return typ;
            }
        }
        return null;
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
    class TablesInQuery{
        public String tableName;
        public ArrayList<Column> columns ;
    }

    class JoinClause {
        public String leftTableName ;
        public String leftColumnName;
        public String rightTableName ;
        public  String rightColumnName;
    }
    class AggrAndColums{
        public AggregationFunction aggregationFunction;
        public String columnName;
        public String tableName;
    }
    class WhereFullExpr{
        public WhereExpr rightWhereExpr ;
        public WhereExpr leftWhereExpr ;
        public String operator;
    }
    class WhereExpr{
        public String rightExpr;
        public String operator;
        public String tableName ;
        public String columnName;
        public InExpr inExpr;
    }
    class InExpr {
//        public String firstExpr;
//        public String secondExpr;
        ArrayList<String> strings;


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
             else if(col.getColumn_name().contains("_AGG")){
                 }
         }
//         tableAndColumnArrayList.forEach(tableAndColumn1 -> System.err.println(tableAndColumn1.Columns));
         return tableAndColumnArrayList;
     }




    public ArrayList<WhereFullExpr> where_function(Parse p) {
        // System.out.println(" we are here........................... ");
        ArrayList<WhereFullExpr> whereExprArrayList = new ArrayList<>();
        WhereFullExpr whereFullExpr= new WhereFullExpr();
        WhereExpr whereExpr  = new WhereExpr();
        String left_side = "";
        String righ_side = "";
        String operator = "";
        Expr left_one= null;
        Expr right_one = null;
        String select_value_we_have = "";

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
                                    Select_Core select_core = generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core();
                                    // System.out.println("what we have herev "+generalcreate.getWithassign().getVar_wiht_assign().getVar().getFactored().getSelect_core().getReslult_cloumnList().size());
//                                    select_value_we_have = select_core.getReslult_cloumnList().get(0).getExpr().getColumnName().getName();
                                    if (select_core.getWhereExpr() != null) {

                                        if (select_core.getWhereExpr().getExpr().getLeft() != null) {
                                             if(select_core.getWhereExpr().getExpr().getLeft().getTableName()!=null)
                                            {
                                                whereExpr.tableName = select_core.getWhereExpr().getExpr().getLeft().getTableName().getName() ;

                                            }else {
                                                 whereExpr.tableName = select_core.getTableOrSubQueryList().get(0).getTableName().getName();
                                             }
                                            if (select_core.getWhereExpr().getExpr().getLeft().getColumnName() != null) {
                                                whereExpr.columnName = select_core.getWhereExpr().getExpr().getLeft().getColumnName().getName() ;
                                                left_side = select_core.getWhereExpr().getExpr().getLeft().getColumnName().getName();
                                                //  System.out.println(" the left side will -----"+left_side);
                                            }
                                            else if(select_core.getWhereExpr().getExpr().getLeft().getLiteral_value()!=null)
                                            {
//                                                whereExpr.columnName = select_core.getWhereExpr().getExpr().getLeft().getColumnName().getName() ;

                                                left_side=select_core.getWhereExpr().getExpr().getLeft().getLiteral_value().getReturnType();

                                            }
                                            else {  left_one= select_core.getWhereExpr().getExpr().getLeft();}
                                        }
                                        if (select_core.getWhereExpr().getExpr().getOp() != null) {
                                            operator = select_core.getWhereExpr().getExpr().getOp();
                                            whereExpr.operator = convertOperatorToJava(operator); ;


//                                            System.out.println("the operatore is ---"+operator);
                                        }

                                        if (select_core.getWhereExpr().getExpr().getRight() != null) {
                                            if(select_core.getWhereExpr().getExpr().getRight().getTableName()!=null)
                                            {
                                                if(select_core.getWhereExpr().getExpr().getRight().getColumnName()!=null) {
                                                    righ_side = select_core.getWhereExpr().getExpr().getRight().getColumnName().getName();
                                                }
                                            }else {


                                            }
                                            if (select_core.getWhereExpr().getExpr().getRight().getColumnName() != null) {
                                                righ_side = select_core.getWhereExpr().getExpr().getRight().getColumnName().getName();


                                            }

                                            else if (select_core.getWhereExpr().getExpr().getRight().getLiteral_value() != null) {
                                                righ_side = select_core.getWhereExpr().getExpr().getRight().getLiteral_value().getReturnType();
                                                whereExpr.rightExpr =  select_core.getWhereExpr().getExpr().getRight().getLiteral_value().getReturnType() ;

                                            }
                                            else if(select_core.getWhereExpr().getExpr().getArray_list_od_right_side()!=null) {
//                                                    System.err.println("XxX");
                                                if(select_core.getWhereExpr().getExpr().getArray_list_od_right_side().size()!=0){
                                                }

                                                    //in
                                                    //   System.out.println(" here we are !!!!");
//                                                    get_where_result_for_complixity_right_side(left_side,select_core.getWhereExpr().getExpr().getArray_list_od_right_side(),operator,e,select_value_we_have);
                                            }
                                             if (select_core.getWhereExpr().getExpr().getLeft().getLeft() != null){
                                                right_one =  select_core.getWhereExpr().getExpr().getRight();

                                               return  expression_with_logic(left_one, right_one,operator ,select_core);
                                            }
                                            whereFullExpr.leftWhereExpr = whereExpr;
                                            whereExprArrayList.add(whereFullExpr);


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

        return whereExprArrayList;
    }
    private String convertOperatorToJava(String operator){
         if(operator.equals("="))
             return "==";
         else if(operator.equals(("!=")) || operator.equals("<>"))
             return "!=";
         else if(operator.equals("like")||operator.equals("LIKE"))
             return ".equals(";
         else if(operator.equals("IN ") ||operator.equals("in"))
             return "IN";
         else return operator;

    }
    public void get_where_final_result(boolean column_in_the_left,String left_side, String right_side, String operator, String select_column, ArrayList<employess> datalist) {
        ArrayList<employess> temp_list = get_where_result(column_in_the_left , left_side,right_side, operator, datalist);
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

    public ArrayList<employess> get_where_result( boolean what_is_the_column,String left_side,String right_side, String operator, ArrayList<employess> datalist) {
        //know what the right real type
        //symbole map column name --> type
        //if type double // convert string ->double
        // if type boolean ///convert strign -> boolean
        // print_the_list(datalist);
        // System.out.println("the right side here will be "+right_side);
        // if the type of leftside is integer we should convert the
        String value_convert_it_type_and_compare_with_it="";
        if(what_is_the_column==true)
        {
            value_convert_it_type_and_compare_with_it=right_side;

        }
        else {value_convert_it_type_and_compare_with_it=left_side;}
        ArrayList<employess> temp_list = new ArrayList<employess>();
        if (operator.equals("=")) {
            int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
            if (left_side.equals("name")) {
                for (int i = 0; i < datalist.size(); i++) {
                    // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    // righ_side.contains()
                    //datalist.get(i) == "1";
                    if (datalist.get(i).name == value_convert_it_type_and_compare_with_it) {
                        //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        temp_list.add(datalist.get(i));

                    }

                }

            }

            return temp_list;
        }
        if (operator.equals(("!=")) || operator.equals("<>")) {
            int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
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
                int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(what_is_the_column==true) {
                    if (datalist.get(i).getId() < temp_righ_value) {
                        //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        temp_list.add(datalist.get(i));

                    }
                }
                else {
                    if(datalist.get(i).getId() >temp_righ_value)
                    {temp_list.add(datalist.get(i));}
                }


            }
            // System.out.println(" size of temp_list "+temp_list.size());
            return temp_list;
        }
        if (operator.equals(">")) {
            //System.out.println("inside the main fucntion ........"+right_side);
            int temp_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(what_is_the_column==true)
                { if (datalist.get(i).getId() > temp_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }}
                else {
                    if(datalist.get(i).getId() < temp_value)
                    {  temp_list.add(datalist.get(i));}
                }

            }
            // System.out.println(" size of temp_list "+temp_list.size());
            return temp_list;
        }
        if (operator.equals("<=")) {
            int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
            for (int i = 0; i < datalist.size(); i++) {
                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(what_is_the_column==true) {
                    if (datalist.get(i).getId() <= temp_righ_value) {
                        //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        temp_list.add(datalist.get(i));

                    }
                }else { if (datalist.get(i).getId() >= temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }}

            }
            return temp_list;
        }
        if (operator.equals(">=")) {
            int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
            for (int i = 0; i < datalist.size(); i++) {

                // System.out.println("int he arraylist we have "+datalist.get(i).getId());
                if(what_is_the_column==true) {
                    if (datalist.get(i).getId() >= temp_righ_value) {
                        //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                        temp_list.add(datalist.get(i));

                    }
                }
                else { if (datalist.get(i).getId() <= temp_righ_value) {
                    //System.out.println("int he arraylist we have "+datalist.get(i).getId());
                    temp_list.add(datalist.get(i));

                }}
            }

            return temp_list;
        }
        if(operator.equals("like")||operator.equals("LIKE"))
        {

            if(!right_side.contains("%"))
            {
                int temp_righ_value = Integer.valueOf(value_convert_it_type_and_compare_with_it);
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
    public ArrayList<WhereFullExpr> expression_with_logic(Expr left_side , Expr right_side , String logical_operator ,Select_Core select_core) {

        ArrayList<WhereFullExpr> whereExprArrayList = new ArrayList<>();
        WhereFullExpr whereFullExpr= new WhereFullExpr();
        WhereExpr leftWhereExpr  = new WhereExpr();
        WhereExpr rightWhereExpr  = new WhereExpr();


//        String left_one_from_the_left_logic_operator = "";
//        String rigth_one_from_the_left_logica_opeartor = "";
//        String left_side_from_the_right_one = "";
//        String right_side_from_the_right_one = "";
//        boolean check_it=false;
   /* if(left_side.getLeft().getColumnName()!=null)
    {
        String left = left_side.getLeft().getColumnName().getName();
    }
    if(right_side.getRight().getLiteral_value()!=null)
    {
        String right = right_side.getRight().getLiteral_value().getReturnType().toString();
        System.out.println(" show the right side "+right_side.getRight().getLiteral_value().getReturnType().toString());
    }*/

        if(left_side.getLeft().getTableName()!=null)
        {
            leftWhereExpr.tableName = left_side.getLeft().getTableName().getName();
//            left_one_from_the_left_logic_operator=left_side.getLeft().getColumnName().getName();
        }
       else {
            leftWhereExpr.tableName = select_core.getTableOrSubQueryList().get(0).getTableName().getName();
        }
        if (left_side.getLeft().getColumnName() != null) {
//            left_one_from_the_left_logic_operator = left_side.getLeft().getColumnName().getName();
            leftWhereExpr.columnName = left_side.getLeft().getColumnName().getName();


        }

        else if (left_side.getLeft().getLiteral_value() != null) {
//            left_one_from_the_left_logic_operator = left_side.getRight().getLiteral_value().getReturnType();
            // from_the_left_side_of_logic_operator = get_where_result(true,left_one_from_the_left_logic_operator, rigth_one_from_the_left_logica_opeartor, left_side.getOp().toString(), e);
        }

        if (left_side.getRight().getColumnName() != null) {
//            rigth_one_from_the_left_logica_opeartor = left_side.getLeft().getColumnName();
            leftWhereExpr.rightExpr = left_side.getRight().getColumnName().getName();

        }
         if(left_side.getRight().getTableName()!=null)
        {
//            rigth_one_from_the_left_logica_opeartor=left_side.getLeft().getColumnName();
            leftWhereExpr.rightExpr = left_side.getRight().getTableName().getName();
        }
        else if (left_side.getRight().getLiteral_value() != null) {
            leftWhereExpr.rightExpr = left_side.getRight().getLiteral_value().getReturnType();
//            rigth_one_from_the_left_logica_opeartor = left_side.getRight().getLiteral_value().getReturnType();
            // from_the_left_side_of_logic_operator = get_where_result(true,left_one_from_the_left_logic_operator, rigth_one_from_the_left_logica_opeartor, left_side.getOp().toString(), e);
        }
        if(left_side.getOp() != null)
        {
            leftWhereExpr.operator = convertOperatorToJava(left_side.getOp());
        }

        if (right_side.getRight().getColumnName() != null) {
//            right_side_from_the_right_one = left_side.getRight().getColumnName().getName();
//            rightWhereExpr.columnName = left_side.getRight().getColumnName().getName();
        }
         if(right_side.getRight().getTableName()!=null){
//            right_side_from_the_right_one= right_side.getRight().getColumnName().getName();
//             rightWhereExpr.columnName = right_side.getRight().getColumnName().getName();

         }
        else if (right_side.getRight().getLiteral_value() != null) {
//            right_side_from_the_right_one = right_side.getRight().getLiteral_value().getReturnType();
            rightWhereExpr.rightExpr = right_side.getRight().getLiteral_value().getReturnType();
        }


         if(right_side.getLeft().getTableName()!=null){
             rightWhereExpr.tableName = right_side.getLeft().getTableName().getName();
//             left_side_from_the_right_one= right_side.getLeft().getColumnName().getName();
        }else {
             rightWhereExpr.tableName = select_core.getTableOrSubQueryList().get(0).getTableName().getName();
         }
         if (right_side.getLeft().getColumnName() != null) {
             rightWhereExpr.columnName = right_side.getLeft().getColumnName().getName();
//            left_side_from_the_right_one = left_side.getRight().getColumnName().getName();
        }
         else if (right_side.getLeft().getLiteral_value() != null) {
//             rightWhereExpr.rightExpr = right_side.getLeft().getColumnName().getName();
//             left_side_from_the_right_one = right_side.getLeft().getLiteral_value().getReturnType();

        }
        if(right_side.getOp() != null)
        {
            rightWhereExpr.operator = convertOperatorToJava(right_side.getOp());
        }

        if (logical_operator.equals("&") || logical_operator.equals("and") || logical_operator.equals("AND")) {
             whereFullExpr.operator = "&&";
//            from_the_left_side_of_logic_operator = get_where_result(check_it,left_one_from_the_left_logic_operator, rigth_one_from_the_left_logica_opeartor, left_side.getOp(), e);
////            from_the_right_side_of_logical_operator = get_where_result(check_it,left_side_from_the_right_one, right_side_from_the_right_one, right_side.getOp(), from_the_left_side_of_logic_operator);
//            if (from_the_right_side_of_logical_operator.size() == 0) {
//                System.out.println(" no result we have !!");
//            }
//             else {
//                ArrayList<String> the_final = new ArrayList<String>();
//                for (int i = 0; i < from_the_right_side_of_logical_operator.size(); i++) {
//                    if (we_select_on.equals("name ")) {
//                        the_final.add(from_the_right_side_of_logical_operator.get(i).getName());
//                    }
//                }
//                for (int i = 0; i < from_the_right_side_of_logical_operator.size(); i++) {
//                    System.out.println(the_final.get(i));
//                }
//            }
//            for (int i = 0; i < from_the_right_side_of_logical_operator.size(); i++)
//                System.out.println("the result value" + from_the_right_side_of_logical_operator.get(i).getId());
        }
        else if (logical_operator.equals("||") || logical_operator.equals("OR") || logical_operator.equals("or")) {
            whereFullExpr.operator = "||";

//            from_the_left_side_of_logic_operator = get_where_result(check_it,left_one_from_the_left_logic_operator, rigth_one_from_the_left_logica_opeartor, left_side.getOp(), e);
//            from_the_right_side_of_logical_operator = get_where_result(check_it,left_side_from_the_right_one, right_side_from_the_right_one, right_side.getOp(), e);
////            if (from_the_left_side_of_logic_operator.size() > from_the_right_side_of_logical_operator.size()) {
////                for (int i = 0; i < from_the_right_side_of_logical_operator.size(); i++) {
////                    for (int j = 0; j < from_the_left_side_of_logic_operator.size(); j++) {
////                        if (from_the_right_side_of_logical_operator.get(i).equals(from_the_left_side_of_logic_operator.get(j))) {
//                            is_there=true;
//                            break;
//                        }
//                    }
//                    if(!is_there)
//                    {
//                        from_the_left_side_of_logic_operator.add(from_the_right_side_of_logical_operator.get(i));
//                    }
//                }
//
//                if(we_select_on.equals("name"))
//                {
//                    for(int i = 0; i < from_the_left_side_of_logic_operator.size(); i++)
//                    {
//                        System.out.println(" the result will be"+from_the_left_side_of_logic_operator.get(i).getName());
//                    }
//                }
//
//            } else {
//                System.out.println("the size for every side from left " + from_the_left_side_of_logic_operator.size());
//                System.out.println(" the size from the right " + from_the_right_side_of_logical_operator.size());
//                for (int i = 0; i < from_the_left_side_of_logic_operator.size(); i++) {
//                    for (int j = 0; j < from_the_right_side_of_logical_operator.size(); j++) {
//                        if (from_the_right_side_of_logical_operator.get(j).equals(from_the_left_side_of_logic_operator.get(i))) {
//                            //System.out.println(" we should not get in there !!!!!!!");
//                            is_there = true;
//                            break;
//
//                        }
//                    }
//                    if (!is_there)
//                        from_the_right_side_of_logical_operator.add(from_the_left_side_of_logic_operator.get(i));
//                }
//                if(we_select_on.equals("name"))
//                {
//                    for(int i = 0; i < from_the_right_side_of_logical_operator.size(); i++)
//                    {
//                        System.out.println(" the result will be"+from_the_right_side_of_logical_operator.get(i).getName());
//                    }
//                }
//
//            }
//

        }
        whereFullExpr.leftWhereExpr = leftWhereExpr ;
        whereFullExpr.rightWhereExpr= rightWhereExpr;

        whereExprArrayList.add(whereFullExpr);

        return whereExprArrayList;
    }

    public void  get_where_result_for_complixity_right_side(String left_side , ArrayList<String > right_side, String operator , ArrayList<employess> data_list,String  select_valu  ) {
        //if left side is number conver list from string to integer ......
        ArrayList<employess> temp_list = new ArrayList<employess>();
        for (int j = 0; j < right_side.size(); j++) {
            //according to what we will get the type is for the columnn we will conver
            // type number convert string got number
            // type boolean convert string to boolean
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
