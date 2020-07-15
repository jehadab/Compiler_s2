 package Java.SqlGenerated.TableClasses; 
import java.util.List; 
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
import Java.Main; 
import java.util.ArrayList; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonElement; 
import com.google.gson.JsonObject; 
import com.google.gson.stream.JsonReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
 public class d {
  	String    name ;
  	double    id ;   
 	String tablePath = "D:";
	String tableType = "json"; 
 	static List<d> entityObject  ;
 	public void load(){
	System.out.println("hiiiii");
	}
	public List<d> readJsonFile(){
	List<d> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("d");
	JsonArray j = json_ele.getAsJsonArray();
	Type type = returnSpecificType("d");
	List<Column> columnList = new ArrayList<>();
	columnList = returnListOfColumn(type);
	return result;
	}
	public Type returnSpecificType(String typeName)
	{
	for(Type typ :Main.symbolTable.getDeclaredTypes())
	{
	if(typ.getName() == typeName)
	{
	return typ;
	}
	}
	return null;
	}
	public List<Column>  returnListOfColumn(Type type)
	{
	List<Column> columnList = new ArrayList<>();
	for(Object col:type.getColumns().keySet().toArray())
	{
	Column column = new Column();
	column.setColumn_name(col.toString());
	column.setColumn_type(type.getColumns().get(col.toString()));
	columnList.add(column);
	}
	return columnList;
	}
 }